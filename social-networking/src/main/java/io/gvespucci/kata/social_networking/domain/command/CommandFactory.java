package io.gvespucci.kata.social_networking.domain.command;

import java.io.PrintStream;
import java.time.LocalTime;

import io.gvespucci.kata.social_networking.domain.following.FollowingRepository;
import io.gvespucci.kata.social_networking.domain.message.MessageRepository;
import io.gvespucci.kata.social_networking.domain.message.TextMessage;

public class CommandFactory {

	private final MessageRepository messageRepository;
	private final FollowingRepository followingRepository;
	private final PrintStream printStream;

	public CommandFactory(MessageRepository messageRepository, FollowingRepository followingRepository, PrintStream printStream) {
		this.messageRepository = messageRepository;
		this.followingRepository = followingRepository;
		this.printStream = printStream;
	}

	public SocialNetworkCommand commandBy(String commandText, LocalTime submissionTime) {
		if(commandText.contains(" -> ")) {
			final String[] splitCommand = commandText.split(" -> ");
			final String username = splitCommand[0];
			final String messageText = splitCommand[1];
			return new PostCommand(new TextMessage(username, messageText, submissionTime), this.messageRepository);
		} else
		if(commandText.contains(" follows ")) {
			final String[] splitCommand = commandText.split(" follows ");
			final String followerName = splitCommand[0];
			final String followeeName = splitCommand[1];
			return new FollowCommand(followerName, followeeName, this.followingRepository);
		} else
		if(commandText.contains(" wall")) {
			final String[] splitCommand = commandText.split(" wall");
			final String username = splitCommand[0];
			return new WallCommand(username, submissionTime, this.messageRepository, this.followingRepository, this.printStream);
		} else {
			return new ReadCommand(commandText, submissionTime, this.messageRepository, this.printStream);
		}
	}

}
