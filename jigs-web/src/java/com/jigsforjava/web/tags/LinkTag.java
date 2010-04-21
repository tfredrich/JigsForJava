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
package com.jigsforjava.web.tags;

import org.springframework.web.servlet.tags.form.TagWriter;

/**
 * @author toddf
 * @since Sep 9, 2008
 */
public class LinkTag
extends UrlBasedTag
{
	// SECTION: CONSTANTS
	
	private static final String HREF_ATTRIBUTE = "href";
	private static final String TARGET_ATTRIBUTE = "target";
	
	
	// SECTION: INSTANCE VARIABLES
	
	private String target;

	
	// SECTION: CONSTRUCTOR

	public LinkTag()
	{
		super("a");
	}
	
	
	// SECTION: ACCESSORS/MUTATORS
	
	public String getTarget()
	{
		return target;
	}
	
	public void setTarget(String value)
	{
		this.target = value;
	}

	
	// SECTION: SUB-CLASS RESPONSIBILITIES

	@Override
	protected int writeTagContents(TagWriter tagWriter) throws Exception
	{
    	tagWriter.writeAttribute(HREF_ATTRIBUTE, toUrl());
    	tagWriter.writeOptionalAttributeValue(TARGET_ATTRIBUTE, getTarget());
		return EVAL_BODY_INCLUDE;
	}
}
