package os.ransj.thundership.scenes;

import os.ransj.thundership.Constants;

/**
 * Created by ransj on 9/6/16.
 */
class SceneDailyAward extends AbstractScene {

    @Override
    public String getConfigFileName() {
        return Constants.CONFIG_NAME_DAILY_AWARD;
    }

    @Override
    public int id() {
        return Scene.INFINITE_DAILYAWARD;
    }

}
