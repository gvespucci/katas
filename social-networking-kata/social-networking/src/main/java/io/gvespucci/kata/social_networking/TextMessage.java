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
import java.time.Duration;
import java.time.LocalTime;

public class TextMessage {

	private final String text;
	private final LocalTime submittedOn;

	public TextMessage(String text, LocalTime submittedOn) {
		this.text = text;
		this.submittedOn = submittedOn;
	}

	public void printTo(PrintStream out, LocalTime referenceTime) {

		final long secondsPassed = Duration.between(this.submittedOn, referenceTime).getSeconds();

		out.println(String.format("%s (%s %s ago)", this.text, elapsedTimeValue(secondsPassed), elapsedTimeUnit(secondsPassed)));
	}

	private String elapsedTimeValue(final long secondsPassed) {
		return secondsPassed < 60 ? Long.toString(secondsPassed) : Long.toString(secondsPassed/60);
	}

	private String elapsedTimeUnit(final long secondsPassed) {
		if(secondsPassed == 60) {
			return "minute";
		}
		return secondsPassed > 60 ? "minutes" : "seconds";
	}

}
