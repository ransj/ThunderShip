package os.ransj.thundership.actions;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.UiDevice;
import android.util.Log;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.File;

/**
 * Created by ransj on 9/1/16.
 */
@RunWith(AndroidJUnit4.class)
public class MissileInspectRunner {

    @Test
    public void inspect() {
        UiDevice device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
        File file = new File(Environment.getExternalStorageDirectory(), "ThunderShip/screenShot/inspect.png");
        Bitmap image = BitmapFactory.decodeFile(file.getAbsolutePath());
        int interval = device.getDisplayWidth() >> 3;
        for (int i = 1; i < 8; i++) {
            int x = interval*i;
            int y = device.getDisplayHeight()-142;
            int height = 100;
            Log.d("MissileInspectRunner", x+" , "+ActionTools.isMissleArea(image, x, y, height));
        }
    }

}
