package pairhero.test.event.listener;

import com.google.common.base.Optional;
import pairhero.event.Event;
import pairhero.event.EventBus;
import pairhero.event.Listener;
import pairhero.test.ExecutedTest;
import pairhero.test.ExecutedTestStore;
import pairhero.test.event.TestBroken;
import pairhero.test.event.TestExecutionFinished;
import pairhero.test.event.TestResolved;

public class TestExecutionFinishedListener implements Listener<TestExecutionFinished> {

    private EventBus eventBus;
    private ExecutedTestStore store;

    public TestExecutionFinishedListener(EventBus eventBus, ExecutedTestStore store) {
        this.eventBus = eventBus;
        this.store = store;
    }

    @Override
    public boolean canHandle(Event event) {
        return event instanceof TestExecutionFinished;
    }

    @Override
    public void handle(TestExecutionFinished event) {
        for (ExecutedTest current : event.getTests()) {
            Optional<ExecutedTest> previous = store.previousExecution(current);
            if (previous.isPresent()) {
                handlePrevious(previous.get(), current);
            } else {
                handleCurrrent(current);
            }
        }
    }

    private void handlePrevious(ExecutedTest previous, ExecutedTest current) {
        if (hasBeenResolved(previous, current)) {
            previous.resolved();
            eventBus.post(new TestResolved());
        }
        if (hasBeenBroken(previous, current)) {
            previous.broken();
            eventBus.post(new TestBroken());
        }
    }

    private boolean hasBeenResolved(ExecutedTest previous, ExecutedTest current) {
        return previous.isBroken() && !current.isBroken();
    }

    private boolean hasBeenBroken(ExecutedTest previous, ExecutedTest current) {
        return !previous.isBroken() && current.isBroken();
    }

    private void handleCurrrent(ExecutedTest current) {
        store.save(current);
        if (current.isBroken()) {
            eventBus.post(new TestBroken());
        }
    }
}
