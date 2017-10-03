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

import static org.assertj.core.api.Assertions.assertThat;

import java.io.PrintStream;
import java.time.LocalTime;
import java.util.List;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.gvespucci.kata.social_networking.domain.following.FollowingRepository;
import io.gvespucci.kata.social_networking.domain.following.InMemoryFollowingRepository;
import io.gvespucci.kata.social_networking.domain.message.InMemoryMessageRepository;
import io.gvespucci.kata.social_networking.domain.message.MessageRepository;
import io.gvespucci.kata.social_networking.util.FakePrintStream;

public class SocialNetworkTest {

	private static final LocalTime _1ST_MESSAGE_TIME = LocalTime.of(16, 21, 34);
	private static final LocalTime SUBMISSION_TIME = LocalTime.of(15, 00, 00);

	private MessageRepository messageRepository;
	private FollowingRepository followingRepository;

	@BeforeMethod
	public void before() {
		this.messageRepository = new InMemoryMessageRepository();
		this.followingRepository = new InMemoryFollowingRepository();
	}

	@Test
	public void aUserCanPostOneMessage() {
		final SocialNetwork socialNetwork = new SocialNetwork(this.messageRepository, this.followingRepository, new PrintStream(System.out));

		socialNetwork.execute("Alice -> I love the weather today", SUBMISSION_TIME);

		assertThat(socialNetwork.messagesFor("Alice").size()).isEqualTo(1);
	}

	@Test
	public void aUserCanPostMultipleMessages() {
		final SocialNetwork socialNetwork = new SocialNetwork(this.messageRepository, this.followingRepository, new PrintStream(System.out));

		socialNetwork.execute("Bob -> Damn! We lost!", SUBMISSION_TIME);
		socialNetwork.execute("Bob -> Good game though.", SUBMISSION_TIME.plusSeconds(5));

		assertThat(socialNetwork.messagesFor("Bob").size()).isEqualTo(2);
	}

	@Test
	public void aUserTimelineContainsAllMessagesInReverseTimeOrder() throws Exception {
		final FakePrintStream printStream = new FakePrintStream(System.out);
		final SocialNetwork socialNetwork = new SocialNetwork(this.messageRepository, this.followingRepository, printStream);

		this.postMessagesIn(socialNetwork);

		socialNetwork.execute("Bob", SUBMISSION_TIME);

		assertThat(printStream.printedMessages().get(0)).startsWith("Bob 3rd Message");
		assertThat(printStream.printedMessages().get(1)).startsWith("Bob 2nd Message");
		assertThat(printStream.printedMessages().get(2)).startsWith("Bob 1st Message");
	}

	@Test
	public void aUserTimelineIsEmptyIfNotYetPostedAnything() throws Exception {
		final SocialNetwork socialNetwork = new SocialNetwork(this.messageRepository, this.followingRepository, System.out);
		socialNetwork.execute("Mike", SUBMISSION_TIME);
		assertThat(socialNetwork.messagesFor("Mike")).isEmpty();
	}

	@Test
	public void aUserCanFollowAnotherUser() throws Exception {
		final SocialNetwork socialNetwork = new SocialNetwork(this.messageRepository, this.followingRepository, System.out);
		socialNetwork.execute("Bob follows Alice", SUBMISSION_TIME);
		assertThat(socialNetwork.isFollowing("Bob", "Alice")).isTrue();
	}

	@Test
	public void aUserDoesNotFollowAnotherUserByDefault() throws Exception {
		assertThat(new SocialNetwork(this.messageRepository, this.followingRepository, System.out).isFollowing("Bob", "Alice")).isFalse();
	}

	@Test
	public void charliesWall_WhenFollowsAlice_IsMadeOfItsMessagesPlusAliceOnes() throws Exception {
		final FakePrintStream printStream = new FakePrintStream(System.out);
		final SocialNetwork socialNetwork = new SocialNetwork(this.messageRepository, this.followingRepository, printStream);
		this.postMessagesIn(socialNetwork);

		socialNetwork.execute("Charlie follows Alice", SUBMISSION_TIME);

		socialNetwork.execute("Charlie wall", SUBMISSION_TIME.plusSeconds(5));

		final List<String> messages = printStream.printedMessages();
		assertThat(messages.size()).isEqualTo(3);
		assertThat(messages.get(0)).startsWith("Charlie 1st Message");
		assertThat(messages.get(1)).startsWith("Alice 2nd Message");
		assertThat(messages.get(2)).startsWith("Alice 1st Message");
	}

	@Test
	public void charliesWall_WhenFollowsAliceAndBob_IsMadeOfItsMessagesPlusAliceAndBobOnes() throws Exception {
		final FakePrintStream printStream = new FakePrintStream(System.out);
		final SocialNetwork socialNetwork = new SocialNetwork(this.messageRepository, this.followingRepository, printStream);
		this.postMessagesIn(socialNetwork);

		socialNetwork.execute("Charlie follows Alice", SUBMISSION_TIME);
		socialNetwork.execute("Charlie follows Bob", SUBMISSION_TIME.plusSeconds(5));

		socialNetwork.execute("Charlie wall", SUBMISSION_TIME.plusSeconds(10));

		final List<String> messages = printStream.printedMessages();
		assertThat(messages.size()).isEqualTo(6);
		assertThat(messages.get(0)).startsWith("Charlie 1st Message");
		assertThat(messages.get(1)).startsWith("Bob 3rd Message");
		assertThat(messages.get(2)).startsWith("Alice 2nd Message");
		assertThat(messages.get(3)).startsWith("Alice 1st Message");
		assertThat(messages.get(4)).startsWith("Bob 2nd Message");
		assertThat(messages.get(5)).startsWith("Bob 1st Message");

	}

	private void postMessagesIn(final SocialNetwork socialNetwork) {
		socialNetwork.execute("Bob -> Bob 1st Message", _1ST_MESSAGE_TIME);
		socialNetwork.execute("Bob -> Bob 2nd Message", _1ST_MESSAGE_TIME.plusSeconds(15));

		socialNetwork.execute("Alice -> Alice 1st Message", _1ST_MESSAGE_TIME.plusSeconds(30));
		socialNetwork.execute("Alice -> Alice 2nd Message", _1ST_MESSAGE_TIME.plusSeconds(45));

		socialNetwork.execute("Bob -> Bob 3rd Message", _1ST_MESSAGE_TIME.plusMinutes(1));

		socialNetwork.execute("Charlie -> Charlie 1st Message", _1ST_MESSAGE_TIME.plusMinutes(2));
	}


}
