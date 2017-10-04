package io.gvespucci.kata.social_networking.domain.command;

import java.io.PrintStream;
import java.time.LocalTime;

import io.gvespucci.kata.social_networking.domain.message.MessageRepository;

class ReadCommand implements SocialNetworkCommand {

	private final MessageRepository messageRepository;
	private final String username;
	private final LocalTime referenceTime;
	private final PrintStream printStream;

	public ReadCommand(String username, LocalTime referenceTime, MessageRepository messageRepository, PrintStream printStream) {
		this.username = username;
		this.referenceTime = referenceTime;
		this.messageRepository = messageRepository;
		this.printStream = printStream;
	}

	@Override
	public void execute() {
		this.messageRepository.findBy(this.username)
		.stream()
		.forEach(message -> message.printTo(this.printStream, this.referenceTime));
	}

}
