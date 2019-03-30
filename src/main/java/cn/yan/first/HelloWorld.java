package cn.yan.first;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import scala.annotation.elidable;

public class HelloWorld extends UntypedActor {
	ActorRef greeter;
	
	@Override
	public void preStart() throws Exception {
		greeter = getContext().actorOf(Props.create(Greeter.class), "greeter");
		System.out.println("Greeter Actor Path:" + greeter.path());
		greeter.tell(Greeter.Msg.GREET, getSelf());
	}

	@Override
	public void onReceive(Object message) throws Throwable {
		if (message == Greeter.Msg.DONG) {
			greeter.tell(Greeter.Msg.GREET, getSelf());
			getContext().stop(getSelf());
		} else {
			unhandled(message);
		}
		
	}

}
