package os.ransj.thundership.scenes;


import os.ransj.thundership.Constants;

/**
 * 无尽模式结果确认页面
 * Created by ransj on 8/27/16.
 */
final class SceneResult extends AbstractScene {

    @Override
    public String getConfigFileName() {
        return Constants.CONFIG_NAME_RESULT;
    }

    @Override
    public int id() {
        return Scene.INFINITE_RESULT;
    }
}
