package pairhero.client.presenter;

import pairhero.client.view.PopUpNotification;
import pairhero.client.view.game.GameView;
import pairhero.client.view.game.model.Scoreboard;
import pairhero.time.Timer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static pairhero.client.view.util.Icons.anIcon;

public class Presenter {

    private Timer timer;
    private GameView view;
    private Scoreboard scoreboard = new Scoreboard();
    private PopUpNotification popUpNotification;
    private int messageDelayCounter;

    public void onResolvedTest() {
        if(isOnGoing()) {
            onGreenTest();
        }
    }

    public void onBrokenTest() {
        if(isOnGoing()) {
            onSwitchRole();
        }
    }

    private void start() {
        timer = new Timer();
        timer.start(this);
        view.leftSideDriving();
}

    private void stop() {
        timer.stop();
        view.enableRestart();
    }

    private void restart() {

    }

    public void onTimeChange(int seconds) {
        scoreboard.timeProgress();
        view.timeProgress(seconds);
        updateMessageToDefault();
        if (seconds <= 0) {
            stop();
            view.onGameFinished();
            return;
        }
    }

    public void onSwitchRole() {
        showNotification(getSwitchRoleImage());
        view.onSwitchRole();
        scoreboard.addSwitch();

        showMessageAndUpdateScore(getSwitchRoleImage(), scoreboard.getScore());
    }

    public void onRefactoring() {
        scoreboard.addRefactoring();
        view.onRefactoring();

        showMessageAndUpdateScore("refactor", scoreboard.getScore());
    }

    private void onGreenTest() {
        scoreboard.addGreenTest();

        showMessageAndUpdateScore("green", scoreboard.getScore());
    }

    public void setView(GameView view) {
        this.view = view;
        this.view.addListener(startGame(), stopGame());
    }

    private boolean isOnGoing() {
        return timer != null && timer.isRunning();
    }

    private ActionListener startGame() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reset();
                start();
            }
        };
    }

    private ActionListener stopGame() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stop();
            }
        };
    }

    private void showMessageAndUpdateScore(String icon, long score) {
        view.updateScore(score);
        messageDelayCounter = 3;
    }

    private String getSwitchRoleImage() {
        int multiplier = scoreboard.getLastMultiplier();
        if (multiplier == Scoreboard.MULTIPLIER_4X) {
            return "switch-4x-noborder";
        } else if (multiplier == Scoreboard.MULTIPLIER_2X) {
            return "switch-2x-noborder";
        } else {
            return "switch-noborder";
        }
    }

    private void updateMessageToDefault() {
        if (messageDelayCounter < 0) {
            removeNotification();
        }
        messageDelayCounter--;
    }

    private void showNotification(String icon) {
        popUpNotification = new PopUpNotification(view.getParent(), anIcon(icon));
        popUpNotification.setVisible(true);
    }

    private void removeNotification() {
        if(popUpNotification != null && popUpNotification.isVisible()) {
            popUpNotification.dispose();
        }
    }

    private void reset() {
        scoreboard.resetStats();
        view.updateScore(scoreboard.getScore());
    }
}
