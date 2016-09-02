package os.ransj.thundership.scenes;


import os.ransj.thundership.Constants;

/**
 * 无尽模式领取奖励
 * Created by ransj on 8/27/16.
 */
final class SceneGift extends AbstractScene {
    @Override
    public String getConfigFileName() {
        return Constants.CONFIG_NAME_PRIZE;
    }

    @Override
    public int id() {
        return Scene.INFINITE_PRIZE;
    }
}
