package cn.yan.first;

import com.typesafe.config.ConfigFactory;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

public class HelloMainSimple {
	public static void main(String[] args) {
		ActorSystem system = ActorSystem.create("Hello", ConfigFactory.load("samplehello.conf"));
		ActorRef actorOf = system.actorOf(Props.create(HelloWorld.class), "helloWorld");
		System.out.println("Hello World Path：" + actorOf.path());
	}
}
