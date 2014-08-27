package pairhero.intellij.listener;

import com.intellij.openapi.project.Project;
import com.intellij.usageView.UsageInfo;
import pairhero.event.EventBus;
import pairhero.refactoring.event.RefactoringPerformed;

public class RefactorListener implements com.intellij.refactoring.RefactoringHelper {

    private EventBus eventBus;

    public RefactorListener(EventBus eventBus) {
        this.eventBus = eventBus;
    }

	@Override
	public Object prepareOperation(UsageInfo[] usageInfos) {
		return null;
	}

	@Override
	public void performOperation(Project project, Object operationData) {
        eventBus.post(new RefactoringPerformed());
	}
}
