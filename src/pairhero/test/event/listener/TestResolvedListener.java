package pairhero.test.event.listener;

import com.intellij.openapi.components.ServiceManager;
import pairhero.client.presenter.Presenter;
import pairhero.event.Event;
import pairhero.event.Listener;
import pairhero.test.event.TestResolved;

public class TestResolvedListener implements Listener<TestResolved> {

    private Presenter presenter;

    @Override
    public boolean canHandle(Event event) {
        return event instanceof TestResolved;
    }

    @Override
    public void handle(TestResolved event) {
        presenter().onResolvedTest();
    }

    private Presenter presenter() {
        if (presenter == null) {
            presenter = ServiceManager.getService(Presenter.class);
        }
        return presenter;
    }
}
