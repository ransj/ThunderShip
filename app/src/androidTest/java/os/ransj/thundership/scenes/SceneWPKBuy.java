package os.ransj.thundership.scenes;

import os.ransj.thundership.Constants;

/**
 * Created by ransj on 9/10/16.
 */
class SceneWPKBuy extends AbstractScene {
    @Override
    public String getConfigFileName() {
        return Constants.CONFIG_NAME_WPK_BUY;
    }

    @Override
    public int id() {
        return Scene.WPK_BUY;
    }
}
