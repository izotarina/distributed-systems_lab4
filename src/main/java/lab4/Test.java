package lab4;

public class Test {
    private final String testName;
    private final String expectedResult;
    private final int[] params;

    public Test(String testName, String expectedResult, int[] params) {
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

    public int[] getParams() {
        return params;
    }
}
