package os.ransj.thundership.scenes;

import android.graphics.Bitmap;
import android.os.Environment;

import com.google.common.io.Files;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
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
            File config = new File(Environment.getExternalStorageDirectory(), "ThunderShip/config/"+getConfigFileName());
            String jsonString = Files.readFirstLine(config, Charset.forName("UTF-8"));
            JSONArray array = new JSONArray(jsonString);
            for (int i = 0, size = array.length(); i < size; i++) {
                mPoints.add(PixelPoint.parseJSONObject(array.optJSONObject(i)));
            }
        } catch (JSONException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (IOException e) {
            e.printStackTrace();
        }
        mHitFilter = (int) (mPoints.size()*FILTER);
    }

    public abstract String getConfigFileName();

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
