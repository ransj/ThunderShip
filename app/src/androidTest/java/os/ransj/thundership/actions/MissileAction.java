package os.ransj.thundership.actions;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Handler;
import android.support.test.uiautomator.UiDevice;
import android.util.Log;
import android.util.SparseIntArray;

import java.util.ArrayList;
import java.util.List;

import os.ransj.thundership.Constants;

/**
 * Created by ransj on 9/3/16.
 */
class MissileAction implements Action {
    private List<Integer> mHitRows = new ArrayList<>();

    @Override
    public void processLocation(Bitmap image, final UiDevice device, final ShipLocation location, Handler handler) {
        mHitRows.clear();
//        int min = location.mX - (Constants.SHIP_SIZE >> 1);
//        int max = location.mX + (Constants.SHIP_SIZE >> 1);
//        int gap = device.getDisplayWidth() >> 3;
//        int start = min / gap * gap;
//        int end = max / gap * gap;
        int y = device.getDisplayHeight() - 142;
//        for (int i = start; i <= end; i += gap) {
//            if (ActionTools.isMissleArea(image, i, y, 100)) {
            if (ActionTools.isMissleArea(image, location.mX, y, 100)) {
                mHitRows.add(location.mX);
            }
//        }
        if (!mHitRows.isEmpty()) {
            StringBuilder builder = new StringBuilder();
            for (Integer value : mHitRows) {
                builder.append(value);
                builder.append(" , ");
            }
            Log.d("MissileAction", "rocket found " + builder.toString());
            device.swipe(location.mX, location.mY, location.mX - Constants.SHIP_SIZE, location.mY, 50);
            location.mX -= Constants.SHIP_SIZE;
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    device.swipe(location.mX, location.mY, location.mX + Constants.SHIP_SIZE, location.mY, 50);
                    location.mX += Constants.SHIP_SIZE;
                }
            }, 5000);
        }
    }

}
