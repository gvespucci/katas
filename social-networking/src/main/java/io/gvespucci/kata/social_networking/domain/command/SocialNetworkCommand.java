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
