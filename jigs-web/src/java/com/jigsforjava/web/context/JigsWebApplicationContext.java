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

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.jigsforjava.web.JigsPathHelper;
import com.jigsforjava.web.routing.UrlMapper;

/**
 * Represents an access point into the Spring Web Application Context.
 * 
 * @author toddf
 * @since Aug 11, 2008
 */
public class JigsWebApplicationContext
{
	//SECTION: CONSTANTS
	
	private static final String URL_MAPPER_BEAN_NAME = "urlMapper";


	//SECTION: ACCESSORS/MUTATORS
	
	public static UrlMapper getUrlMapper(HttpServletRequest request)
	{
		return (UrlMapper) getBean(request, URL_MAPPER_BEAN_NAME);
	}
	
	public static UrlMapper getUrlMapper()
	{
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		return getUrlMapper(attributes.getRequest());
	}
	
	
	//SECTION: UTILITY - PRIVATE
	
	private static ApplicationContext getWebApplicationContext(HttpServletRequest request)
	{
		return RequestContextUtils.getWebApplicationContext(request);
	}

	private static Object getBean(HttpServletRequest request, String beanName)
	{
		return getWebApplicationContext(request).getBean(beanName);
	}
}
