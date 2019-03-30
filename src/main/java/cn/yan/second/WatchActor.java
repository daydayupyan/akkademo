package cn.yan.second;

import akka.actor.ActorRef;
import akka.actor.Terminated;
import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

public class WatchActor extends UntypedActor {
	private final LoggingAdapter log = Logging.getLogger(getContext().system(), this);
	
	public WatchActor(ActorRef actorRef) {
		getContext().watch(actorRef);
	}

	@Override
	public void onReceive(Object message) throws Throwable {
		if (message instanceof Terminated) {
			System.out.println(String.format("%s has terminated, shutting down system", ((Terminated)message).getActor().path()));
			getContext().stop(getSelf());
		} else {
			unhandled(message);
		}
		
	}

}
