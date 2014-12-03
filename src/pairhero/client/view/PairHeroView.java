package pairhero.client.view;

import com.intellij.openapi.actionSystem.ActionManager;
import com.intellij.openapi.actionSystem.ActionToolbar;
import pairhero.client.action.*;
import pairhero.client.presenter.Presenter;
import pairhero.client.view.game.PlayerView;
import pairhero.client.view.game.StatisticsView;
import pairhero.client.model.Player;

import javax.swing.*;
import java.awt.*;

import static java.awt.BorderLayout.*;
import static java.awt.Color.WHITE;
import static pairhero.time.TimeFormatter.formatTime;

public class PairHeroView extends JPanel {

    public static final StatisticsView statistics = new StatisticsView();
    private final Presenter presenter;
    private final JFrame parent;
    private PlayerView playerOne, playerTwo;

    public PairHeroView(JFrame parent, Presenter presenter) {
        super(new BorderLayout());
        this.parent = parent;
        this.presenter = presenter;
        add(toolbar(), NORTH);
        add(game(), CENTER);
        setBackground(WHITE);
    }

    public void setPlayerOne(Player player) {
        playerOne.setPlayer(player);
    }

    public void setPlayerTwo(Player player) {
        playerTwo.setPlayer(player);
    }

    public void updateRoles() {
        playerOne.updateRole();
        playerTwo.updateRole();
    }

    public void onTimeChange(int seconds) {
        playerOne.onTimeChange(seconds);
        playerTwo.onTimeChange(seconds);
        statistics.setTime(formatTime(seconds));
    }

    public void resetTime() {
        playerOne.resetTime();
        playerTwo.resetTime();
        statistics.setTime(formatTime(0));
    }

    public void updateScore(long score) {
        statistics.setScore(score);
    }

    public JFrame getFrame() {
        return parent;
    }

    private JComponent game() {
        playerOne = new PlayerView();
        playerTwo = new PlayerView();
        JPanel game = new JPanel(new BorderLayout());
        game.setOpaque(false);
        game.add(playerOne, WEST);
        game.add(playerTwo, EAST);
        game.add(statistics, SOUTH);
        return game;
    }

    private JComponent toolbar() {
        Actions actions = new Actions();
        actions.addStartAction(new StartAction(presenter));
        actions.addPauseAction(new PauseAction(presenter));
        actions.addStopAction(new StopAction(presenter));
        actions.addSeparator();
        actions.addForceSwitchAction(new ForceSwitchAction(presenter));
        actions.addSeparator();
        actions.addResetAction(new ResetAction(presenter));
        presenter.initialise(this, actions);

        ActionToolbar actionToolbar = ActionManager.getInstance().createActionToolbar("PairHero Toolbar", actions, true);
        actionToolbar.getComponent().setEnabled(true);
        return actionToolbar.getComponent();
    }
}
