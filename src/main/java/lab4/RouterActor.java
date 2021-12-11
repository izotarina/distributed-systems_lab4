package lab4;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.japi.pf.ReceiveBuilder;
import akka.routing.RoundRobinPool;

import java.util.List;

public class RouterActor extends AbstractActor {
    private final ActorRef storage;
    private final ActorRef pool;

    public RouterActor() {
        storage = getContext().actorOf(Props.create(StorageActor.class));
        pool = getContext().actorOf(
                new RoundRobinPool(5).props(Props.create(TestExecutorActor.class, storage)),
                "routerForTests");
    }

    @Override
    public Receive createReceive() {
        return ReceiveBuilder.create()
                .match(InputDataTests.class, m -> {
                    List<Test> tests  = m.getTests();
                    tests.forEach(test -> {
                        pool.tell(new TestExecuteRequest(m.getPackageId(), m.getJsScript(), m.getFunctionName(), test), self());
                    });
                })
                .match(GetTestResults.class, req -> storage.tell(
                        req, sender()
                ).build();
    }
}
