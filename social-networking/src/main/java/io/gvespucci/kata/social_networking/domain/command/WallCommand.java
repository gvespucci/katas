package io.gvespucci.kata.social_networking.domain.command;

import java.io.PrintStream;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import io.gvespucci.kata.social_networking.domain.following.FollowingRepository;
import io.gvespucci.kata.social_networking.domain.message.Message;
import io.gvespucci.kata.social_networking.domain.message.MessageRepository;

public class WallCommand implements SocialNetworkCommand {

	private String username;
	private MessageRepository messageRepository;
	private PrintStream printStream;
	private FollowingRepository followingsRepository;
	private LocalTime referenceTime;

	public WallCommand(String username, LocalTime referenceTime, MessageRepository messageRepository, FollowingRepository followingsRepository, PrintStream printStream) {
		this.username = username;
		this.referenceTime = referenceTime;
		this.messageRepository = messageRepository;
		this.followingsRepository = followingsRepository;
		this.printStream = printStream;
	}

	@Override
	public void execute() {
		final List<Message> userMessages = this.messageRepository.findBy(username);

		final List<Message> wall = new ArrayList<>();

		userMessages.stream().forEach(message -> wall.add(message));

		this.followingsRepository.findBy(username)
		.forEach(following ->
			wall.addAll(this.messageRepository.findBy(following.followee()))
		);

		wall
		.stream()
		.sorted(Comparator.comparing(Message::submissionTime).reversed())
		.forEach(message -> message.printTo(this.printStream, referenceTime))
		;
	}

}
