package os.ransj.thundership.scenes;


/**
 * main entry
 * Created by ransj on 8/27/16.
 */
final class SceneMainEntry extends AbstractScene {
    private static final String JSON = "[{\"x\":1297,\"y\":599,\"value\":-1051649},{\"x\":282,\"y\":2327,\"value\":-4898511},{\"x\":50,\"y\":119,\"value\":-15132647},{\"x\":689,\"y\":846,\"value\":-16765589},{\"x\":1358,\"y\":435,\"value\":-14063179},{\"x\":1011,\"y\":2341,\"value\":-10860263},{\"x\":19,\"y\":565,\"value\":-16245694},{\"x\":278,\"y\":2293,\"value\":-38640},{\"x\":1209,\"y\":2121,\"value\":-9733756},{\"x\":1045,\"y\":1433,\"value\":-15714982},{\"x\":1261,\"y\":2102,\"value\":-11379349},{\"x\":219,\"y\":727,\"value\":-16762484},{\"x\":587,\"y\":713,\"value\":-16233043},{\"x\":764,\"y\":2170,\"value\":-13553350},{\"x\":184,\"y\":2151,\"value\":-4872655},{\"x\":1352,\"y\":373,\"value\":-4868427},{\"x\":1281,\"y\":1434,\"value\":-16242606},{\"x\":59,\"y\":2299,\"value\":-12959414},{\"x\":164,\"y\":196,\"value\":-16741649},{\"x\":1197,\"y\":568,\"value\":-16749148}]";


    @Override
    public String getPointJSONString() {
        return JSON;
    }

    @Override
    public int id() {
        return INFINITE_MAIN_ENTRY;
    }
}
