package io.gvespucci.kata.social_networking.domain.following;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import io.gvespucci.kata.social_networking.domain.Following;

public class InMemoryFollowingRepository implements FollowingRepository {

	private final List<Following> followings = new ArrayList<>();

	@Override
	public void add(Following following) {
		this.followings.add(following);
	}

	@Override
	public Boolean exists(Following following) {
		return
				this.followings
				.stream()
				.filter(aFollowing -> aFollowing.equals(following))
				.findAny().isPresent();
	}

	@Override
	public List<Following> findBy(String username) {
		return
		this.followings.stream()
		.filter(following -> following.follower().equals(username))
		.collect(Collectors.toList());
	}

}
