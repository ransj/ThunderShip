package os.ransj.thundership;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.support.test.uiautomator.UiDevice;
import android.view.Gravity;

/**
 * Created by ransj on 8/27/16.
 */
final class ScreenshotActor implements Handler.Callback {
    private HandlerThread mThread;
    private Handler mHandler;
    private int mMarginBottom;
    private UiDevice mDevice;
    private int mScreenWidth;
    private int mScreenHeight;

    public ScreenshotActor(UiDevice device, int marginBottom){
        mMarginBottom = marginBottom;
        mDevice = device;
        mScreenWidth = device.getDisplayWidth();
        mScreenHeight = device.getDisplayHeight();
    }

    public void start() {
        mThread = new HandlerThread("ScreenshotActor");
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
                click(mScreenWidth >> 1, 1258, Gravity.LEFT, Gravity.TOP);
                break;
            case Scene.INFINITE_FRIEND_RANK:
                click(mScreenWidth >> 1, 144, Gravity.LEFT, Gravity.BOTTOM);
                break;
            case Scene.INFINITE_FRIEND_PICK:
                click(mScreenWidth >> 1, 144, Gravity.LEFT, Gravity.BOTTOM);
                break;
            case Scene.INFINITE_BUY:
                click(mScreenWidth >> 1, 144, Gravity.LEFT, Gravity.BOTTOM);
                break;
            case Scene.INFINITE_REBORN:
                click(mScreenWidth >> 2, 1336, Gravity.LEFT, Gravity.TOP);
                break;
            case Scene.INFINITE_PRIZE:
                click(mScreenWidth >> 1, 657, Gravity.LEFT, Gravity.BOTTOM);
                break;
            case Scene.INFINITE_RESULT:
                click(mScreenWidth >> 2, 250, Gravity.LEFT, Gravity.BOTTOM);
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
