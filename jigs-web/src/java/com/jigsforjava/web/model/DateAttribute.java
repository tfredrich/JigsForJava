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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.jigsforjava.i18n.I18n;
import com.jigsforjava.string.StringConstants;

/**
 * Domain object attribute that represents a 'Date' type.
 * 
 * @author Todd Fredrich
 * @since  Jun 22, 2004
 * @version $Revision: 1.3 $
 */
public class DateAttribute
extends AttributeModel<DateAttribute>
{
	private final static String SET_VALUE_DATE_FORMAT = "yyyy/MM/dd";
	private final static SimpleDateFormat setValueDateFormat = new SimpleDateFormat(SET_VALUE_DATE_FORMAT);

	protected final static String XML_DATE_FORMAT = "M,d,yyyy,k,m,s,S";
	protected final static SimpleDateFormat xmlDateFormat = new SimpleDateFormat(XML_DATE_FORMAT);

	Date contents = null;

	public DateAttribute(String name)
	{
		this(name, null);
	}
	
	public DateAttribute(String name, Date value)
	{
		super(name, "date");
		
		if (value != null)
			initialize(value);
		else
			initialize(new Date());
	}
	
	protected void initialize(Date date)
	{
		setValue(date);
		setModified(false);
	}
	
	public int compareTo(DateAttribute attribute)
	{
		DateAttribute toCompare = (DateAttribute) attribute;
		return getValue().compareTo(toCompare.getValue());
	}
	
	public Date getValue()
	{
		return contents;
	}
	
	public void setValue(Date value)
	{
		if (value != null)
		{
			if (contents == null || !contents.equals(value))
			{
				contents = new Date(value.getTime());
				setModified(true);
			}
		}
		else if (contents != null)
		{
			contents = null;
			setModified(true);
		}
	}

	/**
	 * Sets the attribute value from a date string.  Value string must be in the format YYYY/MM/DD
	 * to parse correctly.
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

	/**
	 * @see com.jigsforjava.web.model.AttributeModel#localize(com.jigsforjava.i18n.I18n)
	 */
	public String localize(I18n i18n)
	{
		String result = StringConstants.EMPTY;

		if (getValue() != null)
			result = i18n.localize(getValue());
		
		return result;
	}
}
