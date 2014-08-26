package pairhero.test;

public class TestId {

    private final TestClassName className;
    private final TestMethodName name;

    private TestId(TestClassName className, TestMethodName name) {
        this.className = className;
        this.name = name;
    }

    public static TestId aTestId(TestClassName className, TestMethodName name) {
        return new TestId(className,  name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TestId testId = (TestId) o;

        if (className != null ? !className.equals(testId.className) : testId.className != null) return false;
        if (name != null ? !name.equals(testId.name) : testId.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = className != null ? className.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
