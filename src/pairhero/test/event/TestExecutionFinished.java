package pairhero.test.event;

import pairhero.event.Event;
import pairhero.test.ExecutedTest;

import java.util.List;

public class TestExecutionFinished implements Event {

    private final List<ExecutedTest> tests;

    public TestExecutionFinished(List<ExecutedTest> tests) {
        this.tests = tests;
    }

    public List<ExecutedTest> getTests() {
        return tests;
    }
}
