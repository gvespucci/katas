package io.gvespucci.kata.social_networking.domain.command;

import io.gvespucci.kata.social_networking.domain.following.Following;
import io.gvespucci.kata.social_networking.domain.following.FollowingRepository;

public class FollowCommand implements SocialNetworkCommand {

	private String followerName;
	private String followeeName;
	private FollowingRepository followingsRepository;

	public FollowCommand(String followerName, String followeeName, FollowingRepository followingsRepository) {
		this.followerName = followerName;
		this.followeeName = followeeName;
		this.followingsRepository = followingsRepository;
	}

	@Override
	public void execute() {
		this.followingsRepository.add(new Following(followerName, followeeName));
	}

}
