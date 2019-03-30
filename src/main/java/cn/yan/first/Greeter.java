package cn.yan.first;

import akka.actor.UntypedActor;

public class Greeter extends UntypedActor {
	
	public static enum Msg {
		GREET,DONG
	}

	@Override
	public void onReceive(Object message) throws Throwable {
		if (message == Msg.GREET) {
			System.out.println("Hello World!");
			getSender().tell(Msg.DONG, getSelf());
		} else {
			unhandled(message);
		}
		
	}

}
