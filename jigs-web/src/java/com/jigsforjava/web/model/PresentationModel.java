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
import java.util.HashMap;
import java.util.List;

import com.jigsforjava.domain.ValidationError;
import com.jigsforjava.util.Validatable;

/**
 * Presentation model contains additional (child) presentation models as attributes.
 * 
 * @author Todd Fredrich
 * @since  Jun 22, 2004
 */
public class PresentationModel
implements Validatable
{
	// SECTION: INSTANCE VARIABLES
	
	private HashMap<String, AttributeModel<?>> attributes;


	// SECTION: CONSTRUCTION

	public PresentationModel()
	{
		super();
		initialize();
	}

	
	// SECTION: INITIALIZATION

	/**
	 * Called at creation time to add all the attributes to the attributes list.  All sub-classes
	 * must override and call super.initialize();
	 */
	protected void initialize()
	{
		attributes = new HashMap<String, AttributeModel<?>>();
	}


	// SECTION: VALIDATION

	public List<ValidationError> validate()
	{
		List<ValidationError> errors = new ArrayList<ValidationError>();

		for (AttributeModel<?> attribute : attributes.values())
		{
			attribute.validateInto(errors);
		}
		
		return errors;
	}

	// SECTION: UTILITY - SUBCLASSES

	public AttributeModel<?> addAttribute(AttributeModel<?> attribute)
	{
		return (AttributeModel<?>) attributes.put(attribute.getAttributeName(), attribute);
	}
	
	public AttributeModel<?> getAttribute(String attributeName)
	{
		return (AttributeModel<?>) attributes.get(attributeName);
	}
}
