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

import java.util.List;

import com.jigsforjava.domain.ValidationError;
import com.jigsforjava.i18n.I18n;
import com.jigsforjava.string.StringConstants;
import com.jigsforjava.string.StringUtils;

/**
 * Domain object attribute that represents a 'String' type.
 * 
 * @author Todd Fredrich
 * @since  Jun 22, 2004
 * @version $Revision: 1.4 $
 */
public class StringAttribute
extends AttributeModel<StringAttribute>
{
	String contents = null;

	public StringAttribute(String name)
	{
		this(name, null);
	}
	
	public StringAttribute(String name, String value)
	{
		super(name, "String");
		
		if (value != null)
			initialize(value);
		else
			initialize(StringConstants.EMPTY);
	}
	
	protected void initialize(String string)
	{
		setValue(string);
		setModified(false);
	}

	public int compareTo(StringAttribute attribute)
	{
		StringAttribute toCompare = (StringAttribute) attribute;
		return getValue().compareTo(toCompare.getValue());
	}
	
	public String getValue()
	{
		return contents;
	}
	
	/**
	 * Sets the value of the string, if it is editable.  Sets the modified flag if the value being set is different than the 
	 * previous value.
	 * 
	 * @see com.jigsforjava.web.model.AttributeModel#setValue(java.lang.String)
	 */
	public void setValue(String value)
	{
		if (isEditable() 
			&& ((getValue() == null  || !getValue().equals(value))))
		{
			contents = value;
			setModified(true);
		}
	}

	/**
	 * @see com.jigsforjava.web.model.AttributeModel#localize(com.jigsforjava.i18n.I18n)
	 */
	public String localize(I18n i18n)
	{
		return i18n.localize(getValue());
	}
	
	public String toString()
	{
		return getValue();
	}
	
	public void validateInto(List<ValidationError> validationErrors)
	{
		if (isRequired() && StringUtils.isNullOrEmpty(getValue()))
		{
			validationErrors.add(new ValidationError(this, getAttributeName() + " requires an entry."));
		}
	}
}
