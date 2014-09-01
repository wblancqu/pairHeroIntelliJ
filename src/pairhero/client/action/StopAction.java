package pairhero.client.action;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import pairhero.client.presenter.Presenter;
import pairhero.client.view.util.Icons;

import javax.swing.*;

public class StopAction extends AnAction {

    private static final ImageIcon ICON = Icons.anIcon("button/stop");
    private final Presenter presenter;

    public StopAction(Presenter presenter) {
        super("", "Stop Pomodoro", ICON);
        this.presenter = presenter;
    }

    @Override
    public void actionPerformed(AnActionEvent anActionEvent) {
        presenter.stop();
    }
}
