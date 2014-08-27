package pairhero.test.event.listener;

import pairhero.client.presenter.Presenter;
import pairhero.event.Event;
import pairhero.event.Listener;
import pairhero.test.event.TestBroken;

public class TestBrokenListener implements Listener<TestBroken> {

    private Presenter presenter;

    public TestBrokenListener(Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public boolean canHandle(Event event) {
        return event instanceof TestBroken;
    }

    @Override
    public void handle(TestBroken event) {
        presenter.onBrokenTest();
    }
}
