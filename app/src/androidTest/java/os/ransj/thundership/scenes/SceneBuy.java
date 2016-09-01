package os.ransj.thundership.scenes;


/**
 * 无尽模式购买道具
 * Created by ransj on 8/27/16.
 */
final class SceneBuy extends AbstractScene {
    private static final String JSON = "[{\"x\":612,\"y\":2065,\"value\":-16247775},{\"x\":259,\"y\":661,\"value\":-16734473},{\"x\":138,\"y\":1272,\"value\":-16762484},{\"x\":1092,\"y\":24,\"value\":-1644570},{\"x\":793,\"y\":1167,\"value\":-16228956},{\"x\":577,\"y\":1406,\"value\":-16236165},{\"x\":851,\"y\":1147,\"value\":-16228964},{\"x\":395,\"y\":2335,\"value\":-10261141},{\"x\":1126,\"y\":1066,\"value\":-9180342},{\"x\":715,\"y\":1629,\"value\":-16765589},{\"x\":1260,\"y\":2366,\"value\":-15129542},{\"x\":120,\"y\":680,\"value\":-16769974},{\"x\":1201,\"y\":957,\"value\":-16758396},{\"x\":961,\"y\":788,\"value\":-15705741},{\"x\":658,\"y\":1318,\"value\":-16766886},{\"x\":234,\"y\":690,\"value\":-15685087},{\"x\":224,\"y\":1242,\"value\":-14576963},{\"x\":1235,\"y\":1064,\"value\":-12393703},{\"x\":259,\"y\":885,\"value\":-4879740},{\"x\":660,\"y\":1125,\"value\":-15694139},{\"x\":315,\"y\":2224,\"value\":-9207412},{\"x\":677,\"y\":669,\"value\":-16766886},{\"x\":1410,\"y\":1156,\"value\":-16246726},{\"x\":633,\"y\":37,\"value\":-16747116},{\"x\":1230,\"y\":60,\"value\":-15723504},{\"x\":1419,\"y\":1828,\"value\":-15720382},{\"x\":754,\"y\":2039,\"value\":-16236140},{\"x\":369,\"y\":319,\"value\":-15128502},{\"x\":292,\"y\":1217,\"value\":-15112787},{\"x\":1229,\"y\":639,\"value\":-12922343}]";

    @Override
    public String getPointJSONString() {
        return JSON;
    }

    @Override
    public int id() {
        return INFINITE_BUY;
    }
}