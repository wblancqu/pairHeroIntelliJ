package pairhero.client.presenter;

import pairhero.client.action.Actions;
import pairhero.client.view.PairHeroView;
import pairhero.client.view.PopUpNotification;
import pairhero.client.view.game.model.Scoreboard;
import pairhero.client.view.player.Player;
import pairhero.client.view.player.Role;
import pairhero.client.view.player.StartDialog;
import pairhero.time.Timer;

import static com.intellij.openapi.ui.DialogWrapper.OK_EXIT_CODE;
import static pairhero.client.view.player.Role.DRIVING;
import static pairhero.client.view.player.Role.OBSERVING;
import static pairhero.client.view.util.Icons.anIcon;

public class Presenter {

    private Timer timer;
    private PairHeroView view;
    private Actions actions;
    private Player playerOne, playerTwo;

    private Scoreboard scoreboard = new Scoreboard();
    private PopUpNotification popUpNotification;
    private int messageDelayCounter;
    private static final int _25_MINS = 1500;
    private int countdownInSeconds = _25_MINS;

    public void initialise(PairHeroView view, Actions actions) {
        this.view = view;
        this.actions = actions;
    }

    public void onResolvedTest() {
        if (isOnGoing()) {
            onGreenTest();
        }
    }

    public void onBrokenTest() {
        if (isOnGoing()) {
            onSwitchRole();
        }
    }

    public void start() {
        if (noPlayers()) {
            if (choosePlayers()) {
                doStart();
            }
        } else {
            doStart();
        }
    }

    private void doStart() {
        if (!isOnGoing()) {
            timer = new Timer();
            timer.start(this, countdownInSeconds);
            /*playerOne.setRole(DRIVING);
            playerTwo.setRole(OBSERVING);
            view.updateRoles();*/
            actions.running();
        }
    }

    public void pause() {
        countdownInSeconds = timer.getCountDownInSeconds();
        timer.stop();
        actions.paused();
    }

    public void stop() {
        pause();
        doReset();
    }

    private void finish() {
        pause();
        countdownInSeconds = _25_MINS;
        view.resetTime();
    }

    public void reset() {
        stop();
        choosePlayers();
        start();
    }

    private void doReset() {
        countdownInSeconds = _25_MINS;
        scoreboard.resetStats();
        view.updateScore(scoreboard.getScore());
        view.resetTime();
        actions.stopped();
    }

    public void onTimeChange(int seconds) {
        scoreboard.timeProgress();
        view.onTimeChange(seconds);
        updateMessageToDefault();
        if (seconds <= 0) {
            finish();
            // this is done with actions: enable/disable buttons view.onGameFinished();
            return;
        }
    }

    public void onSwitchRole() {
        doSwitch();

        showNotification(getSwitchRoleImage());
        scoreboard.addSwitch();
        showMessageAndUpdateScore(getSwitchRoleImage(), scoreboard.getScore());
    }

    public void forceSwitch() {
        doSwitch();
        scoreboard.addForceSwitch();
        showMessageAndUpdateScore("", scoreboard.getScore());
    }

    private void doSwitch() {
        playerOne.switchRole();
        playerTwo.switchRole();
        view.updateRoles();
    }

    public void onRefactoring() {
        //scoreboard.addRefactoring();
        //view.onRefactoring();

        //showMessageAndUpdateScore("refactor", scoreboard.getScore());
    }

    private void onGreenTest() {
        scoreboard.addGreenTest();

        showMessageAndUpdateScore("green", scoreboard.getScore());
    }

    private boolean isOnGoing() {
        return timer != null && timer.isRunning();
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
        popUpNotification = new PopUpNotification(view.getFrame(), anIcon(icon));
        popUpNotification.setVisible(true);
    }

    private void removeNotification() {
        if (popUpNotification != null && popUpNotification.isVisible()) {
            popUpNotification.dispose();
        }
    }

    private boolean noPlayers() {
        return playerOne == null || playerTwo == null;
    }

    private boolean choosePlayers() {
        StartDialog dialog = new StartDialog();
        dialog.show();

        if (dialog.getExitCode() == OK_EXIT_CODE) {
            dialog.buttonPressed(dialog.getExitCode());
            playerOne = dialog.getPlayerOne();
            playerTwo = dialog.getPlayerTwo();
            view.setPlayerOne(playerOne);
            view.setPlayerTwo(playerTwo);
            playerOne.setRole(DRIVING);
            playerTwo.setRole(OBSERVING);
            view.updateRoles();
            return true;
        }

        return false;
    }
}
