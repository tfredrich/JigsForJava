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

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jigsforjava.util.Assert;

/**
 * Wraps all the data for a AbstractMultiActionController handler method, created in
 * {@link AbstractMultiActionController}.  ActionContext is immutable.
 * 
 * @author Todd Fredrich
 * @since May 12, 2008
 */
public class ActionContext
{
	// SECTION: VARIABLES

	private HttpServletRequest request;
	private HttpServletResponse response;
	private String currentAction;
	private Method handlerMethod;


	// SECTION: CONSTRUCTORS

	public ActionContext(HttpServletRequest request, HttpServletResponse response)
	{
		this(request, response, null);
	}

	public ActionContext(HttpServletRequest request, HttpServletResponse response, Method handlerMethod)
	{
		super();

		setRequest(request);
		setResponse(response);
		setHandlerMethod(handlerMethod);
	}

	
	// SECTION: ACCESSORS

	public String getRequestParameter(String parameterKey)
	{
		return getRequest().getParameter(parameterKey);
	}

	public final HttpServletRequest getRequest()
	{
		return request;
	}

	public final HttpServletResponse getResponse()
	{
		return response;
	}

	public HttpSession getSession()
	{
		return getRequest().getSession();
	}

	public Method getHandlerMethod()
    {
    	return handlerMethod;
    }
	
	public boolean hasHandlerMethod()
	{
		return (getHandlerMethod() != null);
	}
	
	public boolean hasHandlerAnnotation(Class<? extends Annotation> annotationClass)
	{
		return getHandlerMethod().isAnnotationPresent(annotationClass);
	}
	
	
	// SECTION: MUTATORS - PRIVATE

	private void setHandlerMethod(Method handlerMethod)
    {
    	this.handlerMethod = handlerMethod;
    }

	private void setRequest(HttpServletRequest newValue)
	{
		Assert.notNull(newValue);
		request = newValue;
	}

	private void setResponse(HttpServletResponse newValue)
	{
		Assert.notNull(newValue);
		response = newValue;
	}
}
