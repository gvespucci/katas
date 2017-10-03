package io.gvespucci.kata.social_networking.domain.command;

import io.gvespucci.kata.social_networking.domain.message.Message;
import io.gvespucci.kata.social_networking.domain.message.MessageRepository;

public class PostCommand implements SocialNetworkCommand {

	private String username;
	private Message message;
	private MessageRepository messageRepository;

	public PostCommand(String username, Message message, MessageRepository messageRepository) {
		this.username = username;
		this.message = message;
		this.messageRepository = messageRepository;
	}

	@Override
	public void execute() {
		this.messageRepository.add(username, message);
	}

}
