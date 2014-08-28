package pairhero.client.view.player;

import javax.swing.*;

import java.awt.*;

import static javax.swing.BoxLayout.Y_AXIS;

public class PlayerConfigurationPanel extends JPanel {

    private final AvatarSelectionPanel avatarSelectionPanel;
    private final JTextField playerNameField;

    public PlayerConfigurationPanel(String name) {
        setLayout(new BoxLayout(this, Y_AXIS));
        JLabel title = new JLabel(name);
        title.setAlignmentX(CENTER_ALIGNMENT);
        add(title);

        avatarSelectionPanel = new AvatarSelectionPanel();
        add(avatarSelectionPanel);

        playerNameField = new JTextField();
        playerNameField.setPreferredSize(new Dimension(150, 15));
        add(playerNameField);
    }

    public Player getPlayer() {
        return new Player(playerNameField.getText(), avatarSelectionPanel.getAvatar());
    }
}
