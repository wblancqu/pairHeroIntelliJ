package pairhero.client.view.player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static javax.swing.BoxLayout.X_AXIS;
import static pairhero.client.view.player.IconSet.HAPPY_THREE_FRIENDS;
import static pairhero.client.view.util.Icons.PATH;
import static pairhero.client.view.util.Icons.anIcon;

public class AvatarSelectionPanel extends JPanel {

    private List<ImageIcon> players = new ArrayList<ImageIcon>();
    private ImageIcon selectedAvatar;
    private JLabel selectedPlayerLabel;

    public AvatarSelectionPanel() {
        build();
        updatePlayerSet(HAPPY_THREE_FRIENDS);
    }

    private void build() {
        setLayout(new BoxLayout(this, X_AXIS));
        JButton previous = new JButton("<");
        previous.setPreferredSize(new Dimension(50, 50));
        previous.addActionListener(previousListener);
        add(previous);
        selectedPlayerLabel = new JLabel();
        add(selectedPlayerLabel);
        JButton next = new JButton(">");
        next.setPreferredSize(new Dimension(50, 50));
        next.addActionListener(nextListener);
        add(next);
    }

    private void updatePlayerSet(IconSet iconSet) {
        loadPlayers(iconSet);
        newSelectedPlayer(players);
    }

    private void loadPlayers(IconSet iconSet) {
        String folder = AvatarSelectionPanel.class.getResource(PATH + iconSet.getPath()).getPath();
        File[] players = new File(folder).listFiles();
        this.players.clear();
        for (File player : players) {
            this.players.add(anIcon(iconSet.getPath() + player.getName()));
        }
    }

    private ActionListener previousListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            selectPrevious();
        }
    };

    private ActionListener nextListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            selectNext();
        }
    };

    private void selectPrevious() {
        int i = players.indexOf(selectedAvatar);
        if (i == 0) {
            newSelectedPlayer(players.get(players.size() - 1));
        } else {
            newSelectedPlayer(players.get(i - 1));
        }
    }

    private void selectNext() {
        int i = players.indexOf(selectedAvatar);
        if (i + 1 == players.size()) {
            newSelectedPlayer(players.get(0));
        } else {
            newSelectedPlayer(players.get(i + 1));
        }
    }

    private void newSelectedPlayer(ImageIcon avatar) {
        selectedAvatar = avatar;
        selectedPlayerLabel.setIcon(selectedAvatar);
    }

    private void newSelectedPlayer(List<ImageIcon> players) {
        int randomIndex = new Random().nextInt(players.size() - 1);
        newSelectedPlayer(players.get(randomIndex));
    }

    public ImageIcon getAvatar() {
        return selectedAvatar;
    }
}
