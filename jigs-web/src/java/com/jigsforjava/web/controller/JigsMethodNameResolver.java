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
package com.jigsforjava.web.controller;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.mvc.multiaction.MethodNameResolver;
import org.springframework.web.servlet.mvc.multiaction.NoSuchRequestHandlingMethodException;

import com.jigsforjava.web.HttpServletRequestUtils;

/**
 * @author toddf
 * @since Aug 11, 2008
 */
public class JigsMethodNameResolver
implements MethodNameResolver
{
	//SECTION: CONSTANTS

	public static final String ACTION_PARAMETER_PREFIX = "method:";

	
	//SECTION: METHOD NAME RESOLVER

	/**
	 * Determines the handler method name based upon the type of request and the
	 * request URI. The method prefix for HTTP GET requests is openFor and the
	 * method prefix for HTTP POST requests is on.
	 * 
	 * @param request the current HTTP request.
	 * @return Assuming the request URI is of the form /login/login.action, and
	 *         the request is an HTTP GET request, openForLogin will be
	 *         returned. If the request is an HTTP POST request, onLogin will be
	 *         returned.
	 */
	public final String getHandlerMethodName(HttpServletRequest request)
	throws NoSuchRequestHandlingMethodException
	{
		final String GET_REQUEST_METHOD_PREFIX = "openFor";
		final String POST_REQUEST_METHOD_PREFIX = "on";

		String methodPrefix;
		String actionName = null;

		if (HttpServletRequestUtils.isGetMethod(request))
		{
			methodPrefix = GET_REQUEST_METHOD_PREFIX;
		}
		else if (HttpServletRequestUtils.isPostMethod(request))
		{
			methodPrefix = POST_REQUEST_METHOD_PREFIX;
			actionName = findActionName(request);
		}
		else
		{
			return null;
		}

		String relativeURI = request.getRequestURI().replace(request.getContextPath(), "");

		if (actionName == null)
		{
			int index = relativeURI.lastIndexOf('/');
			String actionWithExtension = relativeURI.substring(index + 1);
			index = actionWithExtension.lastIndexOf('.');
			actionName = actionWithExtension.substring(0, index);
		}

		String leadingCharacter = actionName.substring(0, 1);
		return methodPrefix
		    + actionName.replaceFirst(leadingCharacter, leadingCharacter.toUpperCase());
	}

	
	//SECTION: UTILITY

	@SuppressWarnings("unchecked")
    private String findActionName(HttpServletRequest request)
	{
		for (Enumeration<String> i = request.getParameterNames(); i.hasMoreElements();)
		{
			String name = i.nextElement();

			if (name.startsWith(ACTION_PARAMETER_PREFIX))
			{
				return name.substring(ACTION_PARAMETER_PREFIX.length());
			}
		}

		return null;
	}
}
