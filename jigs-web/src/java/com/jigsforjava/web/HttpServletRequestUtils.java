/**
 * Copyright (C)2008, Strategic Gains, Inc.  All rights reserved.
 */
package com.jigsforjava.web;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Todd Fredrich
 * @since May 14, 2008
 */
public abstract class HttpServletRequestUtils
{
	// CONSTANTS

	private static final String GET_METHOD = "GET";
	private static final String POST_METHOD = "POST";

	// FOREIGN METHODS

	/**
	 * Returns true if the request is a POST. Otherwise, false.
	 * 
	 * @param request
	 *            an HttpServletRequest
	 * @return true if the request is a POST, otherwise false.
	 */
	public static boolean isPostMethod(HttpServletRequest request)
	{
		return POST_METHOD.equals(request.getMethod());
	}

	/**
	 * Returns true if the request is a GET. Otherwise, false.
	 * 
	 * @param request
	 *            an HttpServletRequest
	 * @return true if the request is a GEt, otherwise false.
	 */
	public static boolean isGetMethod(HttpServletRequest request)
	{
		return GET_METHOD.equals(request.getMethod());
	}
}
