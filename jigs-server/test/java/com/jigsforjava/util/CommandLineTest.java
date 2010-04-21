/*
	Copyright 2003-2006 Strategic Gains, Inc.
	
	Licensed under the Apache License, Version 2.0 (the "License");
	you may not use this file except in compliance with the License.
	You may obtain a copy of the License at
	
		http://www.apache.org/licenses/LICENSE-2.0
	
	Unless required by applicable law or agreed to in writing, software
	distributed under the License is distributed on an "AS IS" BASIS,
	WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
	See the License for the specific language governing permissions and
	limitations under the License.
*/
package com.jigsforjava.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * @author Todd Fredrich
 * @since  Oct 23, 2003
 */
public class CommandLineTest
{
	private static final String COMMAND_LINE_TOKENS = "zi~o~";

	@Test
	public void testParse()
	throws Exception
	{
		String[] command = {"-z", "-o", "XmlFile.xml", "amzn", "yhoo", "hpq", "-i", "InputFile.txt"};
		CommandLine parser = new CommandLine(COMMAND_LINE_TOKENS).parse(command);

		assertTrue("-o option not set", parser.isOptionSet('o'));
		assertTrue("-i option not set", parser.isOptionSet('i'));
		assertTrue("-z option not set", parser.isOptionSet('z'));
		assertFalse("-x option set", parser.isOptionSet('x'));
		assertNotNull("-o value is null", parser.getOptionArgument('o'));
		assertEquals("XmlFile.xml", parser.getOptionArgument('o'));
		assertNotNull("-i value is null", parser.getOptionArgument('i'));
		assertEquals("InputFile.txt", parser.getOptionArgument('i'));

		String[] arguments = parser.getArguments();
		assertEquals("Argument size incorrect", 3, arguments.length);
		assertEquals("argument[0]", "amzn", arguments[0]);
		assertEquals("argument[1]", "yhoo", arguments[1]);
		assertEquals("argument[2]", "hpq", arguments[2]);
	}
}
