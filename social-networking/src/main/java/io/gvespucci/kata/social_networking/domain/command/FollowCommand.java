package io.gvespucci.kata.social_networking.domain.command;

import java.time.LocalTime;

import io.gvespucci.kata.social_networking.domain.following.Following;
import io.gvespucci.kata.social_networking.domain.following.FollowingRepository;

class FollowCommand extends SocialNetworkCommand {

	private static final String FOLLOW_COMMAND_IDENTIFIER = " follows ";

	private final FollowingRepository followingRepository;

	FollowCommand(FollowingRepository followingRepository) {
		this.followingRepository = followingRepository;
	}

	@Override
	void execute(String textCommand, LocalTime submissionTime) {
		if(textCommand.contains(FOLLOW_COMMAND_IDENTIFIER)) {
			final String[] splitCommand = textCommand.split(FOLLOW_COMMAND_IDENTIFIER);
			final String followerName = splitCommand[0];
			final String followeeName = splitCommand[1];
			this.followingRepository.add(new Following(followerName, followeeName));
		} else {
			this.nextCommand.execute(textCommand, submissionTime);
		}
	}

}
