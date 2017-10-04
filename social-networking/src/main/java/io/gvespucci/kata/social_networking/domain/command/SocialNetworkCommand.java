package io.gvespucci.kata.social_networking.domain.command;

import java.time.LocalTime;

abstract class SocialNetworkCommand {

	protected SocialNetworkCommand nextCommand;

	void addNext(SocialNetworkCommand command) {
		this.nextCommand = command;
	}

	abstract void execute(String textCommand, LocalTime submissionTime);

	static SocialNetworkCommand NULL = new SocialNetworkCommand() {
		@Override
		void execute(String textCommand, LocalTime submissionTime) {}
	};

}
