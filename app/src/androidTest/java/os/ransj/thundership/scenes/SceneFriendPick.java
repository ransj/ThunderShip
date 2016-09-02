package os.ransj.thundership.scenes;


import os.ransj.thundership.Constants;

/**
 * 无尽模式选择机友
 * Created by ransj on 8/27/16.
 */
final class SceneFriendPick extends AbstractScene {

    @Override
    public String getConfigFileName() {
        return Constants.CONFIG_NAME_FRIEND_PICK;
    }

    @Override
    public int id() {
        return INFINITE_FRIEND_PICK;
    }
}
