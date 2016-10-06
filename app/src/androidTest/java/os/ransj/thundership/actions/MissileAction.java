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
    private Runnable mLastRunable;
    private boolean mBackCenter;
    private boolean mCanMove = true;

    @Override
    public void processLocation(Bitmap image, final UiDevice device, final ShipLocation location, Handler handler) {
        if (mBackCenter) {
            mBackCenter = false;
            backToCenter(image, device, location);
            return;
        }
        if (!mCanMove) {
            return;
        }
        int move = 0;
        int y = device.getDisplayHeight() - 142;
        while (ActionTools.isMissleArea(image, location.mX - move, y, 100)) {
            move += Constants.SHIP_SIZE;
        }
        if (move > 0) {
            Log.d("MissileAction", "rocket found, move  "+move);
            device.swipe(location.mX, location.mY, location.mX - move, location.mY, 20);
            mCanMove = false;
            final int finalMove = move;
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    location.mX -= finalMove;
                    mCanMove = true;
                }
            }, 150);
            if(mLastRunable != null){
                handler.removeCallbacks(mLastRunable);
            }
            mLastRunable = new Runnable() {
                @Override
                public void run() {
                    mBackCenter = true;
                }
            };
            handler.postDelayed(mLastRunable, 10000);
        }
    }

    private void backToCenter(Bitmap image, UiDevice device, ShipLocation location) {
        int y = device.getDisplayHeight() - 142;
        if(!ActionTools.isMissleArea(image, device.getDisplayWidth() >> 1, y, 100)){
            device.swipe(location.mX, location.mY, device.getDisplayWidth() >> 1, location.mY, 20);
            location.mX = device.getDisplayWidth() >> 1;
        }
    }

}
