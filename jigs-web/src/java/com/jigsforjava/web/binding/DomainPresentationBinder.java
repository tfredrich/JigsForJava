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
package com.jigsforjava.web.binding;

import java.lang.reflect.Field;
import java.util.HashMap;

import com.jigsforjava.util.ClassUtils;
import com.jigsforjava.web.model.AttributeModel;
import com.jigsforjava.web.model.PresentationModel;
import com.jigsforjava.web.model.StringAttribute;

/**
 * @author toddf
 * @since Aug 18, 2008
 */
public class DomainPresentationBinder
{
	// SECTION: INSTANCE VARIABLES

	private Class<?> targetClass;
	private String[] presentedFields;
	private String[] readOnlyFields;
	private String[] disallowedFields;
	private String[] requiredFields;
	
	private HashMap<Class<?>, Class<?>> presentationTypeMap = new HashMap<Class<?>, Class<?>>();

	
	// SECTION: CONSTRUCTORS
	
	public DomainPresentationBinder(Class<?> targetClass)
	{
		super();
		this.targetClass = targetClass;
	}

	
	// SECTION: ACCESSORS/MUTATORS

	public Class<?> getTargetClass()
    {
    	return targetClass;
    }

	public void setTargetClass(Class<?> targetClass)
    {
    	this.targetClass = targetClass;
    }

	public String[] getPresentedFields()
    {
    	return presentedFields;
    }

	public void setPresentedFields(String[] allowedFields)
    {
    	this.presentedFields = allowedFields;
    }

	public String[] getReadOnlyFields()
    {
    	return readOnlyFields;
    }

	public void setReadOnlyFields(String[] readOnlyFields)
    {
    	this.readOnlyFields = readOnlyFields;
    }

	public String[] getDisallowedFields()
    {
    	return disallowedFields;
    }

	public void setDisallowedFields(String[] disallowedFields)
    {
    	this.disallowedFields = disallowedFields;
    }

	public String[] getRequiredFields()
    {
    	return requiredFields;
    }

	public void setRequiredFields(String[] requiredFields)
    {
    	this.requiredFields = requiredFields;
    }
	
	
	// SECTION: PRESENTATION MODEL
	
	public PresentationModel createPresentationModel(Object target)
	{
		PresentationModel result = new PresentationModel();
		
		HashMap<String, Field> fields = ClassUtils.getAllDeclaredFieldsByName(target.getClass());
		AttributeModel<?> attribute;
		
		for (Field field : fields.values())
		{
			attribute = newAttributeFor(field.getType());
			attribute.setAttributeName(field.getName());
//			attribute.setValue(field.get(target));
			result.addAttribute(attribute);
		}
		
		return result;
	}
	
	// SECTION: BINDING
	
	public void bind(PresentationModel model, Object target)
	{
	}
	
	
	// SECTION: UTILITY - PRIVATE
	
	private AttributeModel<?> newAttributeFor(Class<?> aClass)
	{
		AttributeModel<?> result = null;
		Class<?> attributeClass = presentationTypeMap.get(aClass);
		
		if (attributeClass != null)
		{
			try
            {
	            result = (AttributeModel<?>) attributeClass.newInstance();
            }
            catch (Exception e)
            {
	            e.printStackTrace();
				result = new StringAttribute("empty");
            }
		}
		else
		{
			result = new StringAttribute("empty");
		}
		
		return result;
	}
}
