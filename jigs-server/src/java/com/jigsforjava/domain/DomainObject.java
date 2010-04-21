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
package com.jigsforjava.domain;

import java.util.ArrayList;
import java.util.List;

import com.jigsforjava.util.Copyable;
import com.jigsforjava.util.Validatable;

/**
 * This is the root object to all domain objects in the application framework.
 * 
 * @author Todd Fredrich
 * @created  December 10, 2004
 * @version $Revision: 1.4 $
 */
public abstract class DomainObject<T extends DomainObject<T>>
implements Validatable, Copyable<T>
{
	//SECTION: CONSTANTS

    private static final int DEFAULT_VALIDATION_ERROR_LIST_SIZE = 0;

    
    //SECTION: CONSTRUCTION

	public DomainObject()
	{
		super();
		initialize();
	}

	/**
	 * Called at creation time.  All sub-classes must override and call super.initialize();
	 */
	protected void initialize()
	{
	}
	

	//SECTION: ACCESSORS/MUTATORS

	public String getSimpleClassName()
	{
		return this.getClass().getSimpleName();
	}
	
	public boolean isValid()
	{
		return validate().isEmpty();
	}


	// SECTION: VALIDATABLE

	/**
	 * Subclasses implement, calling super.validate(), if applicable.
	 * 
	 * @see com.jigsforjava.util.Validatable#validate()
	 */
	public List<ValidationError> validate()
	{
		List<ValidationError> result = new ArrayList<ValidationError>(DEFAULT_VALIDATION_ERROR_LIST_SIZE);
		validateInto(result);
		return result;
	}
	
	protected void validateInto(List<ValidationError> validationErrors)
	{
		// Default implementation does nothing.
	}
	
	
	// SECTION: COPYABLE
	
	public T copy()
	{
		// TODO: implement shallow copy.
		return null;
	}
}
