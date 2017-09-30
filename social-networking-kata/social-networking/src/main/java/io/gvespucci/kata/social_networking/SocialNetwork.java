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
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class SocialNetwork {

	private final PrintStream printStream;
	final Map<String, LinkedList<Message>> messages = new HashMap<>();

	public SocialNetwork(PrintStream printStream) {
		this.printStream = printStream;
	}

	public void read(String username, LocalTime reference) {
		this.messagesFor(username)
		.stream()
		.peek(message -> System.out.println(message))
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



}
