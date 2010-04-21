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

/**
 * DataTransferObject attribute that represents a 'boolean' integral type.
 * 
 * @author Todd Fredrich
 * @since  Jun 22, 2004
 * @version $Revision: 1.3 $
 */
public class BooleanAttribute
extends AttributeModel<BooleanAttribute>
{
	private Boolean contents;

	public BooleanAttribute(String name)
	{
		super(name, "boolean");
		initialize(false);
	}
	
	public BooleanAttribute(String name, boolean value)
	{
		super(name, "boolean");
		initialize(value);
	}
	
	protected void initialize(boolean value)
	{
		setValue(value);
		setModified(false);
	}

	public int compareTo(BooleanAttribute toCompare)
	{
		int comparison = 0;
		Boolean v1 = getValue();
		Boolean v2 = toCompare.getValue();

		if (v1 != null && v2 != null)
		{
			if (v1.booleanValue() == v2.booleanValue())
			{
				comparison = 0;
			}
			else if (v1.booleanValue())
			{
				comparison = 1;
			}
			else
			{
				comparison = -1;
			}
		}
		else if (v1 != null)
		{
			comparison = 1;
		}
		else
		{
			comparison = -1;
		}
		
		return comparison;
	}
	
	/**
	 * 
	 * @return Boolean. Never null.
	 */
	public Boolean getValue()
	{
		return contents;
	}
	
	public void setValue(boolean value)
	{
		if (getValue() == null || getValue().booleanValue() != value)
		{
			contents = (value ? Boolean.TRUE : Boolean.FALSE);
			setModified(true);
		}
	}
	
	/**
	 * Set the value parsed from a String.  If the string has a value of "TRUE", "YES", "Y", "T" or "1" (ignoring case),
	 * the attribute value gets set to true.  Otherwise, the value is set to false.
	 * 
	 * @see com.jigsforjava.web.model.AttributeModel#setValue(java.lang.String)
	 */
	@Override
	public void setValue(String valueString)
	{
		if (valueString != null
			&& (valueString.equalsIgnoreCase("TRUE")
			|| valueString.equalsIgnoreCase("YES")
			|| valueString.equalsIgnoreCase("Y")
			|| valueString.equalsIgnoreCase("T")
			|| valueString.equals("1")))
		{
			setValue(true);
		}
		else
		{
			setValue(false);
		}
	}

	/**
	 * @see com.jigsforjava.web.model.AttributeModel#localize(com.jigsforjava.i18n.I18n)
	 */
	public String localize(I18n i18n)
	{
		return i18n.localize(toString());
	}
	
	public String toString()
	{
		return contents.toString();
	}
}
