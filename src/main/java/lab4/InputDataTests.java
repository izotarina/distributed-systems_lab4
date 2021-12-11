package lab4;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class InputDataTests {
    private final String packageId;
    private final String jsScript;
    private final String functionName;
    private final List<Test> tests;

    @JsonCreator
    public InputDataTests(@JsonProperty String packageId,
                          @JsonProperty String jsScript,
                          @JsonProperty String functionName,
                          @JsonProperty List<Test> tests)
    {
        this.packageId = packageId;
        this.jsScript = jsScript;
        this.functionName = functionName;
        this.tests = tests;
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
