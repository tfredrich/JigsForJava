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
package com.jigsforjava.web.context;

import java.util.Map;

import org.springframework.web.servlet.ModelAndView;

import com.jigsforjava.web.controller.MultiActionController;
import com.jigsforjava.web.routing.UrlMapper;

/**
 * The return value of a {@link AbstractMultiActionController} handler method.
 * 
 * @author toddf
 * @since May 12, 2008
 */
public class ViewContext
{
	// SECTION: VARIABLES

	private ViewDefinition viewDefinition;
	private Map<String, Object> modelMap;

	
	// SECTION: CONSTRUCTORS

	public ViewContext()
	{
		super();
	}

	public ViewContext(ViewDefinition viewDefinition, Map<String, Object> modelMap)
	{
		this();

		setViewDefinition(viewDefinition);
		setModelMap(modelMap);
	}

	public ViewContext(Class<? extends MultiActionController> controllerClass, String action, Map<String, Object> modelMap)
	{
		this();

		setViewDefinition(new ViewDefinition(controllerClass, action));
		setModelMap(modelMap);
	}


	// SECTION: ACCESSING

	public Map<String, Object> getModelMap()
	{
		return modelMap;
	}

	public void setModelMap(Map<String, Object> modelMap)
	{
		this.modelMap = modelMap;
	}

	public ViewDefinition getViewDefinition()
	{
		return viewDefinition;
	}

	public void setViewDefinition(ViewDefinition viewDefinition)
	{
		this.viewDefinition = viewDefinition;
	}

	public ModelAndView asModelAndUrl()
	{
		return new ModelAndView(urlFor(getViewDefinition()), getModelMap());
	}

	public ModelAndView asModelAndView()
	{
		return new ModelAndView(viewFor(getViewDefinition()), getModelMap());
	}
	
	
	// SECTION: UTILITY - PRIVATE
	
	private String urlFor(ViewDefinition viewDefinition)
	{
		return getUrlMapper().toUrl(viewDefinition.getControllerClass(), viewDefinition.getActionName(), getModelMap());
	}

	private String viewFor(ViewDefinition viewDefinition)
	{
		return getUrlMapper().toView(viewDefinition.getControllerClass(), viewDefinition.getActionName());
	}
	
	private UrlMapper getUrlMapper()
	{
		return JigsWebApplicationContext.getUrlMapper();
	}
}
