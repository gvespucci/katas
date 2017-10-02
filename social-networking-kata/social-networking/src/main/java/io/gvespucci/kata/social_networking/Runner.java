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

import java.io.Console;

public class Runner {

	public static void main(String[] args) {
		System.out.println("--- Social Network ---");
		final Console console = System.console();
		if (console == null) {
			System.out.println("No console: non-interactive mode!");
			System.exit(0);
		}

		String command;

		while ((command = console.readLine("> ")) != null) {
			System.out.println("> Command was: " + command);
		}
		System.exit(0);
	}

}
