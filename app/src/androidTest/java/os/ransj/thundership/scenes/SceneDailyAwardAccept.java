package os.ransj.thundership.scenes;

import os.ransj.thundership.Constants;

/**
 * Created by ransj on 9/10/16.
 */
class SceneDailyAwardAccept extends AbstractScene {
    @Override
    public String getConfigFileName() {
        return Constants.CONFIG_NAME_DAILYAWARD_ACCEPT;
    }

    @Override
    public int id() {
        return Scene.INFINITE_DAILYAWARD_ACCEPT;
    }
}
