package os.ransj.thundership.scenes;

import android.graphics.Bitmap;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import os.ransj.thundership.PixelPoint;

/**
 * Created by ransj on 8/27/16.
 */
abstract class AbstractScene implements Scene {
    private static final float FILTER = 0.75f;
    private List<PixelPoint> mPoints;
    private int mHitFilter;

    public AbstractScene() {
        mPoints = new ArrayList<>();
        try {
            JSONArray array = new JSONArray(getPointJSONString());
            for (int i = 0, size = array.length(); i < size; i++) {
                mPoints.add(PixelPoint.parseJSONObject(array.optJSONObject(i)));
            }
        } catch (JSONException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        mHitFilter = (int) (mPoints.size()*FILTER);
    }

    public abstract String getPointJSONString();

    @Override
    public boolean analysis(Bitmap image) {
        int count = 0;
        for (PixelPoint point : mPoints) {
            if (point.hit(image)) {
                count++;
                if (count > mHitFilter) {
                    return true;
                }
            }
        }
        return false;
    }
}
