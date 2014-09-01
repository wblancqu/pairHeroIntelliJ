package pairhero.client.action;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import pairhero.client.presenter.Presenter;
import pairhero.client.view.util.Icons;

import javax.swing.*;

public class ResetAction extends AnAction {

    private static final ImageIcon ICON = Icons.anIcon("button/reset");
    private final Presenter presenter;

    public ResetAction(Presenter presenter) {
        super("", "Reset Pomodora & Players", ICON);
        this.presenter = presenter;
    }

    @Override
    public void actionPerformed(AnActionEvent anActionEvent) {
        presenter.reset();
    }
}
