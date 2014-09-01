package pairhero.client.view.game;

import com.intellij.openapi.ui.DialogWrapper;
import pairhero.client.view.player.StartDialog;
import pairhero.client.view.util.Icons;
import pairhero.time.TimeFormatter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static pairhero.client.view.util.Icons.anIcon;

public class GameView {

    /*public static final ImageIcon START_ICON = anIcon("button/start");
    public static final ImageIcon STOP_ICON = anIcon("button/stop");

    private final JFrame parent;
    private final ProgrammerView leftProgrammer,  rightProgrammer;
    private final JLabel scoreLabel;
    private final JLabel timerLabel;
    //private final Controls controls;

    public GameView(JFrame parent, JLabel scoreLabel, JLabel timerLabel, ProgrammerView leftProgrammer, ProgrammerView rightProgrammer) {
        this.parent = parent;
        this.scoreLabel = scoreLabel;
        this.timerLabel = timerLabel;
        //this.controls = controls;
        this.leftProgrammer = leftProgrammer;
        this.rightProgrammer = rightProgrammer;
        enableRestart();
    }

    public void addListener(final ActionListener startListener, final ActionListener stopListener) {
       *//* final JButton startGameButton = controls.getStartStop();
        startGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (startGameButton.getIcon().equals(START_ICON)) {
                    if (choosePlayers()) {
                        startListener.actionPerformed(e);
                        enableStop();
                    }
                } else {
                    stopListener.actionPerformed(e);
                }
            }
        });*//*
    }

    private void enableStop() {
        *//*controls.getStartStop().setIcon(STOP_ICON);
        controls.getPause().setEnabled(true);*//*
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
        enableRestart();
    }

    public void updateScore(long score) {
        scoreLabel.setText(String.format("%d", score));
    }

    public JFrame getParent() {
        return parent;
    }

    public void enableRestart() {
       // controls.getStartStop().setIcon(START_ICON);
//        controls.getPause().setEnabled(false);
    }*/
}
