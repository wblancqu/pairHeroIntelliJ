package pairhero.client.view.player;

import javax.swing.*;

public class Player {

    private String name;
    private ImageIcon avatar;

    public Player(String name, ImageIcon avatar) {
        this.name = name;
        this.avatar = avatar;
    }

    public String getName() {
        return name;
    }

    public ImageIcon getAvatar() {
        return avatar;
    }
}
