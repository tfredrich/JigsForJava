/*
	Copyright 2004-2008 Strategic Gains, Inc.
	
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
package com.jigsforjava.string;

import java.io.CharArrayWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang.StringEscapeUtils;

/**
 * @author Todd Fredrich
 * @since Apr 6, 2004
 * @version $Revision: 1.2 $
 */
public final class StringUtils
extends org.springframework.util.StringUtils
{
	// SECTION: CONSTANTS

	/**
     * Then character encoding to use when en/de-coding a string.
     */
    private static final String URL_ENCODING = "UTF-8";

    /**
     * The prefix used when creating an accessor method name.
     */
    private static final String ACCESSOR_PREFIX = "get";

    /**
     * The prefix used when creating a mutator method name.
     */
	private static final String MUTATOR_PREFIX = "set";
	
	/**
	 * Regular expressions used for creating singular forms of words.
	 */
	private static final Map<String, String> SINGULARIZATION_RULES = new LinkedHashMap<String, String>();
	static
	{
		SINGULARIZATION_RULES.put("(\\w*)(ox)en$", "$1$2");
		SINGULARIZATION_RULES.put("(\\w+)(x|ch|ss)es$", "$1$2");
		SINGULARIZATION_RULES.put("(\\w*)series$", "$1series");
		SINGULARIZATION_RULES.put("(\\w*)(cookie)s$", "$1$2");
		SINGULARIZATION_RULES.put("(\\w+)([^aeiou])ies", "$1$2y");
		SINGULARIZATION_RULES.put("(\\w+)([^l])ves", "$1$2fe");
		SINGULARIZATION_RULES.put("(\\w+)([ll])ves", "$1$2f");
		SINGULARIZATION_RULES.put("(\\w*)buses$", "$1bus");
		SINGULARIZATION_RULES.put("(\\w+)(ses)$", "$1sis");
		SINGULARIZATION_RULES.put("(\\w*)people$", "$1person");
		SINGULARIZATION_RULES.put("(\\w*)children$", "$1child");
		SINGULARIZATION_RULES.put("(\\w*)feet$", "$1foot");
		SINGULARIZATION_RULES.put("(\\w*)teeth$", "$1tooth");
		SINGULARIZATION_RULES.put("(\\w*)men$", "$1man");
		SINGULARIZATION_RULES.put("(\\w*)mice$", "$1mouse");
		SINGULARIZATION_RULES.put("(\\w*)geese$", "$1goose");
	}

	/**
	 * Regular expressions used to create plural forms of words.
	 */
	private static final Map<String, String> PLURALIZATION_RULES = new LinkedHashMap<String, String>();
	static
	{
		PLURALIZATION_RULES.put("(ox)$", "$1en");
		PLURALIZATION_RULES.put("(\\w+)(x|ch|ss|sh)$", "$1$2es");
		PLURALIZATION_RULES.put("(\\w+)([^aeiou])y$", "$1$2ies");
		PLURALIZATION_RULES.put("(\\w*)(f)$", "$1ves");
		PLURALIZATION_RULES.put("(\\w*)(fe)$", "$1ves");
		PLURALIZATION_RULES.put("(\\w+)(sis)$", "$1ses");
		PLURALIZATION_RULES.put("(\\w*)person$", "$1people");
		PLURALIZATION_RULES.put("(\\w*)child$", "$1children");
		PLURALIZATION_RULES.put("(\\w*)series$", "$1series");
		PLURALIZATION_RULES.put("(\\w*)foot$", "$1feet");
		PLURALIZATION_RULES.put("(\\w*)tooth$", "$1teeth");
		PLURALIZATION_RULES.put("(\\w*)bus$", "$1buses");
		PLURALIZATION_RULES.put("(\\w*)man$", "$1men");
		PLURALIZATION_RULES.put("(\\w*)mouse$", "$1mice");
		PLURALIZATION_RULES.put("(\\w*)goose$", "$1geese");
		PLURALIZATION_RULES.put("(\\w*)moose$", "$1moose");
	}


	// SECTION: TESTING

	/**
	 * Returns true if a String is null or empty.
	 * 
	 * @param string the String to check
	 * @return boolean true if <code> string == null || string.length() < 1 </code>
	 */
	public static boolean isNullOrEmpty(String string)
	{
		return ((string == null || string.equals("null"))
			|| (string.trim().length() < 1));
	}

	/**
	 * Returns true if a String is not null and not empty.
	 * 
	 * @param string the String to check
	 * @return boolean false if <code> string == null || string.length() < 1 </code>
	 */
	public static boolean isNotNullNorEmpty(String string)
	{
		return !isNullOrEmpty(string);
	}

	/**
	 * Compares two String instances, allowing null in either or both strings.
	 * If both strings are null, they are considered equal. If only one string
	 * is null, the non-null string is considered greater. If both strings are
	 * non-null, this method returns the results of string1.compareTo(string2).
	 * 
	 * @param string1 a String instance to compare. May be null.
	 * @param string2 a String instance to compare. May be null.
	 * @return <0 if string1 < string2, 0 if string1.equals(string2), >0 if
	 *         string1 > string2.
	 */
	public static int compareTo(String string1, String string2)
	{
		int result = 0;

		if (string1 != null && string2 == null)
			result = 1;
		else if (string1 == null && string2 != null)
			result = -1;
		else if (string1 != null && string2 != null)
		    result = string1.compareTo(string2);

		return result;
	}

	/**
	 * Checks two string instances for equality, allowing for null in either or
	 * both strings. If both strings are null, they are considered equal.
	 * 
	 * @param string1 a String instance to compare. May be null.
	 * @param string2 a String instance to compare. May be null.
	 * @return true if the String instances are lexically equal. Otherwise
	 *         false.
	 * @see compareTo(String, String)
	 */
	public static boolean areEqual(String string1, String string2)
	{
		return (compareTo(string1, string2) == 0);
	}

	/**
	 * Checks two string instances for equality without case sensitivity,
	 * allowing for null in either or both strings. If both strings are null,
	 * they are considered equal.
	 * 
	 * @param string1 a String instance to compare. May be null.
	 * @param string2 a String instance to compare. May be null.
	 * @return true if the String instances are lexically equal. Otherwise
	 *         false.
	 * @see compareTo(String, String)
	 */
	public static boolean areEqualIgnoringCase(String string1, String string2)
	{
		return (compareTo((string1 == null ? string1 : string1.toLowerCase()),
			(string2 == null ? string2 : string2.toLowerCase())) == 0);
	}
	
	/**
	 * Returns true only if aString is not null and is of zero length after
	 * trimming.
	 * 
	 * @param aString a String to check for emptiness
	 * @return true if the string is empty, but not null.
	 */
	public static boolean isEmpty(String aString)
	{
		return (aString != null && aString.trim().length() == 0);
	}
	
	
	//SECTION: CONVENIENCE

    /**
     * <p>Utilizes the Jakarta Commons Lang library to escape the characters
     * in a String using HTML entities.</p>
     *
     * <p>
     * For example:
     * </p> 
     * <p><code>"bread" & "butter"</code></p>
     * becomes:
     * <p>
     * <code>&amp;quot;bread&amp;quot; &amp;amp; &amp;quot;butter&amp;quot;</code>.
     * </p>
     * 
     * @param string is the string to encode.
     * @see StringEscapeUtils
     */
	public static String htmlEncode(String string)
	{
		return StringEscapeUtils.escapeHtml(string);
	}
	
	public static String urlDecode(String string)
	{
		try
        {
	        return URLDecoder.decode(string, URL_ENCODING);
        }
        catch (UnsupportedEncodingException e)
        {
	        e.printStackTrace();
        }
        
        return string;
	}
	
	public static String urlEncode(String string)
	{
		try
        {
	        return URLEncoder.encode(string, URL_ENCODING);
        }
        catch (UnsupportedEncodingException e)
        {
	        e.printStackTrace();
        }
        
        return string;
	}

	/**
	 * Appends the String argument to the StringBuilder, followed by the
	 * delimiter, iff the String argument is not null or empty.
	 * 
	 * @param element the String to append
	 * @param delimiter the delimiter String to append following the element String
	 * @param buffer the buffer to append to
	 */
	public static boolean appendElement(String element, String delimiter, StringBuilder buffer)
	{
		if (isNotNullNorEmpty(element))
		{
			buffer.append(element);
			buffer.append(delimiter);
			return true;
		}

		return false;
	}

	/**
	 * Parse a string, via a given delimiter, into an array of strings.
	 * 
	 * @param string the string to parse.
	 * @param delimiter a delimiter to use in parsing the string.
	 * @return an array of strings.
	 */
	public static String[] parseString(String string, String delimiter)
	{
		String delim = (delimiter.equals(".") ? "\\." : delimiter);
		String[] strings = string.split(delim);

		if (strings.length == 0 && string.length() > 0)
		{
			strings = new String[]
			{
				string
			};
		}

		return strings;
	}

	/**
     * @param propertyName
     * @return a String prefixed with the accessor prefix in camel case.
     */
    public static String toAccessorName(String propertyName)
    {
    	StringBuilder sb = new StringBuilder(ACCESSOR_PREFIX);
    	sb.append(StringUtils.capitalize(propertyName));
    	return sb.toString();
    }

	public static String toCamelCase(String stringValue, String delimiter)
	{
		StringBuffer result = new StringBuffer(stringValue.length());
		String[] strings = parseString(stringValue.toLowerCase(), delimiter);

		for (int i = 0; i < strings.length; ++i)
		{
			char[] characters = strings[i].toCharArray();

			if (characters.length > 0)
			{
				characters[0] = Character.toUpperCase(characters[0]);
				result.append(characters);
			}
		}

		return result.toString();
	}

	/**
     * @param propertyName
     * @return a String prefixed with the mutator prefix in camel case.
     */
    public static String toMutatorName(String propertyName)
    {
    	StringBuilder sb = new StringBuilder(MUTATOR_PREFIX);
    	sb.append(StringUtils.capitalize(propertyName));
    	return sb.toString();
    }

	/**
	 * Converts a camel case string into a lower-case, delimiter-separated
	 * string. For example, a call of toSeparatedString("ACamelCaseString, '_')
	 * returns a_camel_case_string.
	 * 
	 * @param camelCaseString
	 *            a String in camel case to delimit.
	 * @param delimiter
	 *            the character used to separate the string.
	 * @return a String where capitals in the original are prefixed with the
	 *         given delimiter.
	 */
	public static String toSeparatedString(String camelCaseString, char delimiter)
	{
		CharArrayWriter result = new CharArrayWriter();
		char[] chars = camelCaseString.toCharArray();

		for (int i = 0; i < chars.length; ++i)
		{
			if (chars[i] != delimiter && Character.isUpperCase(chars[i])
			    && i > 0)
			{
				if ((i < chars.length - 1)
				    && (!Character.isUpperCase(chars[i + 1]) && chars[i + 1] != delimiter)
				    && chars[i - 1] != delimiter)
					result.write(delimiter);
				else if (!Character.isUpperCase(chars[i - 1])
				    && chars[i - 1] != delimiter) result.write(delimiter);
			}

			result.write(chars[i]);
		}

		return result.toString().toLowerCase();
	}

	/**
	 * Converts the string (presumably a single word) from plural into singular
	 * form.
	 * 
	 * @param word a single word in plural form.
	 * @return the singular form of the word.
	 */
	public static String singularize(String word)
	{
		if (isNullOrEmpty(word)) return word;

		for (Entry<String, String> rule : SINGULARIZATION_RULES.entrySet())
		{
			final String pattern = rule.getKey().toString();
			final String replacement = rule.getValue().toString();

			if (word.matches(pattern))
			{
				return word.replaceFirst(pattern, replacement);
			}
		}

		return word.replaceFirst("([\\w]+)s$", "$1");
	}

	/**
	 * Converts the string (presumably a single word) from singular into plural
	 * form.
	 * 
	 * @param word a single word in singular form.
	 * @return the plural form of the word.
	 */
	public static String pluralize(String word)
	{
		if (isNullOrEmpty(word)) return word;

		for (Entry<String, String> rule : PLURALIZATION_RULES.entrySet())
		{
			final String pattern = rule.getKey().toString();
			final String replacement = rule.getValue().toString();

			if (word.matches(pattern))
			{
				return word.replaceFirst(pattern, replacement);
			}
		}

		return word.replaceFirst("([\\w]+)([^s])$", "$1$2s");
	}

	/**
	 * Truncates the string (presumably a large amount of text) down to the
	 * first whole word after the length parameter and then adds an ellipsis
	 * (...) if there is additional text.
	 * 
	 * @param longString
	 *            a block of text
	 * @param length
	 *            the desired length of the resulting text string
	 * @return
	 */
	public static String truncateWithEllipsis(String longString, int length)
	{
		if (isNotNullNorEmpty(longString))
		{
			// check length of text and cut off at first whole word after
			// specified nbr of characters (Jim's bytes)
			if (longString.length() > length)
			{
				boolean checkingForWholeWord = true;
				int characterPosition = length;

				while (checkingForWholeWord)
				{
					if ((longString.length() > characterPosition)
					    && (isNotNullNorEmpty(String.valueOf(longString
					        .charAt(characterPosition)))))
					{
						characterPosition++;
					}
					else
					{
						checkingForWholeWord = false;
					}
				}

				String continuationIndicator = StringConstants.EMPTY;
				if (longString.length() > characterPosition)
				{
					continuationIndicator = "...";
				}

				longString = longString.substring(0, characterPosition)
				    + continuationIndicator;
			}
		}

		return longString;
	}
}
