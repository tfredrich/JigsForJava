/**
 * Copyright (C)2008, Strategic Gains, Inc.  All rights reserved.
 */
package com.jigsforjava.web;

import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;

import com.jigsforjava.identity.Identifiable;
import com.jigsforjava.string.StringConstants;
import com.jigsforjava.string.StringUtils;

/**
 * @author Todd Fredrich
 * @since May 14, 2008
 */
public class JigsPathHelper
{
	//SECTION: CONSTANTS

	private static final String DEFAULT_CAMEL_CASE_SEPARATOR = "_";
	private static final String DEFAULT_CONTROLLER_NAME_SUFFIX = "Controller";
	private static final String DEFAULT_PACKAGE_BASE = StringConstants.EMPTY;
	private static final String DEFAULT_URL_EXTENSION = "action";
	private static final String PACKAGE_SEPARATOR = ".";
	private static final String PATH_SEPARATOR = "/";


	//SECTION: INSTANCE VARIABLES

	private static JigsPathHelper instance = null;
	private String camelCaseSeparator = DEFAULT_CAMEL_CASE_SEPARATOR;
	private String controllerNameSuffix = DEFAULT_CONTROLLER_NAME_SUFFIX;
	private String controllerPackageBase = DEFAULT_PACKAGE_BASE;
	private String urlExtension = DEFAULT_URL_EXTENSION;


	//SECTION: CONSTRUCTING

	public JigsPathHelper()
	{
		instance = this;
	}


	//SECTION: SINGLETON

	public static JigsPathHelper getInstance()
	{
		return instance;
	}


	//SECTION: BEAN PROPERTY ACCESSORS

	/**
	 * @return the camelCaseSeparator
	 */
	public String getCamelCaseSeparator()
	{
		return camelCaseSeparator;
	}

	/**
	 * @param camelCaseSeparator
	 *            the camelCaseSeparator to set
	 */
	public void setCamelCaseSeparator(String camelCaseSeparator)
	{
		this.camelCaseSeparator = camelCaseSeparator;
	}

	/**
	 * @return the controllerNameSuffix
	 */
	public String getControllerNameSuffix()
	{
		return controllerNameSuffix;
	}

	/**
	 * @param controllerNameSuffix
	 *            the controllerNameSuffix to set
	 */
	public void setControllerNameSuffix(String controllerNameSuffix)
	{
		this.controllerNameSuffix = controllerNameSuffix;
	}

	/**
	 * @return the packageBase
	 */
	public String getControllerPackageBase()
	{
		return controllerPackageBase;
	}

	/**
	 * @param packageBase
	 *            the packageBase to set
	 */
	public void setControllerPackageBase(String packageBase)
	{
		this.controllerPackageBase = packageBase;
	}

	/**
	 * @return the urlExtension
	 */
	public String getUrlExtension()
	{
		return urlExtension;
	}

	/**
	 * @param urlExtension
	 *            the urlExtension to set
	 */
	public void setUrlExtension(String urlExtension)
	{
		this.urlExtension = urlExtension;
	}


	//SECTION: URL PARSING

	private ArrayList<String> getControllerStrings(String basePath)
	{
		String path = (basePath.startsWith(PATH_SEPARATOR) ? basePath
		    .substring(1) : basePath);
		String[] pathStrings = StringUtils.parseString(path, PATH_SEPARATOR);
		return correctPackageNameCase(pathStrings);
	}

	public String getControllerName(String basePath)
	{
		ArrayList<String> controllerStrings = getControllerStrings(basePath);
		String controllerName = createPartiallyQualifiedControllerName(controllerStrings);
		return createFullyQualifiedControllerName(controllerName);
	}


	//SECTION: URL GENERATION

	public String getURL(Class<?> controllerClass, String actionName)
	{
		return getURL(getControllerBasedURL(controllerClass), actionName);
	}

	public String getURL(String controllerName, String actionName)
	{
		StringBuilder result = new StringBuilder();

		if (!controllerName.startsWith(PATH_SEPARATOR))
		{
			result.append(PATH_SEPARATOR);
		}

		result.append(controllerName);
		result.append(PATH_SEPARATOR);
		result.append(actionName);
		result.append(".");
		result.append(urlExtension);
		return result.toString();
	}

	public String getURL(Class<?> controllerClass, String actionName, Identifiable<?> identifiable)
	{
		return getURL(getControllerBasedURL(controllerClass), actionName, identifiable);
	}

	public String getURL(String controllerName, String actionName, Identifiable<?> identifiable)
	{
		StringBuilder result = new StringBuilder(getURL(controllerName, actionName));
		Map<String, Object> aspects = identifiable.getIdentity().getIdentifyingAspects();
		boolean isFirstEntry = true;
		
		for (Entry<String, Object> aspectEntry : aspects.entrySet())
		{
			if (isFirstEntry)
			{
				result.append("?");
				isFirstEntry = false;
			}
			else
			{
				result.append("&");
			}
			
			result.append(aspectEntry.getKey());
			result.append("=");
			result.append(aspectEntry.getValue().toString());
		}
		
		return result.toString();
	}


	//SECTION: VIEW_ACTION NAME GENERATION

	public String getViewName(Class<?> controllerClass, String jspBaseName)
	{
		StringBuilder result = new StringBuilder(getControllerBasedURL(controllerClass));
		result.append(PATH_SEPARATOR);
		result.append(jspBaseName);
		return result.toString();
	}


	//SECTION: UTILITY

	private ArrayList<String> correctPackageNameCase(String[] pathStrings)
	{
		ArrayList<String> controllerStrings = new ArrayList<String>();

		for (int i = 0; i < pathStrings.length - 1; ++i)
		{
			String name = StringUtils.toCamelCase(pathStrings[i],
			    camelCaseSeparator);

			if (i < pathStrings.length - 2)
			{
				name = StringUtils.uncapitalize(name);
			}

			controllerStrings.add(name);
		}

		return controllerStrings;
	}

	private String createPartiallyQualifiedControllerName(
	    ArrayList<String> controllerStrings)
	{
		String controllerName = null;

		if (!controllerStrings.isEmpty())
		{
			controllerName = StringUtils.arrayToDelimitedString(
			    controllerStrings.toArray(), PACKAGE_SEPARATOR);
		}

		return controllerName;
	}

	private String createFullyQualifiedControllerName(
	    String partiallyQualifiedName)
	{
		if (partiallyQualifiedName == null) return null;

		StringBuilder sb = new StringBuilder(getControllerPackageBase());

		if (StringUtils.isNotNullNorEmpty(getControllerPackageBase())
		    && !getControllerPackageBase().endsWith(PACKAGE_SEPARATOR))
		{
			sb.append(PACKAGE_SEPARATOR);
		}

		sb.append(partiallyQualifiedName);
		sb.append(controllerNameSuffix);
		return sb.toString();
	}

	public String getControllerBasedURL(Class<?> controllerClass)
	{
		String fullyQualifiedClassName = controllerClass.getName();
		String relativeClassName = fullyQualifiedClassName.replace(getControllerPackageBase(), "");

		if (relativeClassName.startsWith(PACKAGE_SEPARATOR))
		{
			relativeClassName = relativeClassName.substring(PACKAGE_SEPARATOR.length());
		}

		StringBuilder result = new StringBuilder();
		String[] tokens = StringUtils.parseString(relativeClassName, PACKAGE_SEPARATOR);

		for (int i = 0; i < tokens.length; ++i)
		{
			if (i < tokens.length - 1)
			{
				result.append(tokens[i]);
				result.append(PATH_SEPARATOR);
			}
			else
			{
				String x = tokens[i].replace(controllerNameSuffix, "");
				result.append(StringUtils.toSeparatedString(x, '_'));
			}
		}

		return result.toString();
	}
}
