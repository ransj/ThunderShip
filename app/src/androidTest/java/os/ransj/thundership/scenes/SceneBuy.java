package os.ransj.thundership.scenes;


import os.ransj.thundership.Constants;

/**
 * 无尽模式购买道具
 * Created by ransj on 8/27/16.
 */
final class SceneBuy extends AbstractScene {

    @Override
    public String getConfigFileName() {
        return Constants.CONFIG_NAME_BEFORE_BUY;
    }

    @Override
    public int id() {
        return INFINITE_BUY;
    }
}
