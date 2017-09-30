package io.gvespucci.kata.social_networking;

import java.io.OutputStream;
import java.io.PrintStream;

public class FakePrintStream extends PrintStream {

	private StringBuffer printed = new StringBuffer();

	public FakePrintStream(OutputStream out) {
		super(out);
	}

	public void reset() {
		this.printed = new StringBuffer();
	}

	@Override
	public void println(String string) {
		this.printed.append(string);
		//		.append("\n");
	}

	public String printed() {
		return this.printed.toString();
	}
}
