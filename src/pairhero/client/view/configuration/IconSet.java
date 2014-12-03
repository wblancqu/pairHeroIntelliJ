package pairhero.client.view.configuration;

public enum IconSet {

    HAPPY_TREE_FRIENDS("players/happy/");

    private String path;

    IconSet(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
