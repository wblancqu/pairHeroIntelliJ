package pairhero.client.view.player;

public enum IconSet {

    HAPPY_THREE_FRIENDS("players/happy/");

    private String path;

    IconSet(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
