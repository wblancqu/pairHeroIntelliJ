package pairhero.intellij.listener;

import com.intellij.execution.testframework.AbstractTestProxy;
import com.intellij.execution.testframework.TestStatusListener;
import com.intellij.openapi.components.ServiceManager;
import pairhero.event.EventBus;
import pairhero.test.event.TestExecutionFinishedFactory;

public class TestExecutionListener extends TestStatusListener {

    private EventBus eventBus;
    private TestExecutionFinishedFactory factory;

    @Override
    public void testSuiteFinished(AbstractTestProxy testProxy) {
        eventBus().post(factory().create(testProxy));
    }

    private EventBus eventBus() {
        if (eventBus == null) {
            eventBus = ServiceManager.getService(EventBus.class);
        }
        return eventBus;
    }

    private TestExecutionFinishedFactory factory() {
        if (factory == null) {
            factory = ServiceManager.getService(TestExecutionFinishedFactory.class);
        }
        return factory;
    }
}
