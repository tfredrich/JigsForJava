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
package com.jigsforjava.string;

import java.util.Map;

/**
 * @author Todd Fredrich
 * @since Feb 16, 2005
 * @version $Revision: 1.1 $
 */
public class MapMessageFormat
{
	private final static String DEFAULT_END_DELIMITER = "}";
	private final static String DEFAULT_START_DELIMITER = "{";

	// PROTOCOL: VARIABLES
	
	private String endDelimiter;
	private String startDelimiter;
	
	public MapMessageFormat()
	{
		this(DEFAULT_START_DELIMITER, DEFAULT_END_DELIMITER);
	}

	public MapMessageFormat(String startDelimiter, String endDelimiter)
	{
		setStartDelimiter(startDelimiter);
		setEndDelimiter(endDelimiter);
	}
	
	// PROTOCOL: ACCESSING

	public String getEndDelimiter()
	{
		return endDelimiter;
	}

	public String getStartDelimiter()
	{
		return startDelimiter;
	}

	public void setEndDelimiter(String delimiter)
	{
		endDelimiter = delimiter;
	}

	public void setStartDelimiter(String delimiter)
	{
		startDelimiter = delimiter;
	}

	/**
	 * 
	 * @param string The string containing named tokens to replace with parameters.
	 * @param parameters A map of strings or I18nKeys, keyed by parameter names.
	 * @return An internationalized string with parameter tokens replaced with values.
	 */
	public String format(String string, Map<String, String> parameters)
	{
		String result = string;
		for (String key : parameters.keySet())
		{
			 String value = parameters.get(key);
			 result = result.replaceAll(constructParameterName(key), value);
		}
		
		
		return result;
	}

	// PROTOCOL: UTILITY
	
	/**
	 * @param key
	 * @return String with key contained within start delimiter and end delimiter
	 */
	private String constructParameterName(String key)
	{
		StringBuffer sb = new StringBuffer();
		sb.append('\\');
		sb.append(getStartDelimiter());
		sb.append(key);
		sb.append('\\');
		sb.append(getEndDelimiter());
		return sb.toString();
	}
}
