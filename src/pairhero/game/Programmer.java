package pairhero.game;

import java.awt.*;
import javax.swing.*;

import pairhero.client.view.player.Player;
import pairhero.time.TimeFormatter;

import static pairhero.client.view.util.Icons.anIcon;

public class Programmer {

	private static Color DRIVER_COLOR = new Color(58, 170, 53);
	private static Color OBSERVER_COLOR = new Color(218, 218, 218);

	private JLabel nameLabel;
	private JLabel roleLabel;
	private JLabel timeAtKeyboardLabel;
	private JLabel avatar;
	private Role currentRole;
	private int timeAtKeyboard;
	private JPanel panel;
    private Player player;

    enum Role {
		Driving, Observing
	}

	public Programmer(JPanel programmerPanel, JLabel nameLabel, JLabel avatar, JLabel roleLabel,
			JLabel timeAtKeyboardLabel) {
		this.panel = programmerPanel;
		this.nameLabel = nameLabel;
		this.avatar = avatar;
		this.roleLabel = roleLabel;
		this.timeAtKeyboardLabel = timeAtKeyboardLabel;
	}

	public void drive() {
		currentRole = Role.Driving;
		updateRole(currentRole);
	}

	public void observe() {
		currentRole = Role.Observing;
		updateRole(currentRole);
	}

	public void switchRole() {
		if (currentRole.equals(Role.Driving)) {
			observe();
		} else {
			drive();
		}
	}

	public void onTimeChange() {
		if (Role.Driving.equals(currentRole)) {
			timeAtKeyboard++;
			updateTimeAtKeyboard(timeAtKeyboard);
		}
	}

	void updateRole(final Role role) {
		if (role.equals(Role.Driving)) {
			roleLabel.setIcon(anIcon("green-keyboard"));
			panel.setBackground(DRIVER_COLOR);
		} else {
			roleLabel.setIcon(anIcon("red-keyboard"));
			panel.setBackground(OBSERVER_COLOR);
		}
	}

	void updateTimeAtKeyboard(final int seconds) {
		timeAtKeyboardLabel.setText(TimeFormatter.formatTime(seconds));
	}

	public void resetStats() {
		timeAtKeyboard = 0;
		updateTimeAtKeyboard(timeAtKeyboard);
	}

    public void setPlayer(Player player) {
        this.player = player;
        setName(player.getName());
        setAvatar(player.getAvatar());
    }

    private void setName(String playerName) {
        nameLabel.setText(playerName);
    }

	private void setAvatar(final ImageIcon avatarImage) {
		avatar.setIcon(avatarImage);
	}
}
