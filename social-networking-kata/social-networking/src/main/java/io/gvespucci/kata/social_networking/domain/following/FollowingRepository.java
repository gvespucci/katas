package io.gvespucci.kata.social_networking.domain.following;

import java.util.List;

import io.gvespucci.kata.social_networking.domain.Following;

public interface FollowingRepository {

	void add(Following following);
	Boolean exists(Following following);
	List<Following> findBy(String username);

}
