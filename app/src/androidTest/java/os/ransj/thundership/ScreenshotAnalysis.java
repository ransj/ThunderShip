package os.ransj.thundership;

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ransj on 8/27/16.
 */
final class ScreenshotAnalysis implements Handler.Callback {
    private static final int MSG_INIT = 99;
    private static final int MSG_ANALYSIS = 100;
    private ScreenshotAnalysisListener mListener;
    private HandlerThread mThread;
    private Handler mHandler;
    private List<Scene> mScenes;
    private int mScene = -1;

    public ScreenshotAnalysis(ScreenshotAnalysisListener listener) {
        mListener = listener;
    }

    public void start() {
        mThread = new HandlerThread("ScreenshotAnalysis");
        mThread.start();
        mHandler = new Handler(mThread.getLooper(), this);
        mHandler.sendEmptyMessage(MSG_INIT);
    }

    public void analysis(Bitmap image) {
        Message msg = new Message();
        msg.what = MSG_ANALYSIS;
        msg.obj = image;
        mHandler.sendMessage(msg);
    }

    @Override
    public boolean handleMessage(Message msg) {
        switch (msg.what) {
            case MSG_ANALYSIS:
//                Log.d("ThunderShipRunner", "start screenshot analysis");
//                long start = System.currentTimeMillis();
                int scene = analysisBitmap((Bitmap) msg.obj);
//                Log.d("ThunderShipRunner", "end screenshot analysis scene " + scene + " cost time " + (System.currentTimeMillis() - start));
                if (scene >= 0 && mScene != scene) {
                    mScene = scene;
                    mListener.onScreenshotAnalysised(scene);
                }
                break;
            case MSG_INIT:
                init();
                break;
        }
        return true;
    }

    private void init() {
        mScenes = new ArrayList<>();
        mScenes.add(new SceneMainEntry());
        mScenes.add(new SceneFriendRace());
        mScenes.add(new SceneFriendRank());
        mScenes.add(new SceneFriendPick());
        mScenes.add(new SceneBuy());
        mScenes.add(new SceneReborn());
        mScenes.add(new SceneGift());
        mScenes.add(new SceneResult());
    }

    private int analysisBitmap(Bitmap image) {
        for (Scene scene : mScenes) {
            if (scene.analysis(image)) {
                image.recycle();
                return scene.id();
            }
        }
        image.recycle();
        return -1;
    }

    public interface ScreenshotAnalysisListener {
        public void onScreenshotAnalysised(int scene);
    }

}
