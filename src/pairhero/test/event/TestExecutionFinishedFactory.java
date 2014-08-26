package pairhero.test.event;

import com.intellij.execution.testframework.AbstractTestProxy;
import pairhero.test.ExecutedTest;

import java.util.List;

import static com.google.common.collect.Lists.asList;
import static com.google.common.collect.Lists.newArrayList;
import static pairhero.test.TestClassName.aTestClass;
import static pairhero.test.TestMethodName.aTestMethod;

public class TestExecutionFinishedFactory {

    public TestExecutionFinished create(AbstractTestProxy testProxy) {
        final List<ExecutedTest> tests = newArrayList();
        parseTests(newArrayList(testProxy), tests);
        return new TestExecutionFinished(tests);
    }

    private void parseTests(List<? extends AbstractTestProxy> testProxies, List<ExecutedTest> tests) {
        for (AbstractTestProxy testProxy : testProxies) {
            if (isTestMethod(testProxy)) {
                tests.add(new ExecutedTest(aTestClass(testProxy.getParent().getName()), aTestMethod(testProxy.getName()), testProxy.isPassed()));
            } else {
                parseTests(testProxy.getChildren(), tests);
            }
        }
    }

    private boolean isTestMethod(AbstractTestProxy testProxy) {
        return testProxy.isLeaf() == true;
    }
}
