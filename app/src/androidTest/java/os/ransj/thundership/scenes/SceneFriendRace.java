package os.ransj.thundership.scenes;


import os.ransj.thundership.Constants;

/**
 * 无尽模式好友竞赛
 * Created by ransj on 8/27/16.
 */
final class SceneFriendRace extends AbstractScene {

    @Override
    public String getConfigFileName() {
        return Constants.CONFIG_NAME_FRIEND_RACE;
    }

    @Override
    public int id() {
        return INFINITE_FRIEND_RACE;
    }
}
