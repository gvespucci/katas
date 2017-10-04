package io.gvespucci.kata.social_networking.domain.command;

import java.io.PrintStream;
import java.time.LocalTime;

import io.gvespucci.kata.social_networking.domain.following.FollowingRepository;
import io.gvespucci.kata.social_networking.domain.message.MessageRepository;
import io.gvespucci.kata.social_networking.domain.message.TextMessage;

public class CommandFactory {

	private static final String WALL_COMMAND_IDENTIFIER = " wall";
	private static final String FOLLOW_COMMAND_IDENTIFIER = " follows ";
	private static final String POST_COMMAND_IDENTIFIER = " -> ";
	private final MessageRepository messageRepository;
	private final FollowingRepository followingRepository;
	private final PrintStream printStream;

	public CommandFactory(MessageRepository messageRepository, FollowingRepository followingRepository, PrintStream printStream) {
		this.messageRepository = messageRepository;
		this.followingRepository = followingRepository;
		this.printStream = printStream;
	}

	public SocialNetworkCommand commandBy(String commandText, LocalTime submissionTime) {
		if(commandText.contains(POST_COMMAND_IDENTIFIER)) {
			final String[] splitCommand = commandText.split(POST_COMMAND_IDENTIFIER);
			final String username = splitCommand[0];
			final String messageText = splitCommand[1];
			return new PostCommand(new TextMessage(username, messageText, submissionTime), this.messageRepository);
		} else
		if(commandText.contains(FOLLOW_COMMAND_IDENTIFIER)) {
			final String[] splitCommand = commandText.split(FOLLOW_COMMAND_IDENTIFIER);
			final String followerName = splitCommand[0];
			final String followeeName = splitCommand[1];
			return new FollowCommand(followerName, followeeName, this.followingRepository);
		} else
		if(commandText.contains(WALL_COMMAND_IDENTIFIER)) {
			final String[] splitCommand = commandText.split(WALL_COMMAND_IDENTIFIER);
			final String username = splitCommand[0];
			return new WallCommand(username, submissionTime, this.messageRepository, this.followingRepository, this.printStream);
		} else {
			return new ReadCommand(commandText, submissionTime, this.messageRepository, this.printStream);
		}
	}

}
