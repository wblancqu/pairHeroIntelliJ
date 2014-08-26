package pairhero.test;

import static pairhero.test.TestId.aTestId;

public class ExecutedTest {

    private final TestClassName className;
    private final TestMethodName methodName;
    private boolean passed;

    public ExecutedTest(TestClassName className, TestMethodName methodName, boolean passed) {
        this.className = className;
        this.methodName = methodName;
        this.passed = passed;
    }

    public TestId getId() {
        return aTestId(className, methodName);
    }

    public TestClassName getClassName() {
        return className;
    }

    public TestMethodName getMethodName() {
        return methodName;
    }

    public boolean isBroken() {
        return !passed;
    }

    public void resolved() {
        passed = true;
    }

    public void broke() {
        passed = false;
    }
}
