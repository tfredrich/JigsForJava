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
package com.jigsforjava.web.tags.form;

import org.springframework.web.servlet.tags.form.TagWriter;

import com.jigsforjava.string.StringUtils;
import com.jigsforjava.web.tags.UrlBasedTag;

/**
 * Generates a submit button based on controller/action pair. Use of this tag enables
 * use of generic form tag(s) without action being set.  Thus, use of Spring form tag
 * is OK, as is the plain old HTML form element.  The generated button has an onclick method
 * that sets the action to a URL based on the requested controller/action pair and
 * the form is submitted.
 * <p/>
 * Alternatively, you can use the Jigs form tag, which accepts a controller/action pair.
 * Then use of plain old HTML button (or input) elements are OK, and use of this tag
 * is not needed.  Think of SubmitButtonTag and FormTag as being mutually exclusive.
 * 
 * @author todd
 * @since Sep 10, 2008
 * @see FormTag
 */
public class SubmitButtonTag
extends UrlBasedTag
{
	// SECTION: CONSTANTS

    private static final String ONCLICK_ATTRIBUTE = "onclick";
	private static final String TYPE_ATTRIBUTE = "type";
	private static final String VALUE_ATTRIBUTE = "value";

	private static final String DEFAULT_FORM_NAME = "form";
	private static final String DEFAULT_TYPE = "submit";

	
	// SECTION: INSTANCE VARIABLES

	private String form;
	private String type;
	private String value;
	private String confirm;

	
	// SECTION: CONSTRUCTOR

	public SubmitButtonTag()
	{
		super("input");
		setForm(DEFAULT_FORM_NAME);
		setType(DEFAULT_TYPE);
	}

	
	// SECTION: ACCESSORS/MUTATORS

	public String getConfirm()
    {
    	return confirm;
    }
	
	public boolean hasConfirm()
	{
		return StringUtils.isNotNullNorEmpty(getConfirm());
	}

	public void setConfirm(String confirm)
    {
    	this.confirm = confirm;
    }

	public String getForm()
    {
    	return form;
    }

	public void setForm(String form)
    {
    	this.form = form;
    }

	public String getType()
    {
    	return type;
    }

	public void setType(String type)
    {
    	this.type = type;
    }

	public String getValue()
    {
    	return value;
    }

	public void setValue(String value)
    {
    	this.value = value;
    }

	
	// SECTION: SUB-CLASS RESPONSIBILITIES

	@Override
	protected int writeTagContents(TagWriter tagWriter) throws Exception
	{
		tagWriter.writeAttribute(TYPE_ATTRIBUTE, getType());
		tagWriter.writeAttribute(VALUE_ATTRIBUTE, getValue());
		tagWriter.writeAttribute(ONCLICK_ATTRIBUTE, createOnClick());
		return SKIP_BODY;
	}

	
	// SECTION: UTILITY - PRIVATE

	private String createOnClick()
	{
        StringBuilder result = new StringBuilder();

        if (hasConfirm())
        {
            result.append("if (confirm('");
            result.append(getConfirm());
            result.append("')) { ");
            appendAction(result);
            result.append(" } ");
            return result.toString();
        }

        appendAction(result);
        return result.toString();
	}
	
	private void appendAction(StringBuilder result)
	{
        result.append("document.");
        result.append(getForm());
        result.append(".action='");
        result.append(toUrl());
        result.append("'; document.");
        result.append(getForm());
        result.append(".submit();");
	}
}
