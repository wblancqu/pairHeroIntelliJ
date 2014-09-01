package pairhero.client.view.game;

import pairhero.client.view.player.Player;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import java.awt.*;

import static java.awt.Color.BLACK;
import static javax.swing.BorderFactory.createLineBorder;
import static javax.swing.BorderFactory.createTitledBorder;
import static javax.swing.Box.createRigidArea;
import static javax.swing.BoxLayout.X_AXIS;
import static javax.swing.BoxLayout.Y_AXIS;
import static javax.swing.border.TitledBorder.CENTER;
import static pairhero.client.view.util.Icons.anIcon;
import static pairhero.time.TimeFormatter.formatTime;

public class PlayerView extends JPanel {

    private final JLabel role = new JLabel();
    private final JLabel avatar = new JLabel();
    private final JLabel time = new JLabel();
    private final TitledBorder titledBorder;

    private Player player;
    private int currentTime;

    public PlayerView() {
        titledBorder = createTitledBorder(createLineBorder(BLACK), "");
        titledBorder.setTitleJustification(CENTER);
        setLayout(new BoxLayout(this, Y_AXIS));
        add(center(avatar));
        add(playerInfo());
    }

    private JComponent playerInfo() {
        JPanel playerInfo = new JPanel();
        BoxLayout mgr = new BoxLayout(playerInfo, X_AXIS);
        playerInfo.setLayout(mgr);
        playerInfo.setOpaque(false);
        playerInfo.add(center(role));
        playerInfo.add(createRigidArea(new Dimension(10, 0)));
        playerInfo.add(center(time));
        return playerInfo;
    }

    public void setPlayer(Player player) {
        this.player = player;
        titledBorder.setTitle(player.getName());
        avatar.setIcon(player.getAvatar());
        setBorder(titledBorder);
        resetTime();
        setMinimumSize(new Dimension(player.getAvatar().getIconWidth() + 8, player.getAvatar().getIconHeight() + 20));
        setPreferredSize(new Dimension(player.getAvatar().getIconWidth() + 8, player.getAvatar().getIconHeight() + 20));
    }

    public void onTimeChange(int seconds) {
        if (player.isDriving()) {
            updateTime();
        }
    }

    public void updateRole() {
        if (player.isDriving()) {
            role.setIcon(anIcon("badge/driving"));
            setBackground(new Color(104, 220, 100));
        } else {
            role.setIcon(anIcon("badge/observing"));
            setBackground(new Color(243, 84, 72));
        }
    }

    private void updateTime() {
        currentTime++;
        time.setText(formatTime(currentTime));
    }

    private JLabel center(JLabel toCenter) {
        toCenter.setAlignmentX(CENTER_ALIGNMENT);
        return toCenter;
    }

    public void resetTime() {
        currentTime = -1;
        updateTime();
    }
}
