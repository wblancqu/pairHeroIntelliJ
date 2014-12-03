package pairhero.client.model;

import javax.swing.*;

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
        return role == Role.DRIVING;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void switchRole() {
        if(isDriving()) {
            setRole(Role.OBSERVING);
        } else {
            setRole(Role.DRIVING);
        }
    }
}
