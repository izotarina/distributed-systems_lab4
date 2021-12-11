package lab4;

import akka.actor.AbstractActor;
import akka.japi.pf.ReceiveBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StorageActor extends AbstractActor {
    private final Map<String, List<TestResult>> store = new HashMap<>();

    @Override
    public Receive createReceive() {
        return ReceiveBuilder.create()
                .match(StoreTestResult.class, m -> {
                    String packageId = m.getPackageId();
                    List<TestResult> testResults;

                    if (store.containsKey(packageId)) {
                        testResults = store.get(packageId);

                    } else {
                        testResults = new ArrayList<>();
                        store.put(m.getPackageId(), testResults);
                    }

                    testResults.add(m.getTestResult());
                })
                .match(GetTestResults.class, req -> sender().tell(
                        new SendTestResults(req.getPackageId(), store.get(req.getPackageId())), self())
                ).build();
    }
}
