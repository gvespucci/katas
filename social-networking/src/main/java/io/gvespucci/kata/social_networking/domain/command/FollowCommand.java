package io.gvespucci.kata.social_networking.domain.command;

import io.gvespucci.kata.social_networking.domain.following.Following;
import io.gvespucci.kata.social_networking.domain.following.FollowingRepository;

class FollowCommand implements SocialNetworkCommand {

	private final String followerName;
	private final String followeeName;
	private final FollowingRepository followingsRepository;

	public FollowCommand(String followerName, String followeeName, FollowingRepository followingsRepository) {
		this.followerName = followerName;
		this.followeeName = followeeName;
		this.followingsRepository = followingsRepository;
	}

	@Override
	public void execute() {
		this.followingsRepository.add(new Following(this.followerName, this.followeeName));
	}

}
