package os.ransj.thundership.actions;

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.support.test.uiautomator.UiDevice;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ransj on 9/3/16.
 */
public class BattleActor implements Handler.Callback {
    private static final int MSG_ID_ININT = 99;
    private static final int MSG_ID_IMAGE = 98;
    private HandlerThread mThread;
    private Handler mHandler;
    private List<Action> mActions;
    private ShipLocation mShipLocation;
    private UiDevice mDevice;
    private boolean mIsIdle = true;

    public BattleActor(UiDevice device) {
        mDevice = device;
        mThread = new HandlerThread("BattleActor");
        mThread.start();
        mHandler = new Handler(mThread.getLooper(), this);
        mShipLocation = new ShipLocation(device.getDisplayWidth() >> 1, device.getDisplayHeight() - 660);
    }

    public void start() {
        mHandler.sendEmptyMessage(MSG_ID_ININT);
    }

    @Override
    public boolean handleMessage(Message msg) {
        switch (msg.what) {
            case MSG_ID_IMAGE:
                synchronized (BattleActor.this){
                    mIsIdle = true;
                    Bitmap image = (Bitmap) msg.obj;
                    msg.obj = null;
                    inspectBattle(image);
                }
                break;
            case MSG_ID_ININT:
                init();
                break;
        }
        return true;
    }

    private void init() {
        mActions = new ArrayList<>();
        mActions.add(new MissileAction());
    }

    public void dealBattle(Bitmap image){
        synchronized (BattleActor.this){
            if (mIsIdle) {
                mIsIdle = false;
                Message msg = Message.obtain();
                msg.what = MSG_ID_IMAGE;
                msg.obj = image;
                mHandler.sendMessage(msg);
            } else {
                image.recycle();
            }
        }
    }

    private void inspectBattle(Bitmap image) {
        long start = System.currentTimeMillis();
        for(Action action : mActions){
            action.processLocation(image, mDevice, mShipLocation, mHandler);
        }
        Log.d("BattleActor", "success inspectBattle cost time " + (System.currentTimeMillis() - start));
        image.recycle();
    }
}
