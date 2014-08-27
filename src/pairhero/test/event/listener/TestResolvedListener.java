package pairhero.test.event.listener;

import pairhero.client.presenter.Presenter;
import pairhero.event.Event;
import pairhero.event.Listener;
import pairhero.test.event.TestResolved;

public class TestResolvedListener implements Listener<TestResolved> {

    private Presenter presenter;

    public TestResolvedListener(Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public boolean canHandle(Event event) {
        return event instanceof TestResolved;
    }

    @Override
    public void handle(TestResolved event) {
        presenter.onResolvedTest();
    }
}
