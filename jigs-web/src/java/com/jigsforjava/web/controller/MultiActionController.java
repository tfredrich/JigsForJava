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

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import org.springframework.web.servlet.mvc.multiaction.MethodNameResolver;
import org.springframework.web.servlet.mvc.multiaction.NoSuchRequestHandlingMethodException;
import org.springframework.web.util.NestedServletException;

import com.jigsforjava.identity.Identifiable;
import com.jigsforjava.web.HttpServletRequestUtils;
import com.jigsforjava.web.context.ActionContext;
import com.jigsforjava.web.context.ViewContext;

/**
 * Currently does not perform binding and/or validation.  Simply supports every action
 * on a form calling a method within this controller.
 * 
 * @author Todd Fredrich
 * @since May 12, 2008
 */
public class MultiActionController
extends AbstractController
{
	// SECTION: CONSTANTS

	protected static final String PAGE_NOT_FOUND = "/view/jsp/pageNotFound.jsp";


	// SECTION: INSTANCE VARIABLES

	/** Methods, keyed by name */
	private Map<String, Method> handlerMethodMap = new HashMap<String, Method>();

	/** Methods, keyed by exception class */
	private Map<Class<?>, Method> exceptionHandlerMap = new HashMap<Class<?>, Method>();
	
	/** Method name resolver */
	private MethodNameResolver methodNameResolver = new JigsMethodNameResolver();

	/** Object we'll invoke handler methods on. Defaults to this. */
	private Object delegate;


	// SECTION: CONSTRUCTORS

	public MultiActionController()
	{
		super();
		initialize(this);
	}
	
	public MultiActionController(Object delegate)
	{
		super();
		initialize(delegate);
	}
	
	
	//SECTION: INITIALIZATION
	
	protected void initialize(Object delegate)
	{
		setDelegate(delegate);
		registerHandlerMethods();
	}


	// SECTION: ABSTRACT_CONTROLLER

	/**
	 * Determine a handler method and invoke it.
	 * 
	 * @param request The current HTTP request.
	 * @param response The current HTTP response.
	 * @see MethodNameResolver#getHandlerMethodName
	 * @see #invokeHandlerMethod(Method, HttpServletRequest, HttpServletResponse)
	 * @see #handleNoSuchRequestHandlingMethod
	 */
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
	    HttpServletResponse response) throws Exception
	{
		try
		{
			String methodName = getMethodNameResolver().getHandlerMethodName(request);
			Method handlerMethod = (Method) getHandlerMethodMap().get(methodName);

			if (handlerMethod == null)
			{
				throw new NoSuchRequestHandlingMethodException(methodName,
				    getClass());
			}

			return invokeHandlerMethod(handlerMethod, request, response);
		}
		catch (NoSuchRequestHandlingMethodException ex)
		{
			handleNoSuchRequestHandlingMethod(ex, request, response);
		}

		return null;
	}

	/**
	 * Handle the case where no request handler method was found.
	 * <p>
	 * The default implementation logs a warning and sends an HTTP 404 error.
	 * Alternatively, a fallback view could be chosen, or the
	 * NoSuchRequestHandlingMethodException could be rethrown as-is.
	 * 
	 * @param ex the NoSuchRequestHandlingMethodException to be handled
	 * @param request current HTTP request
	 * @param response current HTTP response
	 * @throws Exception an Exception that should be thrown as result of the servlet
	 * request.
	 */
	private void handleNoSuchRequestHandlingMethod(
	    NoSuchRequestHandlingMethodException ex, HttpServletRequest request,
	    HttpServletResponse response) throws Exception
	{
		ex.printStackTrace();
		request.getRequestDispatcher(PAGE_NOT_FOUND).forward(request, response);
	}

	/**
	 * Invokes the named method. Uses a custom exception handler if possible;
	 * otherwise, throw an unchecked exception; wrap a checked exception or
	 * Throwable.
	 * 
	 * @param handlerMethod The handler method to invoke.
	 * @param request The current HTTP request.
	 * @param response The current HTTP response.
	 * @return The {@link ModelAndView} instance returned by the handler method.
	 */
	protected ModelAndView invokeHandlerMethod(Method handlerMethod,
	    HttpServletRequest request, HttpServletResponse response)
	throws Exception
	{
		try
		{
			ActionContext actionContext = new ActionContext(request, response, handlerMethod);
			preHandlerInvocation(actionContext);
			ViewContext returnValue = (ViewContext) handlerMethod.invoke(getDelegate(), actionContext);
			postHandlerInvocation(actionContext, returnValue);
			ModelAndView mav = null;

			if (returnValue == null)
			{
				mav = new ModelAndView();
			}
			else if (HttpServletRequestUtils.isGetMethod(request))
			{
				mav = returnValue.asModelAndView();
			}
			else // POST method
			{
				mav = returnValue.asModelAndUrl();
			}

			return mav;
		}
		catch (InvocationTargetException ex)
		{
			// The handler method threw an exception.
			return handleException(request, response, ex.getTargetException());
		}
		catch (Exception ex)
		{
			// The binding process threw an exception.
			return handleException(request, response, ex);
		}
	}

	/**
	 * We've encountered an exception which may be recoverable
	 * (InvocationTargetException or HttpSessionRequiredException). Allow the
	 * subclass a chance to handle it.
	 * 
	 * @param request current HTTP request
	 * @param response current HTTP response
	 * @param ex the exception that got thrown
	 * @return a ModelAndView to render the response
	 */
	protected ModelAndView handleException(HttpServletRequest request,
	    HttpServletResponse response, Throwable ex)
	throws Exception
	{
		Method handler = getExceptionHandler(ex);
		if (handler != null)
		{
			return invokeExceptionHandler(handler, request, response, ex);
		}

		// If we get here, there was no custom handler
		if (ex instanceof Exception)
		{
			throw (Exception) ex;
		}
		if (ex instanceof Error)
		{
			throw (Error) ex;
		}

		// Should never happen!
		throw new NestedServletException("Unknown Throwable type encountered", ex);
	}

	/**
	 * Invoke the selected exception handler.
	 * 
	 * @param handler handler method to invoke
	 * @param request current HTTP request
	 * @param response current HTTP response
	 * @param ex the exception that got thrown
	 * @return The ModelAndView instance appropriate to render to the end user.
	 */
	private ModelAndView invokeExceptionHandler(Method handler,
	    HttpServletRequest request, HttpServletResponse response, Throwable ex)
	throws Exception
	{
		if (handler == null)
		{
			throw new NestedServletException("No handler for exception", ex);
		}

		try
		{
			ViewContext returnValue = (ViewContext) handler.invoke(this, new Object[]
			{
			    request, response, ex
			});
			return (returnValue != null ? returnValue.asModelAndView() : null);
		}
		catch (InvocationTargetException ex2)
		{
			Throwable targetEx = ex2.getTargetException();
			if (targetEx instanceof Exception)
			{
				throw (Exception) targetEx;
			}
			if (targetEx instanceof Error)
			{
				throw (Error) targetEx;
			}
			// Should never happen!
			throw new NestedServletException("Unknown Throwable type encountered", targetEx);
		}
	}

	/**
	 * Determine the exception handler method for the given exception. Can
	 * return null if not found.
	 * 
	 * @param exception From which its type will be deduced.
	 * @return a handler for the given exception type, or <code>null</code>
	 */
	private Method getExceptionHandler(Throwable exception)
	{
		Class<?> exceptionClass = exception.getClass();
		Method handler = (Method) getExceptionHandlerMap().get(exceptionClass);
		while ((handler == null) && (!exceptionClass.equals(Throwable.class)))
		{
			exceptionClass = exceptionClass.getSuperclass();
			handler = (Method) getExceptionHandlerMap().get(exceptionClass);
		}
		return handler;
	}


	// SECTION: ACCESSORS/MUTATORS

	public Object getDelegate()
    {
    	return delegate;
    }

	private void setDelegate(Object delegate)
    {
    	this.delegate = delegate;
    }

	protected Map<Class<?>, Method> getExceptionHandlerMap()
	{
		return exceptionHandlerMap;
	}

	protected Map<String, Method> getHandlerMethodMap()
	{
		return handlerMethodMap;
	}

	public Method getHandlerMethodFor(String methodName)
	{
		return getHandlerMethodMap().get(methodName);
	}

	public MethodNameResolver getMethodNameResolver()
    {
    	return methodNameResolver;
    }

	public void setMethodNameResolver(MethodNameResolver methodNameResolver)
    {
    	this.methodNameResolver = methodNameResolver;
    }


	// SECTION: METHOD REGISTRATION/CACHING

	/**
	 * Determines whether or not the argument {@link Method} instance is a valid
	 * handler method or not.
	 * <p>
	 * A handler method must accept one argument of type ActionContext, and have
	 * a return type of ViewContext or void. Note that the method's
	 * declared argument type can be of the raw ActionContext type, or can be of
	 * the parameterized form. e.g ActionContext<{@link DomainObject}>.
	 * 
	 * @param method The {@link Method} instance in question.
	 * @return true if the given method is in fact a handler method, false
	 *         otherwise.
	 */
	private boolean isHandlerMethod(Method method)
	{
		Class<?>[] parameterTypes = method.getParameterTypes();

		if ((parameterTypes.length != 1)
		    || (!parameterTypes[0].equals(ActionContext.class)))
		{
			return false;
		}

		Class<?> returnType = method.getReturnType();

		return ViewContext.class.equals(returnType)
		    || void.class.equals(returnType);
	}

	/**
	 * Is the supplied method a valid exception handler method?
	 */

	private boolean isExceptionHandlerMethod(Method method)
	{
		return (isHandlerMethod(method)
		    && (method.getParameterTypes().length == 3) && Throwable.class
		    .isAssignableFrom(method.getParameterTypes()[2]));
	}

	private void registerHandlerMethod(Method handlerMethod)
	{
		getHandlerMethodMap().put(handlerMethod.getName(), handlerMethod);
	}

	/**
	 * Registers the supplied method as an exception handler.
	 */
	private void registerExceptionHandlerMethod(Method method)
	{
		getExceptionHandlerMap().put(method.getParameterTypes()[2], method);
	}

	/**
	 * Registers all handlers methods on the delegate object.
	 */

	private void registerHandlerMethods()
	{
		getHandlerMethodMap().clear();
		getExceptionHandlerMap().clear();

		// Look at all methods in the subclass, trying to find
		// methods that are handlers according to our criteria

		for (Method method : getDelegate().getClass().getMethods())
		{
			// We're looking for methods with given parameters.
			if (isHandlerMethod(method))
			{
				registerHandlerMethod(method);
			}

			if (isExceptionHandlerMethod(method))
			{
				// Have an exception handler
				registerExceptionHandlerMethod(method);
			}
		}
	}
	
	
	// SECTION: SUB-CLASS RESPONSIBILTIES
	
	protected void preHandlerInvocation(ActionContext actionContext)
	throws Exception
	{
	}

	protected void postHandlerInvocation(ActionContext actionContext, ViewContext viewContext)
	throws Exception
	{
	}
	
	
	// SECTION: CONVENIENCE

	protected ViewContext newViewContext(String actionName)
	{
		return newViewContext(actionName, (Map<String, Object>) null);
	}

	protected ViewContext newViewContext(String actionName, Identifiable<?> identifiable)
	{
		Map<String, Object> modelMap = new HashMap<String, Object>(1);
		modelMap.put("id", identifiable);
		return newViewContext(actionName, modelMap);
	}

	protected ViewContext newViewContext(String actionName, Identifiable<?> identifiable, Identifiable<?> owner)
	{
		Map<String, Object> modelMap = new HashMap<String, Object>(2);
		modelMap.put("id", identifiable);
		modelMap.put("owner", owner);
		return newViewContext(actionName, modelMap);
	}

	protected ViewContext newViewContext(String actionName, Map<String, Object> modelMap)
	{
		return new ViewContext(this.getClass(), actionName, modelMap);
	}
}
