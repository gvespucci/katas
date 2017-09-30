package io.gvespucci.kata.social_networking;

import java.io.PrintStream;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class SocialNetwork {

	private final PrintStream printStream;
	final Map<String, LinkedList<Message>> messages = new HashMap<>();

	public SocialNetwork(PrintStream printStream) {
		this.printStream = printStream;
	}

	public void read(String username, LocalTime reference) {
		this.messagesFor(username)
		.stream()
		.peek(message -> System.out.println(message))
		.forEach(message -> message.printTo(this.printStream, reference));
	}

	public void post(String username, Message message) {
		final LinkedList<Message> messages = messagesFor(username);
		messages.push(message);
		this.messages.put(username, messages);
	}

	LinkedList<Message> messagesFor(String username) {
		LinkedList<Message> messages = this.messages.get(username);
		if(messages == null) {
			messages = new LinkedList<>();
		}
		return messages;
	}



}
