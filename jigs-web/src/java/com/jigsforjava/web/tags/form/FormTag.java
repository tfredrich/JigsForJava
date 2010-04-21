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

import com.jigsforjava.web.tags.UrlBasedTag;

/**
 * Creates an HTML form element with an action URL based on the requested controller/action pair.
 * This enables plain HTML submit and/or button elements to work appropriately.
 * <p/>
 * Alternatively, if you want to use a plain old HTML form element, then don't set the action on it
 * and utilize SubmitButtonTag to perform the requested action(s).
 * 
 * @author toddf
 * @since Sep 10, 2008
 * @see SubmitButtonTag
 */
public class FormTag
extends UrlBasedTag
{
	// SECTION: CONSTANTS

	private static final String ACTION_ATTRIBUTE = "action";
	private static final String METHOD_ATTRIBUTE = "method";
	private static final String TARGET_ATTRIBUTE = "target";
	private static final String ENCTYPE_ATTRIBUTE = "enctype";
	private static final String ACCEPT_CHARSET_ATTRIBUTE = "accept-charset";
	private static final String ONSUBMIT_ATTRIBUTE = "onsubmit";
	private static final String ONRESET_ATTRIBUTE = "onreset";
	private static final String AUTOCOMPLETE_ATTRIBUTE = "autocomplete";
	
	private static final String DEFAULT_FORM_NAME = "form";

	
	// SECTION: INSTANCE VARIABLES
	
	private String method;
	private String target;
	private String enctype;
	private String onsubmit;
	private String onreset;
	private String autocomplete;
	private String acceptCharset;

	
	// SECTION: CONSTRUCTOR

	public FormTag()
	{
		super("form");
		setName(DEFAULT_FORM_NAME);
	}


	// SECTION: ACCESSORS/MUTATORS

	public String getMethod()
    {
    	return method;
    }

	public void setMethod(String method)
    {
    	this.method = method;
    }

	public String getTarget()
    {
    	return target;
    }

	public void setTarget(String target)
    {
    	this.target = target;
    }

	public String getEnctype()
    {
    	return enctype;
    }

	public void setEnctype(String enctype)
    {
    	this.enctype = enctype;
    }

	public String getOnsubmit()
    {
    	return onsubmit;
    }

	public void setOnsubmit(String onsubmit)
    {
    	this.onsubmit = onsubmit;
    }

	public String getOnreset()
    {
    	return onreset;
    }

	public void setOnreset(String onreset)
    {
    	this.onreset = onreset;
    }

	public String getAutocomplete()
    {
    	return autocomplete;
    }

	public void setAutocomplete(String autocomplete)
    {
    	this.autocomplete = autocomplete;
    }

	public String getAcceptCharset()
    {
    	return acceptCharset;
    }

	public void setAcceptCharset(String acceptCharset)
    {
    	this.acceptCharset = acceptCharset;
    }

	
	// SECTION: SUB-CLASS RESPONSIBILITIES

	@Override
    protected int writeTagContents(TagWriter tagWriter) throws Exception
    {
		tagWriter.writeAttribute(ACTION_ATTRIBUTE, toUrl());
		tagWriter.writeOptionalAttributeValue(METHOD_ATTRIBUTE, getMethod());
		tagWriter.writeOptionalAttributeValue(TARGET_ATTRIBUTE, getTarget());
		tagWriter.writeOptionalAttributeValue(ENCTYPE_ATTRIBUTE, getEnctype());
		tagWriter.writeOptionalAttributeValue(ACCEPT_CHARSET_ATTRIBUTE, getAcceptCharset());
		tagWriter.writeOptionalAttributeValue(ONSUBMIT_ATTRIBUTE, getOnsubmit());
		tagWriter.writeOptionalAttributeValue(ONRESET_ATTRIBUTE, getOnreset());
		tagWriter.writeOptionalAttributeValue(AUTOCOMPLETE_ATTRIBUTE, getAutocomplete());
	    return EVAL_BODY_INCLUDE;
    }
}
