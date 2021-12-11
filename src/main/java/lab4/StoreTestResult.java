package lab4;

public class StoreTestResult {
    private final String packageId;
    private final TestResult testResult;

    public StoreTestResult(String packageId, TestResult testResult) {
        this.packageId = packageId;
        this.testResult = testResult;
    }

    public String getPackageId() {
        return packageId;
    }

    public TestResult getTestResult() {
        return testResult;
    }
}
