package io.gvespucci.kata.social_networking.domain.command;

import java.io.PrintStream;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import io.gvespucci.kata.social_networking.domain.following.FollowingRepository;
import io.gvespucci.kata.social_networking.domain.message.Message;
import io.gvespucci.kata.social_networking.domain.message.MessageRepository;

class WallCommand implements SocialNetworkCommand {

	private final String username;
	private final MessageRepository messageRepository;
	private final PrintStream printStream;
	private final FollowingRepository followingsRepository;
	private final LocalTime referenceTime;

	public WallCommand(String username, LocalTime referenceTime, MessageRepository messageRepository, FollowingRepository followingsRepository, PrintStream printStream) {
		this.username = username;
		this.referenceTime = referenceTime;
		this.messageRepository = messageRepository;
		this.followingsRepository = followingsRepository;
		this.printStream = printStream;
	}

	@Override
	public void execute() {
		final List<Message> userMessages = this.messageRepository.findBy(this.username);

		final List<Message> wall = new ArrayList<>();

		userMessages.stream().forEach(message -> wall.add(message));

		this.followingsRepository.findBy(this.username)
		.forEach(following ->
			wall.addAll(this.messageRepository.findBy(following.followee()))
		);

		wall
		.stream()
		.sorted(Comparator.comparing(Message::submissionTime).reversed())
		.forEach(message -> message.printTo(this.printStream, this.referenceTime))
		;
	}

}
