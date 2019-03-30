package cn.yan.second;

import com.typesafe.config.ConfigFactory;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.PoisonPill;
import akka.actor.Props;

public class DeadMain {

	public static void main(String[] args) {
		ActorSystem actorSystem = ActorSystem.create("deadwatch", ConfigFactory.load("samplehello.conf"));
		ActorRef worker = actorSystem.actorOf(Props.create(MyWorker.class), "worker");
		actorSystem.actorOf(Props.create(WatchActor.class, worker), "wather");
		worker.tell(MyWorker.Msg.WORKING, ActorRef.noSender());
		worker.tell(MyWorker.Msg.DONE, ActorRef.noSender());
		worker.tell(PoisonPill.getInstance(), ActorRef.noSender());                                               
	}

}
