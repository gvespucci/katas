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

import static org.assertj.core.api.Assertions.assertThat;

import java.io.PrintStream;
import java.time.LocalTime;
import java.util.List;

import org.testng.annotations.Test;

public class SocialNetworkTest {

	@Test
	public void shouldPostOneMessage() {
		final SocialNetwork socialNetworking = new SocialNetwork(new PrintStream(System.out));
		socialNetworking.post("Alice", new TextMessage("I love the weather today", LocalTime.of(16, 21, 34)));
		assertThat(socialNetworking.messagesFor("Alice").size()).isEqualTo(1);
	}

	@Test
	public void shouldPostMultipleMessage() {
		final SocialNetwork socialNetworking = new SocialNetwork(new PrintStream(System.out));
		socialNetworking.post("Bob", new TextMessage("Damn! We lost!", LocalTime.of(16, 21, 34)));
		socialNetworking.post("Bob", new TextMessage("Good game though.", LocalTime.of(16, 21, 35)));
		assertThat(socialNetworking.messagesFor("Bob").size()).isEqualTo(2);
	}

	@Test
	public void shouldStoreMessagesInReverseTimeOrder() {
		final FakePrintStream printStream = new FakePrintStream(System.out);
		final SocialNetwork socialNetworking = new SocialNetwork(printStream);

		final TextMessage _1stMessage = new TextMessage("1st Message", LocalTime.of(16, 21, 34));
		socialNetworking.post("Bob", _1stMessage);

		final TextMessage _2ndMessage = new TextMessage("2nd Message", LocalTime.of(16, 21, 34).plusSeconds(15));
		socialNetworking.post("Bob", _2ndMessage);

		final TextMessage _3rdMessage = new TextMessage("3rd Message", LocalTime.of(16, 21, 34).plusSeconds(44));
		socialNetworking.post("Bob", _3rdMessage);

		final List<Message> bobMessages = socialNetworking.messagesFor("Bob");
		assertThat(bobMessages.get(0)).isEqualTo(_3rdMessage);
		assertThat(bobMessages.get(1)).isEqualTo(_2ndMessage);
		assertThat(bobMessages.get(2)).isEqualTo(_1stMessage);
	}

	@Test
	public void shouldReadUserMessages() throws Exception {
		final FakePrintStream printStream = new FakePrintStream(System.out);
		final SocialNetwork socialNetworking = new SocialNetwork(printStream);

		final TextMessage _1stBobMessage = new TextMessage("Bob 1st Message", LocalTime.of(16, 21, 34));
		socialNetworking.post("Bob", _1stBobMessage);

		final TextMessage _2ndBobMessage = new TextMessage("Bob 2nd Message", LocalTime.of(16, 21, 34).plusSeconds(15));
		socialNetworking.post("Bob", _2ndBobMessage);

		final TextMessage _1stAliceMessage = new TextMessage("Alice 1st Message", LocalTime.of(16, 21, 34).plusSeconds(25));
		socialNetworking.post("Alice", _1stAliceMessage);
		final TextMessage _2ndAliceMessage = new TextMessage("Alice 2nd Message", LocalTime.of(16, 21, 34).plusSeconds(35));
		socialNetworking.post("Alice", _2ndAliceMessage);

		final TextMessage _3rdBobMessage = new TextMessage("Bob 3rd Message", LocalTime.of(16, 21, 34).plusSeconds(44));
		socialNetworking.post("Bob", _3rdBobMessage);

		socialNetworking.read("Bob", LocalTime.of(16, 22, 34));

		assertThat(printStream.printed())
		.isEqualTo(
				"Bob 3rd Message (16 seconds ago) Bob 2nd Message (45 seconds ago) Bob 1st Message (1 minute ago) "
				);
	}

}
