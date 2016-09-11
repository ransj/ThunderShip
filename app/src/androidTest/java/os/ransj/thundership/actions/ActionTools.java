package os.ransj.thundership.actions;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.util.Log;

import java.math.BigDecimal;

/**
 * Created by ransj on 9/3/16.
 */
class ActionTools {
    /**
     * Return the red component of a color int. This is the same as saying
     * (color >> 16) & 0xFF
     */
    public static int red(int color) {
        return (color >> 16) & 0xFF;
    }

    /**
     * Return the green component of a color int. This is the same as saying
     * (color >> 8) & 0xFF
     */
    public static int green(int color) {
        return (color >> 8) & 0xFF;
    }

    /**
     * Return the blue component of a color int. This is the same as saying
     * color & 0xFF
     */
    public static int blue(int color) {
        return color & 0xFF;
    }

    public static boolean isMissleArea(Bitmap image, int x, int y, int height) {
        int[] values = new int[height];
        int[] values2 = new int[height];
        image.getPixels(values, 0, 1, x, y, 1, height);
        image.getPixels(values2, 0, 1, x + 10, y, 1, height);
        long count = 0;
        long count2 = 0;
        for (int i = 0; i < height; i++) {
            count += values[i];
            count2 += (values[i] - values2[i]);
        }
        int avg = (int) (count / height);
        long variance = 0;
        for (int i = 0; i < height; i++) {
            variance += (values[i] - avg);
        }
        long ratio = Math.abs(variance);
        long ratio2 = Math.abs(count2 / height);
        int red = red(values[height -1]);
        Log.d("ActionTools", x + " , " + ratio + " , " + ratio2 + " , "+red);
        return ratio < 120 && ratio2 > 5000000 &&  red> 130;
    }
}
