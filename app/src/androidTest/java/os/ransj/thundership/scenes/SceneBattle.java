package os.ransj.thundership.scenes;

import os.ransj.thundership.Constants;

/**
 * Created by ransj on 9/3/16.
 */
public class SceneBattle extends AbstractScene {

    @Override
    public String getConfigFileName() {
        return Constants.CONFIG_NAME_BATTLE;
    }

    @Override
    public int id() {
        return INFINITE_BATTLE;
    }

}
