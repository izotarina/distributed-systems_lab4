package lab4;

import akka.actor.AbstractActor;
import akka.japi.pf.ReceiveBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StorageActor extends AbstractActor {
    private Map<String, List<TestResult>> store = new HashMap<>();

    @Override
    public Receive createReceive() {
        return ReceiveBuilder.create()
                .match(StoreTestResult.class, m -> {
                    String packageId = m.getPackageId();
                    List<TestResult> testResults;

                    if (store.containsKey(packageId)) {
                        testResults = store.get(packageId);
                        testResults.add(m.getTestResult());

                    } else {
                        testResults = new ArrayList<>();
                        testResults.add(m.getTestResult());
                        store.put(m.getPackageId(), testResults);
                    }
                })
                .match(GetMessage.class, req -> sender().tell(
                        new StoreMessage(req.getKey(), store.get(req.getKey())), self())
                ).build();
    }
}
