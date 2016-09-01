package os.ransj.thundership;

import android.graphics.Bitmap;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by ransj on 8/27/16.
 */
public final class PixelPoint {
    private int mX;
    private int mY;
    private int mValue;

    public PixelPoint(int x, int y, int value) {
        mX = x;
        mY = y;
        mValue = value;
    }

    public boolean hit(Bitmap image) {
        return image.getPixel(mX, mY) == mValue;
    }

    @Override
    public String toString() {
        return mX + ", " + mY + ", " + mValue;
    }

    public static PixelPoint parseJSONObject(JSONObject json) {
        int x = json.optInt("x");
        int y = json.optInt("y");
        int value = json.optInt("value");
        return new PixelPoint(x, y, value);
    }

    public JSONObject toJSONObject(){
        JSONObject object = new JSONObject();
        try {
            object.put("x", mX);
            object.put("y", mY);
            object.put("value", mValue);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return object;
    }
}
