package io.gvespucci.kata.social_networking;

import java.io.PrintStream;
import java.time.LocalTime;

public interface Message {

	void printTo(PrintStream out, LocalTime referenceTime);

}
