package io.gvespucci.kata.social_networking.domain.command;

import java.io.PrintStream;
import java.time.LocalTime;

import io.gvespucci.kata.social_networking.domain.message.MessageRepository;

class ReadCommand extends SocialNetworkCommand {

	private final MessageRepository messageRepository;
	private final PrintStream printStream;

	ReadCommand(MessageRepository messageRepository, PrintStream printStream) {
		this.messageRepository = messageRepository;
		this.printStream = printStream;
	}

	@Override
	void innerExecute(String textCommand, LocalTime submissionTime) {
		this.messageRepository.findBy(textCommand)
		.stream()
		.forEach(message -> message.printTo(this.printStream, submissionTime));
	}

	@Override
	boolean canHandle(String textCommand) {
		return true;
	}

}
