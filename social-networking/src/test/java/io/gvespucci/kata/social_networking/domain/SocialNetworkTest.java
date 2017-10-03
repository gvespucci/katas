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
import io.gvespucci.kata.social_networking.domain.message.Message;
import io.gvespucci.kata.social_networking.domain.message.MessageRepository;
import io.gvespucci.kata.social_networking.domain.message.TextMessage;
import io.gvespucci.kata.social_networking.util.FakePrintStream;

public class SocialNetworkTest {

	private static final String CHARLIE = "Charlie";
	private static final String MIKE = "Mike";
	private static final String ALICE = "Alice";
	private static final String BOB = "Bob";
	private static final LocalTime _1ST_MESSAGE_TIME = LocalTime.of(16, 21, 34);
	private static final LocalTime DUMMY_TIME = LocalTime.of(15, 00, 00);

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
		socialNetwork.post(ALICE, new TextMessage("I love the weather today", DUMMY_TIME));
		assertThat(socialNetwork.messagesFor(ALICE).size()).isEqualTo(1);
	}

	@Test
	public void aUserCanPostMultipleMessages() {
		final SocialNetwork socialNetwork = new SocialNetwork(this.messageRepository, this.followingRepository, new PrintStream(System.out));
		socialNetwork.post(BOB, new TextMessage("Damn! We lost!", DUMMY_TIME));
		socialNetwork.post(BOB, new TextMessage("Good game though.", DUMMY_TIME));
		assertThat(socialNetwork.messagesFor(BOB).size()).isEqualTo(2);
	}

	@Test
	public void messagesShouldBeStoredInReverseTimeOrder() {
		final FakePrintStream printStream = new FakePrintStream(System.out);
		final SocialNetwork socialNetwork = new SocialNetwork(this.messageRepository, this.followingRepository, printStream);

		final TextMessage _1stMessage = new TextMessage("1st Message", LocalTime.of(16, 21, 34));
		socialNetwork.post(BOB, _1stMessage);

		final TextMessage _2ndMessage = new TextMessage("2nd Message", LocalTime.of(16, 21, 34).plusSeconds(15));
		socialNetwork.post(BOB, _2ndMessage);

		final TextMessage _3rdMessage = new TextMessage("3rd Message", LocalTime.of(16, 21, 34).plusSeconds(44));
		socialNetwork.post(BOB, _3rdMessage);

		final List<Message> bobMessages = socialNetwork.messagesFor(BOB);
		assertThat(bobMessages.get(0)).isEqualTo(_3rdMessage);
		assertThat(bobMessages.get(1)).isEqualTo(_2ndMessage);
		assertThat(bobMessages.get(2)).isEqualTo(_1stMessage);
	}

	@Test
	public void aUserTimelineContainsAllItsMessages() throws Exception {
		final FakePrintStream printStream = new FakePrintStream(System.out);
		final SocialNetwork socialNetwork = new SocialNetwork(this.messageRepository, this.followingRepository, printStream);

		this.postMessagesIn(socialNetwork);

		socialNetwork.read(BOB, DUMMY_TIME);

		assertThat(printStream.printedMessages().get(0)).startsWith("Bob 3rd Message");
		assertThat(printStream.printedMessages().get(1)).startsWith("Bob 2nd Message");
		assertThat(printStream.printedMessages().get(2)).startsWith("Bob 1st Message");
	}

	@Test
	public void aUserTimelineIsEmptyIfNotYetPostedAnything() throws Exception {
		final SocialNetwork socialNetwork = new SocialNetwork(this.messageRepository, this.followingRepository, System.out);
		socialNetwork.read(MIKE, DUMMY_TIME);
		assertThat(socialNetwork.messagesFor(MIKE)).isEmpty();
	}

	@Test
	public void aUserCanFollowAnotherUser() throws Exception {
		final SocialNetwork socialNetwork = new SocialNetwork(this.messageRepository, this.followingRepository, System.out);
		socialNetwork.follows(BOB, ALICE);
		assertThat(socialNetwork.isFollowing(BOB, ALICE)).isTrue();
	}

	@Test
	public void aUserDoesNotFollowAnotherUserByDefault() throws Exception {
		assertThat(new SocialNetwork(this.messageRepository, this.followingRepository, System.out).isFollowing(BOB, ALICE)).isFalse();
	}

	@Test
	public void charliesWall_WhenFollowsAlice_IsMadeOfItsMessagesPlusAliceOnes() throws Exception {
		final FakePrintStream printStream = new FakePrintStream(System.out);
		final SocialNetwork socialNetwork = new SocialNetwork(this.messageRepository, this.followingRepository, printStream);
		this.postMessagesIn(socialNetwork);

		socialNetwork.follows(CHARLIE, ALICE);

		socialNetwork.wallOf(CHARLIE, DUMMY_TIME);

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

		socialNetwork.follows(CHARLIE, ALICE);
		socialNetwork.follows(CHARLIE, BOB);

		socialNetwork.wallOf(CHARLIE, DUMMY_TIME);

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
		final TextMessage _1stBobMessage = new TextMessage("Bob 1st Message", _1ST_MESSAGE_TIME);
		socialNetwork.post(BOB, _1stBobMessage);

		final TextMessage _2ndBobMessage = new TextMessage("Bob 2nd Message", _1ST_MESSAGE_TIME.plusSeconds(15));
		socialNetwork.post(BOB, _2ndBobMessage);

		final TextMessage _1stAliceMessage = new TextMessage("Alice 1st Message", _1ST_MESSAGE_TIME.plusSeconds(30));
		socialNetwork.post(ALICE, _1stAliceMessage);
		final TextMessage _2ndAliceMessage = new TextMessage("Alice 2nd Message", _1ST_MESSAGE_TIME.plusSeconds(45));
		socialNetwork.post(ALICE, _2ndAliceMessage);

		final TextMessage _3rdBobMessage = new TextMessage("Bob 3rd Message", _1ST_MESSAGE_TIME.plusMinutes(1));
		socialNetwork.post(BOB, _3rdBobMessage);

		final TextMessage _1stCharlieMessage = new TextMessage("Charlie 1st Message", _1ST_MESSAGE_TIME.plusMinutes(2));
		socialNetwork.post(CHARLIE, _1stCharlieMessage);
	}


}
