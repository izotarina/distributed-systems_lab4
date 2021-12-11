package lab4;

import java.util.List;

public class InputDataTests {
    private final String packageId;
    private final String jsScript;
    private final String functionName;
    private final List<Test> tests;

    public InputDataTests(String packageId, String jsScript, String functionName, Test test) {
        this.packageId = packageId;
        this.jsScript = jsScript;
        this.functionName = functionName;
        this.test = test;
    }

    public String getPackageId() {
        return packageId;
    }

    public String getJsScript() {
        return jsScript;
    }

    public String getFunctionName() {
        return functionName;
    }

    public List<Test> getTests() {
        return tests;
    }
}
