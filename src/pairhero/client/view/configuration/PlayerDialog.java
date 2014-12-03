package pairhero.client.view.configuration;

import com.intellij.openapi.ui.DialogWrapper;
import org.jetbrains.annotations.Nullable;
import pairhero.client.model.Player;

import javax.swing.*;
import java.awt.*;

import static java.awt.BorderLayout.*;
import static javax.swing.Box.createRigidArea;

public class PlayerDialog extends DialogWrapper {

    private Player playerOne, playerTwo;

    private PlayerConfigurationPanel playerOneConfig;
    private PlayerConfigurationPanel playerTwoConfig;

    public PlayerDialog() {
        super(false);
        init();
    }

    @Nullable
	@Override
	protected JComponent createCenterPanel() {
        JPanel content = new JPanel(new BorderLayout());
        playerOneConfig = new PlayerConfigurationPanel("Player 1");
        content.add(playerOneConfig, WEST);
        addSeparator(content);
        playerTwoConfig = new PlayerConfigurationPanel("Player 2");
        content.add(playerTwoConfig, EAST);
		return content;
	}

    private void addSeparator(JPanel content) {
        content.add(createRigidArea(new Dimension(10, 100)), CENTER);
    }

    public void buttonPressed(int dialogExitCode) {
		if (dialogExitCode == OK_EXIT_CODE) {
			playerOne = playerOneConfig.getPlayer();
			playerTwo = playerTwoConfig.getPlayer();
		}
	}

    public Player getPlayerOne() {
        return playerOne;
    }

    public Player getPlayerTwo() {
        return playerTwo;
    }
}
