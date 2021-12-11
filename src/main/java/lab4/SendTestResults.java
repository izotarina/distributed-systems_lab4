package lab4;

import java.util.List;

public class SendTestResults {
    private final String packageId;
    private final List<TestResult> testResults;

    public SendTestResults(String packageId, List<TestResult> testResults) {
        this.packageId = packageId
        this.testResults = testResults;
    }

    public String getPackageId() {
        return packageId;
    }

    public List<TestResult> getTestResults() {
        return testResults;
    }
}
