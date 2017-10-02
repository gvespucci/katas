package io.gvespucci.kata.social_networking.domain.message;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Predicate;

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
public class InMemoryMessageRepository implements MessageRepository {

	private final Map<String, LinkedList<Message>> messages = new HashMap<>();

	@Override
	public List<Message> findBy(String username) {
		return messagesFor(username);
	}

	private LinkedList<Message> messagesFor(String username) {
		return
				this.messages
				.entrySet()
				.stream()
				.filter(entriesBy(username))
				.map(entry -> entry.getValue())
				.findAny().orElse(new LinkedList<>());
	}

	private Predicate<? super Entry<String, LinkedList<Message>>> entriesBy(String username) {
		return entry -> entry.getKey().equals(username);
	}

	@Override
	public void add(String username, Message message) {
		final LinkedList<Message> messages = messagesFor(username);
		messages.push(message);
		this.messages.put(username, messages);
	}

}
