package os.ransj.thundership.actions;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Handler;
import android.support.test.uiautomator.UiDevice;
import android.util.Log;
import android.util.SparseIntArray;
import android.view.GestureDetector;

import java.util.ArrayList;
import java.util.List;

import os.ransj.thundership.Constants;

/**
 * Created by ransj on 9/3/16.
 */
class MissileAction implements Action {
    private long mLastMoveTime;
    private Runnable mLastRunable;

    @Override
    public void processLocation(Bitmap image, final UiDevice device, final ShipLocation location, Handler handler) {
        int move = 0;
        int y = device.getDisplayHeight() - 142;
        while (ActionTools.isMissleArea(image, location.mX - move, y, 100)) {
            move += Constants.SHIP_SIZE;
        }
        if (move > 0) {
            Log.d("MissileAction", "rocket found, move  "+move);
            device.swipe(location.mX, location.mY, location.mX - move, location.mY, 20);
            location.mX -= move;
            if(mLastRunable != null){
                handler.removeCallbacks(mLastRunable);
            }
            mLastRunable = new Runnable() {
                @Override
                public void run() {
                    // wait for last move to complete
                    if(mLastMoveTime > 0){
                        for (;mLastMoveTime + 20 * 5 > System.currentTimeMillis(); ) {
                            Thread.yield();
                        }
                    }
                    mLastMoveTime = System.currentTimeMillis();
                    device.swipe(location.mX, location.mY, device.getDisplayWidth() >> 1, location.mY, 20);
                    location.mX = device.getDisplayWidth() >> 1;
                }
            };
            handler.postDelayed(mLastRunable, 10000);
        }
    }

}
