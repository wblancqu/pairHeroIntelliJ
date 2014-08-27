package pairhero.test.event.listener;

import com.intellij.openapi.components.ServiceManager;
import pairhero.client.presenter.Presenter;
import pairhero.event.Event;
import pairhero.event.Listener;
import pairhero.test.event.TestBroken;

public class TestBrokenListener implements Listener<TestBroken> {

    private Presenter presenter;

    @Override
    public boolean canHandle(Event event) {
        return event instanceof TestBroken;
    }

    @Override
    public void handle(TestBroken event) {
        presenter().onBrokenTest();
    }

    private Presenter presenter() {
        if (presenter == null) {
            presenter = ServiceManager.getService(Presenter.class);
        }
        return presenter;
    }
}
