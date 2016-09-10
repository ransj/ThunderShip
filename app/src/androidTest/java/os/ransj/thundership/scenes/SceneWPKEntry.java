package os.ransj.thundership.scenes;

import os.ransj.thundership.Constants;

/**
 * Created by ransj on 9/10/16.
 */
class SceneWPKEntry extends AbstractScene {
    @Override
    public String getConfigFileName() {
        return Constants.CONFIG_NAME_WPK_ENTRY;
    }

    @Override
    public int id() {
        return Scene.WPK_ENTRY;
    }
}
