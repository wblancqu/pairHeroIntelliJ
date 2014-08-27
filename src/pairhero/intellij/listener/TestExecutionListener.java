package pairhero.intellij.listener;

import com.intellij.execution.testframework.AbstractTestProxy;
import com.intellij.execution.testframework.TestStatusListener;
import pairhero.event.EventBus;
import pairhero.test.event.TestExecutionFinishedFactory;

public class TestExecutionListener extends TestStatusListener {

    private EventBus eventBus;
    private TestExecutionFinishedFactory factory;

    public TestExecutionListener(EventBus eventBus, TestExecutionFinishedFactory factory) {
        this.eventBus = eventBus;
        this.factory = factory;
    }

    @Override
    public void testSuiteFinished(AbstractTestProxy testProxy) {
        eventBus.post(factory.create(testProxy));
    }
}
