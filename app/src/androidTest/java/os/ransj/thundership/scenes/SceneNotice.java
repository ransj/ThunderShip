package os.ransj.thundership.scenes;

import os.ransj.thundership.Constants;

/**
 * Created by ransj on 9/6/16.
 */
class SceneNotice extends AbstractScene {
    @Override
    public String getConfigFileName() {
        return Constants.CONFIG_NAME_NOTICE;
    }

    @Override
    public int id() {
        return Scene.INFINITE_NOTICE;
    }
}
