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

import com.jigsforjava.web.controller.MultiActionController;


/**
 * @author toddf
 * @since May 12, 2008
 */
public class ViewDefinition
{
	// SECTION: VARIABLES

	private Class<? extends MultiActionController> controllerClass;
	private String actionName;


	// SECTION: CONSTRUCTORS

	public ViewDefinition()
	{
		super();
	}

	public ViewDefinition(Class<? extends MultiActionController> controller, String action)
	{
		super();
		setControllerClass(controller);
		setActionName(action);
	}


	// SECTION: ACCESSORS/MUTATORS

	public String getActionName()
	{
		return actionName;
	}

	public void setActionName(String action)
	{
		this.actionName = action;
	}

	public Class<? extends MultiActionController> getControllerClass()
	{
		return controllerClass;
	}

	/**
	 * 
	 * @param controllerClass the fully-qualified name of a controller.
	 */
	public void setControllerClass(Class<? extends MultiActionController> aClass)
	{
		this.controllerClass = aClass;
	}
}
