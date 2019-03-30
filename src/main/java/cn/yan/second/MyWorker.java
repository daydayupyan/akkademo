package cn.yan.second;

import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import sun.util.logging.resources.logging;

public class MyWorker extends UntypedActor {
	private final LoggingAdapter logging = Logging.getLogger(getContext().system(), this);
	public static enum Msg {
		WORKING, DONE, CLOSE;
	}
	
	@Override
	public void preStart() throws Exception {
		System.out.println("MyWorker is starting");
	}
	
	@Override
	public void postStop() throws Exception {
		System.out.println("MyWorker is stopping");
	}

	@Override
	public void onReceive(Object message) throws Throwable {
		if (message == Msg.WORKING) {
			System.out.println("I am Working");
		}
		if (message == Msg.DONE) {
			System.out.println("Stop working");
		}
		if (message == Msg.CLOSE) {
			System.out.println("I will shutdown");
			getSender().tell(Msg.CLOSE, getSelf());
			getContext().stop(getSelf());
		} else {
			unhandled(message);
		}
		
	}

}
