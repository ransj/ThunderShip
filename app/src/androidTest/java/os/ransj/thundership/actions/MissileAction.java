package os.ransj.thundership.actions;

import android.graphics.Bitmap;
import android.graphics.Color;
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
    public void processLocation(Bitmap image, UiDevice device, ShipLocation location) {
        mHitRows.clear();
        int min = location.mX - (Constants.SHIP_SIZE >> 1);
        int max = location.mX + (Constants.SHIP_SIZE >> 1);
        int gap = device.getDisplayWidth() >> 3;
        int start = min / gap * gap;
        int end = max / gap * gap;
        int y = device.getDisplayHeight() - 142;
        for (int i = start; i <= end; i += gap) {
            if (ActionTools.isMissleArea(image, i, y, 100)) {
                mHitRows.add(i);
            }
        }
        if (!mHitRows.isEmpty()) {
            StringBuilder builder = new StringBuilder();
            for (Integer value : mHitRows) {
                builder.append(value);
                builder.append(" , ");
            }
            Log.d("MissileAction", "rocket found " + builder.toString());
        }
    }

}
