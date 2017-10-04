package io.gvespucci.kata.social_networking.domain.command;

import java.time.LocalTime;

import io.gvespucci.kata.social_networking.domain.message.MessageRepository;
import io.gvespucci.kata.social_networking.domain.message.TextMessage;

class PostCommand extends SocialNetworkCommand {

	private static final String POST_COMMAND_IDENTIFIER = " -> ";

	private final MessageRepository messageRepository;

	PostCommand(MessageRepository messageRepository) {
		this.messageRepository = messageRepository;
	}

	@Override
	void innerExecute(String textCommand, LocalTime submissionTime) {
		final String[] splitCommand = textCommand.split(POST_COMMAND_IDENTIFIER);
		final String username = splitCommand[0];
		final String messageText = splitCommand[1];
		this.messageRepository.add(new TextMessage(username, messageText, submissionTime));
	}

	@Override
	boolean canHandle(String textCommand) {
		return textCommand.contains(POST_COMMAND_IDENTIFIER);
	}

}
