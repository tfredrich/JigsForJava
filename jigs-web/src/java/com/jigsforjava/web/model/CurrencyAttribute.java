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
 * Domain object attribute that represents an 'double' integral type.
 * 
 * @author Todd Fredrich
 * @since  Jun 22, 2004
 * @version $Revision: 1.3 $
 */
public class CurrencyAttribute
extends AttributeModel<CurrencyAttribute>
{
	private Double contents;

	public CurrencyAttribute(String name)
	{
		this(name, 0.0d);
	}
	
	public CurrencyAttribute(String name, double value)
	{
		super(name, "currency");
		initialize(value);
	}
	
	public void initialize(double value)
	{
		setValue(value);
		setModified(false);
	}

	public int compareTo(CurrencyAttribute toCompare)
	{
		int comparison = 0;
		
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
	
	public Double getValue()
	{
		return contents;
	}
	
	public void setValue(double value)
	{
		if (getValue() == null || getValue().doubleValue() != value)
		{
			contents = new Double(value);
			setModified(true);
		}
	}
	
	public void setValue(Double value)
	{
		if (value != null)
		{
			setValue(value.doubleValue());
		}
		else if (getValue() != null)
		{
			contents = null;
			setModified(true);
		}
	}
	
	/**
	 * 
	 * @see com.jigsforjava.web.model.AttributeModel#setValue(java.lang.String)
	 */
	@Override
	public void setValue(String valueString)
		throws PresentationModelException
	{
		try
		{
			if (valueString != null)
			{
				setValue(Double.parseDouble(valueString));
			}
			else
			{
				setValue((Double) null);
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
			result = i18n.localizeAsCurrency(getValue().doubleValue());
		
		return result;
	}

	public String toString()
	{
		return contents.toString();
	}
}
