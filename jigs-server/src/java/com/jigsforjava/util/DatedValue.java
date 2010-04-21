/*
    Copyright 2008, Strategic Gains, Inc.

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
package com.jigsforjava.util;

import java.util.Date;

/**
 * An abstract implementation of an immutable, dated data item.  Implements
 * Comparable to provide natural ordering on the date, ascending.
 * 
 * @author Todd Fredrich
 * @since May 19, 2008
 */
public abstract class DatedValue<T>
implements Comparable<DatedValue<T>>
{
	// SECTION: VARIABLES
	
	private Date date;
	private T value;
	
	
	// SECTION: CONSTRUCTOR
	
	public DatedValue(Date date, T value)
	{
		super();
		this.date = date;
		this.value = value;
	}
	
	
	// SECTION: ACCESSORS
	
	public Date getDate()
	{
		return date;
	}
	
	public T getValue()
	{
		return value;
	}
	
	
	// SECTION: COMPARABLE
	
	public int compareTo(DatedValue<T> that)
	{
		return this.getDate().compareTo(that.getDate());
	}
}
