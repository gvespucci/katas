package io.gvespucci.kata.social_networking.domain.command;

import java.io.PrintStream;
import java.time.LocalTime;

import io.gvespucci.kata.social_networking.domain.following.FollowingRepository;
import io.gvespucci.kata.social_networking.domain.message.MessageRepository;

public class CommandDispatcher {

	private final SocialNetworkCommand postCommand;

	public CommandDispatcher(
			MessageRepository messageRepository,
			FollowingRepository followingRepository,
			PrintStream printStream)
	{
		postCommand = new PostCommand(messageRepository);
		final SocialNetworkCommand followCommand = new FollowCommand(followingRepository);
		final SocialNetworkCommand wallCommand = new WallCommand(messageRepository, followingRepository, printStream);
		final SocialNetworkCommand readCommand = new ReadCommand(messageRepository, printStream);

		postCommand.addNext(followCommand);
		followCommand.addNext(wallCommand);
		wallCommand.addNext(readCommand);
		readCommand.addNext(SocialNetworkCommand.NULL);
	}

	public void execute(String commandText, LocalTime submissionTime) {
		postCommand.execute(commandText, submissionTime);
	}

}
