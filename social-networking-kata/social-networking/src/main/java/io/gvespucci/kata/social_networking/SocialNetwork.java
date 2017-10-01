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
package io.gvespucci.kata.social_networking;

import java.io.PrintStream;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class SocialNetwork {

	private final PrintStream printStream;
	private final Map<String, LinkedList<Message>> messages = new HashMap<>();
	private final List<Following> followings = new ArrayList<>();

	public SocialNetwork(PrintStream printStream) {
		this.printStream = printStream;
	}

	public void read(String username, LocalTime reference) {
		this.messagesFor(username)
		.stream()
		.forEach(message -> message.printTo(this.printStream, reference));
	}

	public void post(String username, Message message) {
		final LinkedList<Message> messages = messagesFor(username);
		messages.push(message);
		this.messages.put(username, messages);
	}

	LinkedList<Message> messagesFor(String username) {
		LinkedList<Message> messages = this.messages.get(username);
		if(messages == null) {
			messages = new LinkedList<>();
		}
		return messages;
	}

	public void follows(String followerName, String followeeName) {
		this.followings.add(new Following(followerName, followeeName));
	}

	Boolean isFollowing(String followerName, String followeeName) {
		return
				this.followings
				.stream()
				.filter(following -> following.equals(new Following(followerName, followeeName)))
				.findAny().isPresent();
	}

	public void wallOf(String username, LocalTime referenceTime) {
		final LinkedList<Message> userMessages = this.messagesFor(username);

		final List<Message> wall = new ArrayList<>();

		userMessages.stream().forEach(message -> wall.add(message));

		this.followings.stream()
		.filter(following -> following.follower().equals(username))
		.forEach(following ->
			wall.addAll(this.messagesFor(following.followee()))
		);

		wall
		.stream()
		.sorted(Comparator.comparing(Message::submissionTime).reversed())
		.forEach(message -> message.printTo(this.printStream, referenceTime))
		;

		final Comparator<LocalTime> timeComparator = (arg0, arg1) -> arg0.compareTo(arg1);
	}



}
