package os.ransj.thundership;

import android.app.Instrumentation;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.UiDevice;
import android.util.Log;

import com.google.common.io.Files;

import org.json.JSONArray;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 * Created by ransj on 8/27/16.
 */
@RunWith(AndroidJUnit4.class)
public class ColorPickerRunner {
    private static final int POINT_NUM = 100;

    @Test
    public void testPicker() {
        Instrumentation instrumentation = InstrumentationRegistry.getInstrumentation();
        UiDevice mDevice = UiDevice.getInstance(instrumentation);
        File root = new File(Environment.getExternalStorageDirectory(), "ThunderShip/screenShot/");
        File[] files = root.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String filename) {
                return filename.endsWith(".png");
            }
        });
        if(files == null || files.length <= 0){
            throw new RuntimeException("请把截屏文件放在"+root.getAbsolutePath());
        }
        for (File file : files) {
            String scene = file.getName();
            String configName = null;
            int x = 0;
            int y = 0;
            int width = mDevice.getDisplayWidth();
            int height = mDevice.getDisplayHeight();
            if (Constants.IMAGE_NAME_MAIN_ENTRY.equals(scene)) {
                height = 500;
                y = mDevice.getDisplayHeight() - height;
                configName = Constants.CONFIG_NAME_MAIN_ENTRY;
            } else if (Constants.IMAGE_NAME_FRIEND_RACE.equals(scene)) {
                //DO NOTHING
                configName = Constants.CONFIG_NAME_FRIEND_RACE;
            } else if (Constants.IMAGE_NAME_FRIEND_RANK.equals(scene)) {
                height = 650;
                y = mDevice.getDisplayHeight() - height;
                configName = Constants.CONFIG_NAME_FRIEND_RANK;
            } else if (Constants.IMAGE_NAME_FRIEND_PICK.equals(scene)) {
                height = 400;
                y = mDevice.getDisplayHeight() - height;
                configName = Constants.CONFIG_NAME_FRIEND_PICK;
            } else if (Constants.IMAGE_NAME_BEFORE_BUY.equals(scene)) {
                //NO NOTHING
                configName = Constants.CONFIG_NAME_BEFORE_BUY;
            } else if (Constants.IMAGE_NAME_BATTLE.equals(scene)) {
                x = mDevice.getDisplayWidth() - 405;
                y = 235;
                width = 140;
                height = 140;
                configName = Constants.CONFIG_NAME_BATTLE;
            } else if (Constants.IMAGE_NAME_REBORN.equals(scene)) {
                x = 256;
                y = 666;
                width = 150;
                height = 600;
                configName = Constants.CONFIG_NAME_REBORN;
            } else if (Constants.IMAGE_NAME_PRIZE.equals(scene)) {
                x = 480;
                y = mDevice.getDisplayHeight() - 810;
                width = mDevice.getDisplayWidth() - 480*2;
                height = 140;
                configName = Constants.CONFIG_NAME_PRIZE;
            } else if (Constants.IMAGE_NAME_RESULT.equals(scene)) {
                y = mDevice.getDisplayHeight()>>1;
                height = mDevice.getDisplayHeight()>>1;
                configName = Constants.CONFIG_NAME_RESULT;
            } else if (Constants.IMAGE_NAME_DAILY_AWARD.equals(scene)) {
                height = 480;
                y = mDevice.getDisplayHeight() - 880;
                configName = Constants.CONFIG_NAME_DAILY_AWARD;
            } else if (Constants.IMAGE_NAME_NOTICE.equals(scene)) {
                height = 330;
                y = mDevice.getDisplayHeight() - 700;
                configName = Constants.CONFIG_NAME_NOTICE;
            } else {
                Log.d("ColorPickerRunner", "error handler image "+scene);
                continue;
            }
            Bitmap image = BitmapFactory.decodeFile(file.getAbsolutePath());
            File config = new File(Environment.getExternalStorageDirectory(), "ThunderShip/config/"+configName);
            ensureFileExist(config);
            pick(image, POINT_NUM, x, y, width, height, config);
        }
    }

    private void pick(Bitmap image, int num, int x, int y, int width, int height, File config) {
        if (image != null) {
            List<PixelPoint> points = new ArrayList<>();
            Random random = new Random(System.currentTimeMillis());
            for (int i = 0; i < num; i++) {
                int px = random.nextInt(width) + x;
                int py = random.nextInt(height) + y;
                int color = image.getPixel(px, py);
                points.add(new PixelPoint(px, py, color));
//                Log.d("ColorPickerRunner", px + ", " + py + ", " + color);
            }
            JSONArray array = new JSONArray();
            for (PixelPoint point : points) {
                array.put(point.toJSONObject());
            }
            String jsonStr = array.toString();
            Log.e("ColorPickerRunner", jsonStr);
            try {
                Files.asCharSink(config, Charset.forName("UTF-8")).write(jsonStr);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            throw new RuntimeException("获取屏幕截图失败");
        }
    }

    private void ensureFileExist(File file) {
        if (!file.exists()) {
            File parent = file.getParentFile();
            if (!parent.exists()) {
                parent.mkdirs();
            }
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
