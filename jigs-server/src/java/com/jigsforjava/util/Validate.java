/*
	Copyright 2005 Strategic Gains, Inc.

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

import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.Date;

import com.jigsforjava.string.StringUtils;

/**
 * Abstract class that provides foreign methods for misc. validations.
 *
 * @author Todd Fredrich
 * @since Mar 14, 2005
 * @version $Revision: 1.1 $
 */
public abstract class Validate
{
	public static boolean isNotBlankOrNull(String value)
	{
		return (value != null && value.toString().trim().length() != 0);
	}
	
	public static boolean isNotNull(Object object)
	{
		return (object != null);
	}

	public static boolean isInRange(double value, double minimum, double maximum)
	{
		return (value >= minimum && value <= maximum);
	}
	
	public static boolean isInRange(int value, int minimum, int maximum)
	{
		return (value >= minimum && value <= maximum);
	}
	
	public static boolean isInRange(Date value, Date minimum, Date maximum)
	{
		return (value.compareTo(minimum) >= 0 && value.compareTo(maximum) <= 0);
	}

	public static boolean isNumeric(String aString)
	{
		if (StringUtils.isNullOrEmpty(aString)) return false;
		CharacterIterator i = new StringCharacterIterator(aString);

		for (char c = i.first(); c != CharacterIterator.DONE; c = i.next())
		{
			if (!Character.isDigit(c)) return false;
		}

		return true;
	}

	public static boolean isAlphabetic(String aString)
	{
		if (StringUtils.isNullOrEmpty(aString)) return false;
		CharacterIterator i = new StringCharacterIterator(aString);

		for (char c = i.first(); c != CharacterIterator.DONE; c = i.next())
		{
			if (!Character.isLetter(c)) return false;
		}

		return true;
	}

	public static boolean isValidDecimal(String aString)
	{
		if (StringUtils.isNullOrEmpty(aString)) return false;
		CharacterIterator i = new StringCharacterIterator(aString);
		
		for (char c = i.first(); c != CharacterIterator.DONE; c = i.next())
		{
			if (!Character.isDigit(c) && (c != '.') && (c != '-'))
			    return false;
		}

		return true;
	}

	public static boolean isLengthInRange(Object object, int minLength, int maxLength)
	{
		boolean result = false;

		if (object != null)
		{
			int length = object.toString().trim().length();
			result =  (length >= minLength && length <= maxLength);
		}
		
		return result;
	}
}
