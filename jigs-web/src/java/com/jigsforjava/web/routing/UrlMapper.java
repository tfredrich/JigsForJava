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
package com.jigsforjava.web.routing;

import java.util.Map;

import com.jigsforjava.web.controller.MultiActionController;

/**
 * @author toddf
 * @since Sep 5, 2008
 */
public interface UrlMapper
{
	public String getControllerName(String basePath);
	public String getActionName(String basePath);
	public String toUrl(Class<? extends MultiActionController> controllerClass, String actionName, Map<String, Object> parameters);
	public String toUrl(String controllerBaseName, String actionName, Map<String, Object> parameters);
	public String toView(Class<? extends MultiActionController> controllerClass, String actionName);
}
