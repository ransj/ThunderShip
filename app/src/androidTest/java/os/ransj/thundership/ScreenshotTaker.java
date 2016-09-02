package os.ransj.thundership;

import android.app.UiAutomation;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.util.Log;

/**
 * Created by ransj on 8/27/16.
 */
final class ScreenshotTaker implements Handler.Callback {
    private static final int MSG_START = 100;
    private UiAutomation mDevice;
    private long mInterval;
    private HandlerThread mThread;
    private Handler mHandler;
    private ScreenshotListener mListener;

    /**
     * @param device
     * @param interval <0 automate interval
     */
    public ScreenshotTaker(UiAutomation device, long interval, ScreenshotListener listener) {
        mDevice = device;
        mInterval = interval;
        mListener = listener;
    }

    public void setInterval(long interval) {
        mInterval = interval;
    }

    public void start() {
        mThread = new HandlerThread("ScreenshotTaker");
        mThread.start();
        mHandler = new Handler(mThread.getLooper(), this);
        mHandler.sendEmptyMessage(MSG_START);
    }

    @Override
    public boolean handleMessage(Message msg) {
        switch (msg.what) {
            case MSG_START:
                long start = System.currentTimeMillis();
                Bitmap result = mDevice.takeScreenshot();
                if (result != null) {
                    Log.d("ThunderShipRunner", "take screenshot, cost time " + (System.currentTimeMillis() - start));
                    mListener.onScreenShotToken(result);
                }
                for (; start + mInterval > System.currentTimeMillis(); ) {
                    Thread.yield();
                }
                mHandler.sendMessage(mHandler.obtainMessage(MSG_START));
                break;
        }
        return true;
    }

    public interface ScreenshotListener {
        public void onScreenShotToken(Bitmap image);
    }

}
