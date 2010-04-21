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

import java.util.Map;

import com.jigsforjava.web.context.JigsWebApplicationContext;
import com.jigsforjava.web.routing.UrlMapper;

/**
 * @author toddf
 * @since Sep 9, 2008
 */
public abstract class UrlBasedTag
extends HtmlBlockTag
{
	// SECTION: INSTANCE VARIABLES

	private String controller;
	private String action;
	private Map<String, Object> parameters;
	
	
	// SECTION: CONSTRUCTOR
	
	public UrlBasedTag(String name)
	{
		super(name);
	}

	
	// SECTION: ACCESSORS/MUTATORS

	public String getController()
    {
    	return controller;
    }

	public void setController(String controller)
    {
    	this.controller = controller;
    }

	public String getAction()
    {
    	return action;
    }

	public void setAction(String action)
    {
    	this.action = action;
    }

	public Map<String, Object> getParameters()
    {
    	return parameters;
    }

	public void setParameters(Map<String, Object> parameters)
    {
    	this.parameters = parameters;
    }
	
	
	// SECTION: UTILITY
	
	protected String toUrl()
	{
		UrlMapper urlMapper = JigsWebApplicationContext.getUrlMapper(getRequest());
		String context = getRequest().getContextPath();
		return urlMapper.toUrl(context + getController(), getAction(), getParameters());
	}
}
