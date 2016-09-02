package os.ransj.thundership.scenes;


import os.ransj.thundership.Constants;

/**
 * 无尽模式是否需要复活
 * Created by ransj on 8/27/16.
 */
final class SceneReborn extends AbstractScene {

    @Override
    public String getConfigFileName() {
        return Constants.CONFIG_NAME_REBORN;
    }

    @Override
    public int id() {
        return Scene.INFINITE_REBORN;
    }
}
