package pairhero.client;

import com.intellij.openapi.extensions.Extensions;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowEP;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import pairhero.client.presenter.Presenter;
import pairhero.client.view.game.GameView;
import pairhero.client.view.game.ProgrammerView;

import javax.swing.*;

import static com.intellij.openapi.components.ServiceManager.getService;

public class PairHeroToolWindowFactory implements ToolWindowFactory {

	private JButton startGameButton;
	private JPanel myToolWindowContent;
	private JPanel leftProgrammerPanel;
	private JPanel rightProgrammerPanel;
	private JPanel scorePanel;
	private JLabel leftPlayerName;
	private JLabel rightPlayerName;
	private JLabel leftAvatar;
	private JLabel rightAvatar;
	private JLabel leftRoleLabel;
	private JLabel rightRoleLabel;
	private JLabel rightTimeAtKeyboardLabel;
	private JLabel leftTimeAtKeyboardLabel;
	private JPanel line1;
	private JPanel line2;
	private JPanel line3;
	private JLabel scoreLabel;
	private JLabel timerLabel;
	private JLabel messageLabel;

	private ProgrammerView leftProgrammer;
	private ProgrammerView rightProgrammer;
    private final Presenter presenter;

    public PairHeroToolWindowFactory() {
        presenter = getService(Presenter.class);
	}

	public static PairHeroToolWindowFactory getToolWindowFactory() {
		try {
			ToolWindowEP[] toolWindowExtensionPoints = Extensions.getExtensions(ToolWindowEP.EP_NAME);
			for (final ToolWindowEP toolWindowEP : toolWindowExtensionPoints) {
				ToolWindowFactory toolWindowFactory = toolWindowEP.getToolWindowFactory();
				if (toolWindowFactory instanceof PairHeroToolWindowFactory) {
					return (PairHeroToolWindowFactory) toolWindowFactory;
				}
			}
		} catch (IllegalArgumentException ex) {
			System.out.println(ex.getMessage());
		}
		return null;
	}

	public void createToolWindowContent(Project project, ToolWindow toolWindow) {
		ContentFactory contentFactory = ContentFactory.SERVICE.getInstance();
		Content content = contentFactory.createContent(myToolWindowContent, "", false);
		toolWindow.getContentManager().addContent(content);
        JFrame parent = (JFrame)toolWindow.getComponent().getRootPane().getParent().getParent();

        leftProgrammer = new ProgrammerView(leftProgrammerPanel, leftPlayerName, leftAvatar, leftRoleLabel,
				leftTimeAtKeyboardLabel);
		rightProgrammer = new ProgrammerView(rightProgrammerPanel, rightPlayerName, rightAvatar, rightRoleLabel,
				rightTimeAtKeyboardLabel);

        presenter.setView(new GameView(parent, scoreLabel, timerLabel, startGameButton, leftProgrammer, rightProgrammer));
	}
}
