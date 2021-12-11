package lab4;

public class Test {
    private final String testName;
    private final String expectedResult;
    private final Object[] params;

    public Test(String testName, String expectedResult, Object[] params) {
        this.testName = testName;
        this.expectedResult = expectedResult;
        this.params = params;
    }

    public String getTestName() {
        return testName;
    }

    public String getExpectedResult() {
        return expectedResult;
    }

    public Object[] getParams() {
        return params;
    }
}
