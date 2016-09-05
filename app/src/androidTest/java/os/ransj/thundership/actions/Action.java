package os.ransj.thundership.actions;

import android.graphics.Bitmap;
import android.support.test.uiautomator.UiDevice;

/**
 * Created by ransj on 9/3/16.
 */
interface Action {

    void processLocation(Bitmap image, UiDevice device, ShipLocation location);

}
