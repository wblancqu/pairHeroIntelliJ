package pairhero.test;

import com.google.common.base.Optional;
import com.google.common.base.Predicate;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.collect.Iterables.tryFind;

public class ExecutedTestStore {

    private List<ExecutedTest> history = new ArrayList<ExecutedTest>();

    public void save(ExecutedTest test) {
        if (!previousExecution(test).isPresent()) {
            history.add(test);
        }
    }

    public Optional<ExecutedTest> previousExecution(final ExecutedTest test) {
        return tryFind(history, new Predicate<ExecutedTest>() {
            @Override
            public boolean apply(ExecutedTest previous) {
                return previous.getId().equals(test.getId());
            }
        });
    }
}
