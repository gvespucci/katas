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
import java.util.List;

import io.gvespucci.kata.social_networking.domain.command.FollowCommand;
import io.gvespucci.kata.social_networking.domain.command.PostCommand;
import io.gvespucci.kata.social_networking.domain.command.ReadCommand;
import io.gvespucci.kata.social_networking.domain.command.WallCommand;
import io.gvespucci.kata.social_networking.domain.following.Following;
import io.gvespucci.kata.social_networking.domain.following.FollowingRepository;
import io.gvespucci.kata.social_networking.domain.message.Message;
import io.gvespucci.kata.social_networking.domain.message.MessageRepository;
import io.gvespucci.kata.social_networking.domain.message.TextMessage;

public class SocialNetwork {

	private final PrintStream printStream;
	private final MessageRepository messageRepository;
	private final FollowingRepository followingRepository;

	public SocialNetwork(MessageRepository messageRepository, FollowingRepository followingRepository, PrintStream printStream) {
		this.messageRepository = messageRepository;
		this.followingRepository = followingRepository;
		this.printStream = printStream;
	}

	public void execute(String commandText, LocalTime submissionTime) {

		if(commandText.contains(" -> ")) {
			final String[] splitCommand = commandText.split(" -> ");
			final String username = splitCommand[0];
			final String messageText = splitCommand[1];
			this.post(username, new TextMessage(username, messageText, submissionTime));
		} else
		if(commandText.contains(" follows ")) {
			final String[] splitCommand = commandText.split(" follows ");
			final String followerName = splitCommand[0];
			final String followeeName = splitCommand[1];
			this.follows(followerName, followeeName);
		} else
		if(commandText.contains(" wall")) {
			final String[] splitCommand = commandText.split(" wall");
			final String username = splitCommand[0];
			this.wallOf(username, submissionTime);
		} else {
			this.read(commandText, submissionTime);
		};

	}

	private void read(String username, LocalTime referenceTime) {
		new ReadCommand(username, referenceTime, this.messageRepository, this.printStream).execute();
	}

	private void post(String username, Message message) {
		new PostCommand(username, message, this.messageRepository).execute();
	}

	private void follows(String followerName, String followeeName) {
		new FollowCommand(followerName, followeeName, this.followingRepository).execute();
	}

	private void wallOf(String username, LocalTime referenceTime) {
		new WallCommand(username, referenceTime, this.messageRepository, this.followingRepository, this.printStream).execute();
	}

	List<Message> messagesFor(String username) {
		return this.messageRepository.findBy(username);
	}

	Boolean isFollowing(String followerName, String followeeName) {
		return this.followingRepository.exists(new Following(followerName, followeeName));
	}

}
