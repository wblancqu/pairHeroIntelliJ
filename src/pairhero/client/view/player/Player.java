package pairhero.client.view.player;

import javax.swing.*;

import static pairhero.client.view.player.Role.DRIVING;
import static pairhero.client.view.player.Role.OBSERVING;

public class Player {

    private String name;
    private ImageIcon avatar;
    private Role role;

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

    public boolean isDriving() {
        return role == DRIVING;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void switchRole() {
        if(isDriving()) {
            setRole(OBSERVING);
        } else {
            setRole(DRIVING);
        }
    }
}
