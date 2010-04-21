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

import java.util.List;

import com.jigsforjava.domain.DomainObject;
import com.jigsforjava.domain.ValidationError;

/**
 * @author Todd Fredrich
 * @since Mar 8, 2005
 * @version $Revision: 1.1 $
 */
public class Person
extends DomainObject<Person>
{
	private String name;
	private String password;
	private double accountBalance;
	private boolean isAmazed;

	public Person()
	{
		super();
	}

	/**
	 * @return The value of accountBalance.
	 */
	public double getAccountBalance()
	{
		return accountBalance;
	}

	/**
	 * @param accountBalance The value to use in setting accountBalance.
	 */
	public void setAccountBalance(double accountBalance)
	{
		this.accountBalance = accountBalance;
	}
	
	/**
	 * @return The value of isAmazed.
	 */
	public boolean isAmazed()
	{
		return isAmazed;
	}
	
	/**
	 * @param isAmazed The value to use in setting isAmazed.
	 */
	public void setAmazed(boolean isAmazed)
	{
		this.isAmazed = isAmazed;
	}
	
	/**
	 * @return The value of name.
	 */
	public String getName()
	{
		return name;
	}
	
	/**
	 * @param name The value to use in setting name.
	 */
	public void setName(String name)
	{
		this.name = name;
	}
	
	/**
	 * @return The value of password.
	 */
	public String getPassword()
	{
		return password;
	}
	
	/**
	 * @param password The value to use in setting password.
	 */
	public void setPassword(String password)
	{
		this.password = password;
	}
}
