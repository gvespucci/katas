package io.gvespucci.kata.social_networking.domain.command;

import java.io.PrintStream;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import io.gvespucci.kata.social_networking.domain.following.FollowingRepository;
import io.gvespucci.kata.social_networking.domain.message.Message;
import io.gvespucci.kata.social_networking.domain.message.MessageRepository;

class WallCommand extends SocialNetworkCommand {

	private static final String WALL_COMMAND_IDENTIFIER = " wall";

	private final MessageRepository messageRepository;
	private final PrintStream printStream;
	private final FollowingRepository followingRepository;

	WallCommand(
			MessageRepository messageRepository,
			FollowingRepository followingRepository,
			PrintStream printStream)
	{
		this.messageRepository = messageRepository;
		this.followingRepository = followingRepository;
		this.printStream = printStream;
	}

	@Override
	boolean canHandle(String textCommand) {
		return textCommand.contains(WALL_COMMAND_IDENTIFIER);
	}

	@Override
	void innerExecute(String textCommand, LocalTime submissionTime) {
		final String[] splitCommand = textCommand.split(WALL_COMMAND_IDENTIFIER);
		final String username = splitCommand[0];

		final List<Message> userMessages = this.messageRepository.findBy(username);

		final List<Message> wall = new ArrayList<>();

		userMessages.stream().forEach(message -> wall.add(message));

		this.followingRepository.findBy(username)
		.forEach(following ->
			wall.addAll(this.messageRepository.findBy(following.followee()))
		);

		wall
		.stream()
		.sorted(Comparator.comparing(Message::submissionTime).reversed())
		.forEach(message -> message.printTo(this.printStream, submissionTime))
		;
	}

}
