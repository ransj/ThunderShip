package os.ransj.thundership;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.support.test.uiautomator.UiDevice;
import android.view.Gravity;

import os.ransj.thundership.scenes.Scene;

/**
 * Created by ransj on 8/27/16.
 */
final class SceneActor implements Handler.Callback {
    private HandlerThread mThread;
    private Handler mHandler;
    private int mMarginBottom;
    private UiDevice mDevice;
    private int mScreenWidth;
    private int mScreenHeight;

    public SceneActor(UiDevice device, int marginBottom){
        mMarginBottom = marginBottom;
        mDevice = device;
        mScreenWidth = device.getDisplayWidth();
        mScreenHeight = device.getDisplayHeight();
    }

    public void start() {
        mThread = new HandlerThread("SceneActor");
        mThread.start();
        mHandler = new Handler(mThread.getLooper(), this);
    }

    public void dealSence(int scene) {
        mHandler.sendEmptyMessageDelayed(scene, 100);
    }

    @Override
    public boolean handleMessage(Message msg) {
        switch (msg.what) {
            case Scene.INFINITE_MAIN_ENTRY:
                click(325, 163, Gravity.LEFT, Gravity.BOTTOM);
                break;
            case Scene.INFINITE_FRIEND_RACE:
                click(mScreenWidth >> 1, 400, Gravity.LEFT, Gravity.BOTTOM);
                break;
            case Scene.INFINITE_FRIEND_RANK:
                click(mScreenWidth >> 1, 280, Gravity.LEFT, Gravity.BOTTOM);
                break;
            case Scene.INFINITE_FRIEND_PICK:
                click(mScreenWidth >> 1, 280, Gravity.LEFT, Gravity.BOTTOM);
                break;
            case Scene.INFINITE_BUY:
                click(mScreenWidth >> 1, 280, Gravity.LEFT, Gravity.BOTTOM);
                break;
            case Scene.INFINITE_REBORN:
                click(mScreenWidth >> 2, 1136, Gravity.LEFT, Gravity.BOTTOM);
                break;
            case Scene.INFINITE_PRIZE:
                click(mScreenWidth >> 1, 737, Gravity.LEFT, Gravity.BOTTOM);
                break;
            case Scene.INFINITE_RESULT:
                click(mScreenWidth >> 2, 265, Gravity.LEFT, Gravity.BOTTOM);
                break;
            case Scene.INFINITE_DAILYAWARD:
                click(mScreenWidth >> 1, 540, Gravity.LEFT, Gravity.BOTTOM);
                break;
            case Scene.INFINITE_NOTICE:
                click(mScreenWidth >> 1, 570, Gravity.LEFT, Gravity.BOTTOM);
                break;
            case Scene.INFINITE_DAILYAWARD_ACCEPT:
                click(mScreenWidth >> 1, 1050, Gravity.LEFT, Gravity.BOTTOM);
                break;
            case Scene.WPK_ENTRY:
                click(mScreenWidth >> 1, 1320, Gravity.LEFT, Gravity.TOP);
                break;
            case Scene.WPK_BUY:
                click(mScreenWidth >> 1, 280, Gravity.LEFT, Gravity.BOTTOM);
                break;
        }
        return true;
    }

    private void click(int x, int y, int gravityX, int gravityY) {
        x = (gravityX == Gravity.LEFT ? x : mScreenWidth - x);
        y = (gravityY == Gravity.TOP ? y : mScreenHeight - y - mMarginBottom);
        mDevice.click(x, y);
    }

}
