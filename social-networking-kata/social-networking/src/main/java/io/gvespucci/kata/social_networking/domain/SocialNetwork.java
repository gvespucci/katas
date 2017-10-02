/*
 * =============================================================================
 *
 *   Copyright 2017 Giorgio Vespucci - giorgio.vespucci@gmail.com
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 *
 * =============================================================================
 */
package io.gvespucci.kata.social_networking.domain;

import java.io.PrintStream;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import io.gvespucci.kata.social_networking.domain.following.Following;
import io.gvespucci.kata.social_networking.domain.following.FollowingRepository;
import io.gvespucci.kata.social_networking.domain.message.Message;
import io.gvespucci.kata.social_networking.domain.message.MessageRepository;

public class SocialNetwork {

	private final PrintStream printStream;
	private final MessageRepository messageRepository;
	private final FollowingRepository followingsRepository;

	public SocialNetwork(MessageRepository messages, FollowingRepository followings, PrintStream printStream) {
		this.messageRepository = messages;
		this.followingsRepository = followings;
		this.printStream = printStream;
	}

	public void execute(String command) {

	}

	void read(String username, LocalTime referenceTime) {
		this.messagesFor(username)
		.stream()
		.forEach(message -> message.printTo(this.printStream, referenceTime));
	}

	public void post(String username, Message message) {
		this.messageRepository.add(username, message);
	}

	List<Message> messagesFor(String username) {
		return this.messageRepository.findBy(username);
	}

	void follows(String followerName, String followeeName) {
		this.followingsRepository.add(new Following(followerName, followeeName));
	}

	Boolean isFollowing(String followerName, String followeeName) {
		return this.followingsRepository.exists(new Following(followerName, followeeName));
	}

	void wallOf(String username, LocalTime referenceTime) {
		final List<Message> userMessages = this.messagesFor(username);

		final List<Message> wall = new ArrayList<>();

		userMessages.stream().forEach(message -> wall.add(message));

		this.followingsRepository.findBy(username)
		.forEach(following ->
			wall.addAll(this.messagesFor(following.followee()))
		);

		wall
		.stream()
		.sorted(Comparator.comparing(Message::submissionTime).reversed())
		.forEach(message -> message.printTo(this.printStream, referenceTime))
		;

	}

}
