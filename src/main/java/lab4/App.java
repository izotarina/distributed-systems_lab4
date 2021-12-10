package lab4;

import akka.actor.ActorSystem;

public class App {
    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create("test");
    }
}
