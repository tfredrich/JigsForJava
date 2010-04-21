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

import java.util.ArrayList;
import java.util.List;

import com.jigsforjava.domain.ValidationError;
import com.jigsforjava.i18n.I18n;
import com.jigsforjava.i18n.Localizable;
import com.jigsforjava.util.Validatable;

/**
 * Base object for presentation model attributes.
 * 
 * @author Todd Fredrich
 * @since  Jun 22, 2004
 */
public abstract class AttributeModel<T>
implements Comparable<T>, Validatable, Localizable
{
	private String helpText;
	private boolean isDisplayable;
	private boolean isEditable;
	private boolean isModified;
	private boolean isRequired;
	private String attributeName;
	private String typeName;

	public AttributeModel(String name, String typeName)
	{
		setAttributeName(name);
		setTypeName(typeName);
		setModified(false);
		setDisplayable(true);
		setEditable(true);
		setRequired(false);
	}

	/**
	 * @return true if the attribute has help text.
	 */
	public boolean hasHelpText()
	{
		return (getHelpText() != null);
	}

	/**
	 * @return true if the attribute value has been changed.
	 */
	public boolean isModified()
	{
		return isModified;
	}

	/**
	 * @return true if the attribute value should be displayed on the user interface.
	 */
	public boolean isDisplayable()
	{
		return isDisplayable;
	}

	/**
	 * @return true if the attribute value can be changed via the user interface.
	 */
	public boolean isEditable()
	{
		return isEditable;
	}

	/**
	 * @return true if the attribute value must be entered on the user interface.
	 */
	public boolean isRequired()
	{
		return isRequired;
	}

	/**
	 * @return true if the attribute value is valid.
	 */
	public boolean isValid()
	{
		return validate().isEmpty();
	}

	/**
	 * @param value
	 */
	public void setModified(boolean value)
	{
		isModified = value;
	}

	/**
	 * @param value
	 */
	public void setDisplayable(boolean value)
	{
		isDisplayable = value;
	}

	/**
	 * @param value
	 */
	public void setEditable(boolean value)
	{
		isEditable = value;
	}

	/**
	 * @param value
	 */
	public void setRequired(boolean value)
	{
		isRequired = value;
	}

	/**
	 * Set the attribute's value from a string.
	 * 
	 * @param valueString The string to be parse into an appropriate value.  May be null.
	 * @throws PresentationModelException if the value string cannot be parsed correctly.
	 */
	public abstract void setValue(String valueString)
		throws PresentationModelException;
	
//	public abstract void setValue(Object object)
//	throws PresentationModelException;
	
	
	public void setValueIfEditable(String valueString)
	throws PresentationModelException
	{
		if (isEditable())
		{
			setValue(valueString);
		}
		else
		{
			throw new PresentationModelException(getAttributeName() + " is not editable");
		}
	}

	/**
	 * @return
	 */
	@Override
	public List<ValidationError> validate()
	{
		List<ValidationError> result = new ArrayList<ValidationError>();
		validateInto(result);
		return result;
	}
	
	protected void validateInto(List<ValidationError> validationErrors)
	{
		// default implementation does nothing.
	}

	/**
	 * @return
	 */
	public String getHelpText()
	{
		return helpText;
	}

	/**
	 * @param string
	 */
	public void setHelpText(String message)
	{
		helpText = message;
	}

	public abstract String localize(I18n i18n);

	/**
	 * Method: getTypeName()
	 * 
	 * @return
	 */
	public String getTypeName()
	{
		return typeName;
	}

	/**
	 * Method: setTypeName()
	 * 
	 * @param string
	 */
	public void setTypeName(String name)
	{
		typeName = name;
	}
	
	public String getAttributeName()
	{
		return attributeName;
	}

	public void setAttributeName(String name)
	{
		this.attributeName = name;
	}
}
