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
package com.jigsforjava.web.model;

import com.jigsforjava.i18n.I18n;
import com.jigsforjava.web.model.BooleanAttribute;
import com.jigsforjava.web.model.DoubleAttribute;
import com.jigsforjava.web.model.PresentationModel;
import com.jigsforjava.web.model.StringAttribute;

/**
 * @author Todd Fredrich
 * @since Mar 8, 2005
 * @version $Revision: 1.1 $
 */
public class PersonModel
extends PresentationModel
{
	// PROTOCOL: CONSTANTS
	private static final String NAME_ASPECT = "name";
	private static final String PASSWORD_ASPECT = "password";
	private static final String ACCOUNT_BALANCE_ASPECT = "accountBalance";
	private static final String IS_AMAZED_ASPECT = "isAmazed";
	
	// PROTOCOL: VARIABLES

	public PersonModel()
	{
		this(new Person());
	}

	public PersonModel(Person person)
	{
		super();
		initialize(person);
	}
	
	private void initialize(Person person)
	{
		addAttribute(new StringAttribute(NAME_ASPECT, person.getName()));
		addAttribute(new StringAttribute(PASSWORD_ASPECT, person.getPassword()));
		addAttribute(new DoubleAttribute(ACCOUNT_BALANCE_ASPECT, person.getAccountBalance()));
		addAttribute(new BooleanAttribute(IS_AMAZED_ASPECT, person.isAmazed()));
	}

	// PROTOCOL: ACCESSING
	
	public String getName()
	{
		return ((StringAttribute) getAttribute(NAME_ASPECT)).getValue();
	}
	
	public void setName(String value)
	{
		((StringAttribute) getAttribute(NAME_ASPECT)).setValue(value);
	}
	
	public String getPassword()
	{
		return ((StringAttribute) getAttribute(PASSWORD_ASPECT)).getValue();
	}
	
	public void setPassword(String value)
	{
		((StringAttribute) getAttribute(PASSWORD_ASPECT)).setValue(value);
	}

	public boolean isAmazed()
	{
		return ((BooleanAttribute) getAttribute(IS_AMAZED_ASPECT)).getValue().booleanValue();
	}

	public void setAmazed(boolean value)
	{
		((BooleanAttribute) getAttribute(IS_AMAZED_ASPECT)).setValue(value);
	}

	/**
	 * @see com.jigsforjava.web.model.AttributeModel#localize(com.jigsforjava.i18n.I18n)
	 */
	public String localize(I18n i18n)
	{
		return null;
	}

}
