/**
 * Copyright (C)2008, Strategic Gains, Inc.  All rights reserved.
 */
package com.jigsforjava.web.view;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

import com.jigsforjava.web.HttpServletRequestUtils;

/**
 * This ViewResolver supports keeping the URL address in the browser updated by
 * performing a redirect after HTTP POST operation.
 * 
 * @author Todd Fredrich
 * @since May 14, 2008
 */
public class JigsRedirectAfterPostViewResolver
extends UrlBasedViewResolver
{
	// SECTION: VIEW_ACTION RESOLVER

	protected View createView(String viewName, Locale locale) throws Exception
	{
		log().debug(
		    "In " + getClass().getSimpleName() + " .createView(" + viewName
		        + ")");
		// If this resolver is not supposed to handle the given view,
		// return null to pass on to the next resolver in the chain.
		if (!canHandle(viewName, locale))
		{
			return null;
		}

		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder
		    .getRequestAttributes();
		HttpServletRequest request = attributes.getRequest();

		if (HttpServletRequestUtils.isPostMethod(request))
		{
			return new RedirectView(viewName, isRedirectContextRelative(), isRedirectHttp10Compatible());
		}

		// Else fall back to superclass implementation: calling loadView.
		return super.createView(viewName, locale);
	}

	// LOGGING

	/**
	 * Returns the receiver's Logger.
	 * 
	 * @return the receiver's Logger
	 */
	protected Logger log()
	{
		return Logger.getLogger(getClass());
	}
}
