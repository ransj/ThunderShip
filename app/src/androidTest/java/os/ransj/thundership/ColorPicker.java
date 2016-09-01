package os.ransj.thundership;

import android.app.Instrumentation;
import android.app.UiAutomation;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.os.Environment;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.UiDevice;
import android.util.Log;

import org.json.JSONArray;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 * Created by ransj on 8/27/16.
 */
@RunWith(AndroidJUnit4.class)
public class ColorPicker {
    private static final int POINT_NUM = 30;
    private static final int POINT_HIT_NUM = 20;
    private static final int CHECK_TIMES = 5;
    private UiDevice mDevice;
    private UiAutomation mAutomation;
    private boolean mIsRunning = true;

    @Test
    public void testPicker() {
        Instrumentation instrumentation = InstrumentationRegistry.getInstrumentation();
        mDevice = UiDevice.getInstance(instrumentation);
        mAutomation = instrumentation.getUiAutomation();
        IntentFilter filter = new IntentFilter();
        filter.addAction("os.ransj.color.picker");
        InstrumentationRegistry.getContext().registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String scene = intent.getStringExtra("scene");
                Log.d("ColorPicker", "onReceive broadcast, scene "+scene);
                File config = new File(Environment.getExternalStorageDirectory(), "ThunderShip/config/" + scene + ".txt");
                ensureFileExist(config);
                int x = 0;
                int y = 0;
                int width = mDevice.getDisplayWidth();
                int height = mDevice.getDisplayHeight();
                if ("MainEntry".equals(scene)) {
                    height = 500;
                    y = mDevice.getDisplayHeight() - height;
                } else if ("InfiniteFriendRace".equals(scene)) {
                    //DO NOTHING
                } else if ("InfiniteFriendRank".equals(scene)) {
                    height = 500;
                    y = mDevice.getDisplayHeight() - height;
                } else if ("InfiniteFriendPick".equals(scene)) {
                    height = 400;
                    y = mDevice.getDisplayHeight() - height;
                } else if ("InfiniteBeforeBuy".equals(scene)) {
                } else if ("InfiniteReborn".equals(scene)) {
                } else if ("InfinitePrize".equals(scene)) {
                } else if ("InfiniteResult".equals(scene)) {
                }
                pick(mDevice, POINT_NUM, x, y, width, height, config);
            }
        }, filter);
        for (; mIsRunning; ) {
            Thread.yield();
        }
    }

    private void pick(UiDevice device, int num, int x, int y, int width, int height, File config) {
        Bitmap image = mAutomation.takeScreenshot();
        if (image != null) {
            List<PixelPoint> points = new ArrayList<>();
            Random random = new Random(System.currentTimeMillis());
            for (int i = 0; i < num; i++) {
                int px = random.nextInt(width) + x;
                int py = random.nextInt(height) + y;
                int color = image.getPixel(px, py);
                points.add(new PixelPoint(px, py, color));
//                Log.d("ColorPicker", px + ", " + py + ", " + color);
            }
            check(device, points, 1, x, y, width, height, config);
        } else {
            throw new RuntimeException("获取屏幕截图失败");
        }
    }

    private void check(UiDevice device, List<PixelPoint> points, int times, int x, int y, int width, int height, File config) {
        Bitmap image = mAutomation.takeScreenshot();
        if (image != null) {
            List<PixelPoint> hits = new ArrayList<>();
            for (PixelPoint point : points) {
                if (point.hit(image)) {
                    hits.add(point);
                }
            }
            int size = hits.size();
            if (size >= POINT_HIT_NUM) {
                if (++times > CHECK_TIMES) {
                    JSONArray array = new JSONArray();
                    for (PixelPoint point : points) {
                        array.put(point.toJSONObject());
                    }
                    Log.e("ColorPicker", array.toString());
                } else {
                    check(device, hits, times, x, y, width, height, config);
                }
            } else {
                Log.d("ColorPicker", "check failed, try again " + size);
                pick(device, POINT_NUM, x, y, width, height, config);
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
