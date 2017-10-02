package io.gvespucci.kata.social_networking.domain.message;

import java.util.List;

import io.gvespucci.kata.social_networking.domain.Message;

public interface MessageRepository {
	List<Message> findBy(String username);
	void add(String username, Message message);
}
