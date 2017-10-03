package io.gvespucci.kata.social_networking.domain.command;

import java.io.PrintStream;
import java.time.LocalTime;

import io.gvespucci.kata.social_networking.domain.message.MessageRepository;

public class ReadCommand implements SocialNetworkCommand {

	private MessageRepository messageRepository;
	private String username;
	private LocalTime referenceTime;
	private PrintStream printStream;

	public ReadCommand(String username, LocalTime referenceTime, MessageRepository messageRepository, PrintStream printStream) {
		this.username = username;
		this.referenceTime = referenceTime;
		this.messageRepository = messageRepository;
		this.printStream = printStream;
	}

	@Override
	public void execute() {
		this.messageRepository.findBy(username)
		.stream()
		.forEach(message -> message.printTo(this.printStream, referenceTime));
	}

}
