package os.ransj.thundership.scenes;

import android.graphics.Bitmap;

/**
 * Created by ransj on 8/27/16.
 */
public interface Scene {

    int INFINITE_MAIN_ENTRY = 0;
    int INFINITE_FRIEND_RACE = 1;
    int INFINITE_FRIEND_RANK = 2;
    int INFINITE_FRIEND_PICK = 3;
    int INFINITE_BUY = 4;
    int INFINITE_REBORN = 5;
    int INFINITE_PRIZE = 6;
    int INFINITE_RESULT = 7;

    boolean analysis(Bitmap image);

    int id();

}
