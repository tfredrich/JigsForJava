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

import com.jigsforjava.i18n.I18n;
import com.jigsforjava.string.StringConstants;

/**
 * Domain object attribute that represents an 'int' integral type.
 * 
 * @author Todd Fredrich
 * @since  Jun 22, 2004
 * @version $Revision: 1.3 $
 */
public class IntegerAttribute
extends AttributeModel<IntegerAttribute>
{
	private Integer contents;

	public IntegerAttribute(String name)
	{
		super(name, "integer");
		initialize(0);
	}
	
	public IntegerAttribute(String name, int value)
	{
		super(name, "integer");
		initialize(value);
	}
	
	protected void initialize(int value)
	{
		setValue(value);
		setModified(false);
	}

	public int compareTo(IntegerAttribute attribute)
	{
		int comparison = 0;
		IntegerAttribute toCompare = (IntegerAttribute) attribute;
		
		if (getValue() != null && toCompare != null)
		{
			comparison = getValue().compareTo(toCompare.getValue());
		}
		else if (getValue() != null)
		{
			comparison = 1;
		}
		else
		{
			comparison = -1;
		}
		
		return comparison;
	}
	
	public Integer getValue()
	{
		return contents;
	}
	
	public void setValue(int value)
	{
		if (getValue() == null || getValue().intValue() != value)
		{
			contents = new Integer(value);
			setModified(true);
		}
	}
	
	public void setValue(Integer value)
	{
		if (value != null)
		{
			setValue(value.intValue());
		}
		else if (getValue() != null)
		{
			contents = null;
			setModified(true);
		}
	}

	public void setValue(String valueString)
		throws PresentationModelException
	{
		try
		{
			if (valueString != null)
			{
				setValue(Integer.parseInt(valueString));
			}
			else
			{
				setValue((Integer) null);
			}
		}
		catch (NumberFormatException e)
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
			result = i18n.localize(getValue().intValue());
		
		return result;
	}

	public String toString()
	{
		return contents.toString();
	}
}
