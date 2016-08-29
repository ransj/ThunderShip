package os.ransj.thundership;


import android.app.Instrumentation;
import android.graphics.Bitmap;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.UiDevice;
import android.util.Log;

import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
@RunWith(AndroidJUnit4.class)
public class ThunderShipRunner implements ScreenshotTaker.ScreenshotListener, ScreenshotAnalysis.ScreenshotAnalysisListener {
    private ScreenshotAnalysis mAnalysis;
    private ScreenshotActor mActor;

    @Test
    public void startTest() {
        Instrumentation instrumentation= InstrumentationRegistry.getInstrumentation();
        UiDevice device = UiDevice.getInstance(instrumentation);
        int marginBottom = device.getDisplayHeight() - InstrumentationRegistry.getContext().getResources().getDisplayMetrics().heightPixels;
        mActor = new ScreenshotActor(device, marginBottom);
        mActor.start();
        new ScreenshotTaker(instrumentation.getUiAutomation(), 5000, this).start();
        mAnalysis = new ScreenshotAnalysis(this);
        mAnalysis.start();
        for (;;) {
            Thread.yield();
        }
    }

    @Override
    public void onScreenShotToken(Bitmap image) {
        mAnalysis.analysis(image);
    }

    @Override
    public void onScreenshotAnalysised(int scene) {
        Log.d("ThunderShipRunner", "onScreenshotAnalysised " + scene);
        mActor.dealSence(scene);
    }

}