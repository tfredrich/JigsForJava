/*
	Copyright 2003-2008 Strategic Gains, Inc.
	
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.jigsforjava.exception.CommandLineException;

/**
 * The option pattern is defined of those characters accepted as options or
 * arguments. Argument options are followed by '~' in the string. All others are
 * assumed to be switches (options).
 * 
 * Example: If Options = "abC~c~de~fgh~" then 'a', 'b', 'd', 'f', and 'g' are
 * option switches, and 'C', 'c', 'e' and 'h' are followed with arguments.
 * 
 * If the switch character reported by DOS is '/' then using the above option
 * line: Command /g /a /b /c xyzt extras
 * 
 * Yields: 'a', 'b', and 'g' are returned as option switches. 'c' returns with
 * "xyzt" as its argument in Arg. "extras" is not an option and require special
 * handling by using the argv pointers later in the program.
 * 
 * @author Todd Fredrich
 * @since Oct 23, 2003
 */
public class CommandLine
{
	// SECTION: CONSTANTS

	private final static char ARGUMENT_INDICATOR = '~';
	private final static char SWITCH_CHARACTER = '-';

	
	// SECTION: INSTANCE VARIABLES

	private String pattern;
	private Set<Character> patternSwitches;
	private Set<Character> patternOptions;
	private List<String> arguments;
	private HashMap<Character, String> optionArguments;
	private Set<Character> setSwitches;

	
	// SECTION: CONSTRUCTORS

	/**
	 * Constructs a new CommandLineParser object using the pattern.
	 */
	public CommandLine(String pattern)
	{
		super();
		setPattern(pattern);
	}

	
	// SECTION: ACCESSORS/MUTATORS

	/**
	 * Method: setPattern()
	 * 
	 * @param pattern
	 */
	private void setPattern(String pattern)
	{
		this.pattern = pattern;
		patternSwitches = new HashSet<Character>();
		patternOptions = new HashSet<Character>();

		for (int i = 0; i < pattern.length(); ++i)
		{
			char token = pattern.charAt(i);

			if (i < pattern.length() - 1)
			{
				char indicator = pattern.charAt(i + 1);

				if (indicator == ARGUMENT_INDICATOR)
				{
					patternOptions.add(new Character(token));
					++i;
				}
				else
				{
					patternSwitches.add(new Character(token));
				}
			}
			else
			{
				patternSwitches.add(new Character(token));
			}
		}
	}

	public CommandLine parse(String[] args)
	throws CommandLineException
	{
		optionArguments = new HashMap<Character, String>();
		arguments = new ArrayList<String>();
		setSwitches = new HashSet<Character>();

		if (args != null)
		{
			for (int i = 0; i < args.length; i++)
			{
				String arg = args[i];
				char switchChar = arg.charAt(0);

				if (switchChar == SWITCH_CHARACTER)
				{
					for (int j = 1; j < arg.length(); ++j)
					{
						Character option = new Character(arg.charAt(j));

						if (patternSwitches.contains(option)) // switch setting
						{
							setSwitches.add(option);
						}
						else if (patternOptions.contains(option)) // parameter
																  // required
						{
							if (arg.length() > 2)
							{
								throw new CommandLineException(
								    "Invalid command line option: " + arg);
							}

							if (i + 1 >= args.length)
							{
								throw new CommandLineException(
								    "Parameter required for option: " + arg);
							}

							optionArguments.put(option, args[++i]);
						}
						else
						{
							throw new CommandLineException(
							    "Invalid command line option: " + option);
						}
					}
				}
				else
				// not option argument, plain old argument.
				{
					arguments.add(arg);
				}
			}
		}
		
		return this;
	}

	public boolean isOptionSet(char optionToken)
	{
		boolean isSet = false;
		Character option = new Character(optionToken);

		if (setSwitches.contains(option))
		{
			isSet = true;
		}
		else if (optionArguments.containsKey(option))
		{
			isSet = true;
		}

		return isSet;
	}

	public String getOptionArgument(char optionToken)
	{
		return (String) optionArguments.get(new Character(optionToken));
	}

	public boolean hasOptionArgumentFor(char optionToken)
	{
		return (getOptionArgument(optionToken) != null);
	}

	public String[] getArguments()
	{
		String[] strings = new String[arguments.size()];
		return (String[]) arguments.toArray(strings);
	}

	/**
	 * Method: getPattern()
	 * 
	 * @return
	 */
	public String getPattern()
	{
		return pattern;
	}
}
