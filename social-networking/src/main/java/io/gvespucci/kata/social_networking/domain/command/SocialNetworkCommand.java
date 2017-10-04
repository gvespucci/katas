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
package io.gvespucci.kata.social_networking.domain.command;

import java.time.LocalTime;

abstract class SocialNetworkCommand {

	protected SocialNetworkCommand nextCommand;

	void addNext(SocialNetworkCommand command) {
		this.nextCommand = command;
	}

	void execute(String textCommand, LocalTime submissionTime) {
		if(canHandle(textCommand)) {
			innerExecute(textCommand, submissionTime);
		} else {
			this.nextCommand.execute(textCommand, submissionTime);
		}
	};

	abstract boolean canHandle(String textCommand);
	abstract void innerExecute(String textCommand, LocalTime submissionTime);

	static SocialNetworkCommand NULL = new SocialNetworkCommand() {

		@Override
		boolean canHandle(String textCommand) { return true; }

		@Override
		void innerExecute(String textCommand, LocalTime submissionTime) {}
	};

}
