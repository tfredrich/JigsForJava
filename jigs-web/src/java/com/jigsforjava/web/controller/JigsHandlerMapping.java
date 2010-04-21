/**
 * Copyright (C)2008, Strategic Gains, Inc.  All rights reserved.
 */
package com.jigsforjava.web.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.handler.AbstractHandlerMapping;
import org.springframework.web.util.UrlPathHelper;

import com.jigsforjava.web.routing.UrlMapper;

/**
 * @author toddf
 * @since May 14, 2008
 */
public class JigsHandlerMapping
extends AbstractHandlerMapping
{
	//SECTION: INSTANCE VARIABLES
	
	private HashMap<String, Object> controllerMap;
	private UrlPathHelper urlPathHelper;
	private UrlMapper urlMapper;
	
	
	//SECTION: CONSTRUCTION
	
	public JigsHandlerMapping()
	{
		controllerMap = new HashMap<String, Object>();
		urlPathHelper = new UrlPathHelper();
	}

	
	//SECTION: BEAN PROPERTIES
	
	/**
	 * Set the UrlPathHelper to use when parsing the requested URL.
	 * 
	 * @param helper a UrlPathHelper.
	 */
	public void setUrlPathHelper(UrlPathHelper helper)
	{
		this.urlPathHelper = helper;
	}
	
	public void setUrlMapper(UrlMapper mapper)
	{
		this.urlMapper = mapper;
	}

	
	//SECTION: RESOLVING

	/*
	 * Please note: this method is not currently synchronized as it is dubbed as
	 * not important enough to pay the price of acquiring the mutex.
	 */
	public Object getHandlerInternal(HttpServletRequest request)
	{
		String controllerName = getControllerName(request);
		Object handler = controllerMap.get(controllerName);

		if (handler == null)
		{
			try
			{
				handler = Class.forName(controllerName).newInstance();
				controllerMap.put(controllerName, handler);
			}
			catch (Exception e)
			{
				throw new UnsupportedOperationException(urlPathHelper.getPathWithinApplication(request)
					+ " maps to " + controllerName, e);
			}
		}

		return handler;
	}
	
	
	//SECTION: UTILITY - PRIVATE

	private String getControllerName(HttpServletRequest request)
	{
		String basePath = urlPathHelper.getPathWithinApplication(request);
		return urlMapper.getControllerName(basePath);
	}
}
