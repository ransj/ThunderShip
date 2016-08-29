package os.ransj.thundership;

import android.graphics.Bitmap;

/**
 * Created by ransj on 8/27/16.
 */
interface Scene {

    public static final int INFINITE_MAIN_ENTRY = 0;
    public static final int INFINITE_FRIEND_RACE = 1;
    public static final int INFINITE_FRIEND_RANK = 2;
    public static final int INFINITE_FRIEND_PICK = 3;
    public static final int INFINITE_BUY = 4;
    public static final int INFINITE_REBORN = 5;
    public static final int INFINITE_PRIZE = 6;
    public static final int INFINITE_RESULT = 7;

    public boolean analysis(Bitmap image);

    public int id();

}
