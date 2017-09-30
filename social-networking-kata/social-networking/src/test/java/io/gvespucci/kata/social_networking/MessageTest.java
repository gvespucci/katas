package io.gvespucci.kata.social_networking;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalTime;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class MessageTest {

	private FakePrintStream printStream;
	private String text;
	private LocalTime submittedOn;

	@BeforeMethod
	public void before() {
		this.printStream = new FakePrintStream(System.out);
		this.text = "Hi there!";
		this.submittedOn = LocalTime.of(13, 40, 27);
	}

	@AfterMethod
	public void after() {
		this.printStream.reset();
	}

	@Test
	public void shouldPrintTheText() {
		final TextMessage message = new TextMessage(this.text, LocalTime.now());
		message.printTo(this.printStream, LocalTime.now());
		assertThat(this.printStream.printed()).startsWith(this.text);
	}

	@Test
	public void shouldPrintElapsedSecondsAfterTheText() {
		final TextMessage message = new TextMessage(this.text, this.submittedOn);
		message.printTo(this.printStream, this.submittedOn.plusSeconds(24));
		assertThat(this.printStream.printed()).isEqualTo(this.text+" (24 seconds ago) ");
	}

	@Test
	public void shouldPrintElapsedMinutesAfterTheText() {
		final TextMessage message = new TextMessage(this.text, this.submittedOn);
		message.printTo(this.printStream, this.submittedOn.plusMinutes(4).plusSeconds(22));
		assertThat(this.printStream.printed()).isEqualTo(this.text+" (4 minutes ago) ");
	}

	@Test
	public void shouldPrintOneSingleMinuteAfterTheText() {
		final TextMessage message = new TextMessage(this.text, this.submittedOn);
		message.printTo(this.printStream, this.submittedOn.plusMinutes(1));
		assertThat(this.printStream.printed()).isEqualTo(this.text+" (1 minute ago) ");
	}

}
