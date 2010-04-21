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

import java.util.List;

import com.jigsforjava.domain.DomainObject;

/**
 * 
 * 
 * @author Todd Fredrich
 * @since  Apr 6, 2004
 */
public class DefaultDomainObject 
extends DomainObject<DefaultDomainObject>
{
	public DefaultDomainObject()
	{
		super();
	}

	/* (non-Javadoc)
     * @see com.jigsforjava.domain.DomainObject#validateInto(java.util.List)
     */
    @Override
    protected void validateInto(List<ValidationError> validationErrors)
    {
    	validationErrors.add(new ValidationError(this, "a generic validation error"));
    }
}
