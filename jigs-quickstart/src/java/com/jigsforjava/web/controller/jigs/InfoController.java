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
package com.jigsforjava.web.controller.jigs;

import java.util.HashMap;
import java.util.Map;

import com.jigsforjava.web.context.ActionContext;
import com.jigsforjava.web.context.ViewContext;
import com.jigsforjava.web.controller.MultiActionController;

/**
 * @author toddf
 * @since Sep 9, 2008
 */
public class InfoController
extends MultiActionController
{
	public ViewContext openForView(ActionContext context)
	{
		System.out.println("In InfoController.openForView()");
		return null;
	}
	
	public ViewContext openForEdit(ActionContext context)
	{
		System.out.println("In InfoController.openForEdit()");
		Map<String, Object> modelMap = new HashMap<String, Object>();
		modelMap.put("textarea", "TextArea contents...");
		return newViewContext("edit", modelMap);
	}
	
	public ViewContext onSave(ActionContext context)
	{
		System.out.println("In InfoController.onSave()");
		return newViewContext("view");
	}
	
	public ViewContext onCancel(ActionContext context)
	{
		System.out.println("In InfoController.onCancel()");
		return newViewContext("view");
	}
}
