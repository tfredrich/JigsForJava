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
package com.jigsforjava.web.model;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.jigsforjava.i18n.I18n;
import com.jigsforjava.string.StringConstants;

/**
 * @author Todd Fredrich
 * @since Dec 13, 2004
 * @version $Revision: 1.3 $
 */
public class TimestampAttribute
extends DateAttribute
{
	private final static String SET_VALUE_DATE_FORMAT = "yyyy/MM/dd k:m:s:S";
	private final static SimpleDateFormat setValueDateFormat = new SimpleDateFormat(SET_VALUE_DATE_FORMAT);

	/**
	 * @param name
	 */
	public TimestampAttribute(String name)
	{
		super(name);
	}

	/**
	 * @param name
	 * @param value
	 */
	public TimestampAttribute(String name, Date value)
	{
		super(name, value);
	}

	/**
	 * Sets the attribute value from a date/time string.  Value string must be in the format 
	 * "YYYY/MM/DD hours:min:sec:milliseconds" to parse correctly.
	 * 
	 * @see com.jigsforjava.web.model.AttributeModel#setValue(java.lang.String)
	 */
	public void setValue(String valueString)
		throws PresentationModelException
	{
		try
		{
			if (valueString != null)
			{
				setValue(setValueDateFormat.parse(valueString));
			}
			else
			{
				setValue((Date) null);
			}
		}
		catch (ParseException e)
		{
			throw new PresentationModelException(e);
		}
	}
	
	public String localize(I18n i18n)
	{
		String result = StringConstants.EMPTY;

		if (getValue() != null)
			result = i18n.localize(getValue(), DateFormat.LONG);
		
		return result;
	}
}
