package io.gvespucci.kata.social_networking.domain.command;

import io.gvespucci.kata.social_networking.domain.message.Message;
import io.gvespucci.kata.social_networking.domain.message.MessageRepository;

public class PostCommand implements SocialNetworkCommand {

	private final Message message;
	private final MessageRepository messageRepository;

	public PostCommand(Message message, MessageRepository messageRepository) {
		this.message = message;
		this.messageRepository = messageRepository;
	}

	@Override
	public void execute() {
		this.messageRepository.add(message);
	}

}
