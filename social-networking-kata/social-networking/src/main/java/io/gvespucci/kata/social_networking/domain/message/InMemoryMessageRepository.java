package io.gvespucci.kata.social_networking.domain.message;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import io.gvespucci.kata.social_networking.domain.Message;

public class InMemoryMessageRepository implements MessageRepository {

	private final Map<String, LinkedList<Message>> messages = new HashMap<>();

	@Override
	public List<Message> findBy(String username) {
		return messagesFor(username);
	}

	private LinkedList<Message> messagesFor(String username) {
		LinkedList<Message> messages = this.messages.get(username);
		if(messages == null) {
			messages = new LinkedList<>();
		}
		return messages;
	}

	@Override
	public void add(String username, Message message) {
		final LinkedList<Message> messages = messagesFor(username);
		messages.push(message);
		this.messages.put(username, messages);
	}

}
