package pairhero.client.view.game;

import com.intellij.openapi.ui.DialogWrapper;
import pairhero.client.view.player.StartDialog;
import pairhero.time.TimeFormatter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameView {

    private static final String BUTTON_LABEL_START = "Start";
    private static final String BUTTON_LABEL_STOP = "Stop";

    private final JFrame parent;
    private final ProgrammerView leftProgrammer,  rightProgrammer;
    private final JLabel scoreLabel;
    private final JLabel timerLabel;
    private final JButton startGameButton;

    public GameView(JFrame parent, JLabel scoreLabel, JLabel timerLabel, JButton startGameButton, ProgrammerView leftProgrammer, ProgrammerView rightProgrammer) {
        this.parent = parent;
        this.scoreLabel = scoreLabel;
        this.timerLabel = timerLabel;
        this.startGameButton = startGameButton;
        this.leftProgrammer = leftProgrammer;
        this.rightProgrammer = rightProgrammer;
    }

    public void addListener(final ActionListener startListener, final ActionListener stopListener) {
        startGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (startGameButton.getText().equals(BUTTON_LABEL_START)) {
                    if (choosePlayers()) {
                        startListener.actionPerformed(e);
                        startGameButton.setText(BUTTON_LABEL_STOP);
                    }
                } else {
                    stopListener.actionPerformed(e);
                }
            }
        });
    }

    private boolean choosePlayers() {
        StartDialog dialog = new StartDialog();
        dialog.show();

        if (dialog.getExitCode() == DialogWrapper.OK_EXIT_CODE) {
            dialog.buttonPressed(dialog.getExitCode());
            leftProgrammer.resetStats();
            rightProgrammer.resetStats();
            leftProgrammer.setPlayer(dialog.getPlayerOne());
            rightProgrammer.setPlayer(dialog.getPlayerTwo());
            return true;
        }

        return false;
    }

    public void leftSideDriving() {
        leftProgrammer.drive();
        rightProgrammer.observe();
    }

    public void onSwitchRole() {
        leftProgrammer.switchRole();
        rightProgrammer.switchRole();
    }

    public void onRefactoring() {

    }

    public void timeProgress(int seconds) {
        timerLabel.setText(TimeFormatter.formatTime(seconds));
        leftProgrammer.onTimeChange();
        rightProgrammer.onTimeChange();
    }

    public void onGameFinished() {
        // TODO: Dialog to show score???
        startGameButton.setText(BUTTON_LABEL_START);
    }

    public void updateScore(long score) {
        scoreLabel.setText(String.format("%d", score));
    }

    public JFrame getParent() {
        return parent;
    }

    public void enableRestart() {
        startGameButton.setText(BUTTON_LABEL_START);
    }
}
