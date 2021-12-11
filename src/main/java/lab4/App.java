package lab4;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

public class App {
    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create("test");
        ActorRef router = system.actorOf(
                Props.create(RouterActor.class)
        );
        
    }
}
