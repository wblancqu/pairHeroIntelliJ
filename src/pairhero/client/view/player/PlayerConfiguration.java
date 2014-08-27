package pairhero.client.view.player;

import javax.swing.*;

import java.awt.*;

import static javax.swing.BoxLayout.Y_AXIS;

public class PlayerConfiguration extends JPanel {

    private final AvatarSelection avatarSelection;
    private final JTextField playerNameField;

    public PlayerConfiguration(String name) {
        setLayout(new BoxLayout(this, Y_AXIS));
        JLabel title = new JLabel(name);
        title.setAlignmentX(CENTER_ALIGNMENT);
        add(title);

        avatarSelection = new AvatarSelection();
        add(avatarSelection);

        playerNameField = new JTextField();
        playerNameField.setPreferredSize(new Dimension(150, 15));
        add(playerNameField);
    }

    public Player getPlayer() {
        return new Player(playerNameField.getText(), avatarSelection.getAvatar());
    }
}
