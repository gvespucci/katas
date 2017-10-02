package io.gvespucci.kata.social_networking.domain.command;

public interface ParsedFromTextCommand {
	void execute();

	public final static ParsedFromTextCommand NULL = () -> {
		// It does nothing
	};
}
