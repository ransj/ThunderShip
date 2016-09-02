package os.ransj.thundership.scenes;


import os.ransj.thundership.Constants;

/**
 * main entry
 * Created by ransj on 8/27/16.
 */
final class SceneMainEntry extends AbstractScene {


    @Override
    public String getConfigFileName() {
        return Constants.CONFIG_NAME_MAIN_ENTRY;
    }

    @Override
    public int id() {
        return INFINITE_MAIN_ENTRY;
    }
}
