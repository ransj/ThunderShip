package os.ransj.thundership;


import android.app.Instrumentation;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.PowerManager;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.UiDevice;
import android.util.Log;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import os.ransj.thundership.actions.BattleActor;
import os.ransj.thundership.scenes.Scene;
import os.ransj.thundership.scenes.ScreenshotAnalysis;

/**
 * nohup am instrument -w -r -e debug false -e class os.ransj.thundership.ThunderShipRunner os.ransj.thundership.test/android.support.test.runner.AndroidJUnitRunner >/dev/null 2>&1 &
 *
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
@RunWith(AndroidJUnit4.class)
public class ThunderShipRunner implements ScreenshotTaker.ScreenshotListener, ScreenshotAnalysis.ScreenshotAnalysisListener {
    private ScreenshotAnalysis mAnalysis;
    private SceneActor mSceneActor;
    private BattleActor mBattleActor;

    @Test
    public void startTest() {
        Instrumentation instrumentation= InstrumentationRegistry.getInstrumentation();
        UiDevice device = UiDevice.getInstance(instrumentation);
        int marginBottom = device.getDisplayHeight() - InstrumentationRegistry.getContext().getResources().getDisplayMetrics().heightPixels;
        mSceneActor = new SceneActor(device, marginBottom);
        mSceneActor.start();
        new ScreenshotTaker(instrumentation.getUiAutomation(), -1, this).start();
        mAnalysis = new ScreenshotAnalysis(this);
        mAnalysis.start();
        mBattleActor = new BattleActor(device);
        mBattleActor.start();
        blackScreen();
        for (;;) {
            Thread.yield();
        }
    }

    @Override
    public void onScreenShotToken(Bitmap image) {
        mAnalysis.analysis(image);
    }

    @Override
    public void onSceneAnalysised(int scene) {
        Log.d("ThunderShipRunner", "onSceneAnalysised " + scene);
        mSceneActor.dealSence(scene);
    }

    @Override
    public void onBattle(Bitmap image) {
        Log.d("ThunderShipRunner", "onBattle ");
        mBattleActor.dealBattle(image);
    }

    private void blackScreen() {
        try {
            Context ctx = InstrumentationRegistry.getContext();
            PowerManager pm = (PowerManager) ctx.getSystemService(Context.POWER_SERVICE);
            Method method = PowerManager.class.getDeclaredMethod("setBacklightBrightness", int.class);
            method.setAccessible(true);
            //调用实现PowerManagerService的setBacklightBrightness
            method.invoke(pm, 0);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

}