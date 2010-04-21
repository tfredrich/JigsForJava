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

import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.jigsforjava.string.StringConstants;
import com.jigsforjava.string.StringUtils;
import com.jigsforjava.web.controller.MultiActionController;

/**
 * @author Todd Fredrich
 * @since May 14, 2008
 */
public class JigsUrlMapper
implements UrlMapper
{
	//SECTION: CONSTANTS

	private static final String DEFAULT_ACTION = "view";
	private static final String DEFAULT_ACTION_SUFFIX = "action";
	private static final char DEFAULT_CAMEL_CASE_SEPARATOR = '_';
	private static final String DEFAULT_CONTROLLER_NAME_SUFFIX = "Controller";
	private static final String DEFAULT_PACKAGE_BASE = StringConstants.EMPTY;
	private static final String PACKAGE_SEPARATOR = ".";
	private static final String PATH_SEPARATOR = "/";
	private static final String FULL_URL_REGEX_PATTERN = "/?(.*)/\\w+.action$";
	private static final String PARTIAL_URL_REGEX_PATTERN = "/?(.+?)/?$";
	private static final String ACTION_REGEX_PATTERN = "/?.*/(\\w+).action$";


	//SECTION: INSTANCE VARIABLES

	private String actionSuffix = DEFAULT_ACTION_SUFFIX;
	private char camelCaseSeparator = DEFAULT_CAMEL_CASE_SEPARATOR;
	private String controllerNameSuffix = DEFAULT_CONTROLLER_NAME_SUFFIX;
	private String controllerPackageBase = DEFAULT_PACKAGE_BASE;
	private String defaultAction = DEFAULT_ACTION;
	private Pattern fullUrlRegexPattern = Pattern.compile(FULL_URL_REGEX_PATTERN);
	private Pattern partialUrlRegexPattern = Pattern.compile(PARTIAL_URL_REGEX_PATTERN);
	private Pattern actionRegexPattern = Pattern.compile(ACTION_REGEX_PATTERN);


	//SECTION: CONSTRUCTORS

	public JigsUrlMapper()
	{
		super();
	}


	//SECTION: BEAN PROPERTY ACCESSORS/MUTATORS

	public String getActionSuffix()
	{
		return actionSuffix;
	}
	
	public void setActionSuffix(String value)
	{
		this.actionSuffix = value;
		handleActionSuffixChange(value);
	}
	
	private void handleActionSuffixChange(String value)
	{
		fullUrlRegexPattern = Pattern.compile(FULL_URL_REGEX_PATTERN.replace(DEFAULT_ACTION_SUFFIX, value));
		actionRegexPattern = Pattern.compile(ACTION_REGEX_PATTERN.replace(DEFAULT_ACTION_SUFFIX, value));
	}

	/**
	 * @return the camelCaseSeparator
	 */
	public char getCamelCaseSeparator()
	{
		return camelCaseSeparator;
	}

	/**
	 * @param camelCaseSeparator
	 *            the camelCaseSeparator to set
	 */
	public void setCamelCaseSeparator(char camelCaseSeparator)
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

	public String getDefaultAction()
    {
    	return defaultAction;
    }
	
	public boolean hasDefaultAction()
	{
		return StringUtils.isNotNullNorEmpty(getDefaultAction());
	}

	public void setDefaultAction(String defaultAction)
    {
    	this.defaultAction = defaultAction;
    }


	//SECTION: URL PARSING

	@Override
	public String getControllerName(String basePath)
	{
		Matcher m1 = fullUrlRegexPattern.matcher(basePath);
		String controller = StringConstants.EMPTY;

		if (m1.matches())
		{
			controller = m1.group(1);
		}
		else
		{
			Matcher m2 = partialUrlRegexPattern.matcher(basePath);
			
			if (m2.matches())
			{
				controller = m2.group(1);
			}
		}

		return (StringUtils.isNotNullNorEmpty(controller) 
			? getControllerClassName(controller)
			: controller);
	}
	
	@Override
	public String getActionName(String basePath)
	{
		Matcher m1 = actionRegexPattern.matcher(basePath);
		String action = getDefaultAction();
		
		if (m1.matches())
		{
			action = m1.group(1);
		}
		
		return (StringUtils.isNotNullNorEmpty(action)
			? StringUtils.toCamelCase(action, String.valueOf(getCamelCaseSeparator()))
			: action);
	}


	//SECTION: URL GENERATION

	@Override
	public String toUrl(Class<? extends MultiActionController> controllerClass, String actionName, Map<String, Object> parameters)
	{
		return toUrl(getControllerBasedURL(controllerClass), actionName, parameters);
	}

	@Override
	public String toUrl(String controllerName, String actionName, Map<String, Object> parameters)
	{
		StringBuilder result = new StringBuilder();

		if (!controllerName.startsWith(PATH_SEPARATOR))
		{
			result.append(PATH_SEPARATOR);
		}

		result.append(controllerName);
		result.append(PATH_SEPARATOR);
		result.append(StringUtils.toSeparatedString(actionName, getCamelCaseSeparator()));
		result.append(".");
		result.append(getActionSuffix());
		generateParametersInto(result, parameters);
		return result.toString();
	}


	//SECTION: VIEW/ACTION NAME GENERATION

	@Override
	public String toView(Class<? extends MultiActionController> controllerClass, String jspBaseName)
	{
		StringBuilder result = new StringBuilder(getControllerBasedURL(controllerClass));
		result.append(PATH_SEPARATOR);
		result.append(StringUtils.uncapitalize(jspBaseName));
		return result.toString();
	}


	//SECTION: UTILITY

	private String getControllerClassName(String controllerPath)
	{
		ArrayList<String> controllerStrings = correctPackageNameCase(controllerPath.split(PATH_SEPARATOR));
		String controllerName = createPartiallyQualifiedControllerName(controllerStrings);
		return createFullyQualifiedControllerName(controllerName);
	}

	private ArrayList<String> correctPackageNameCase(String[] pathStrings)
	{
		ArrayList<String> controllerStrings = new ArrayList<String>();

		for (int i = 0; i < pathStrings.length; ++i)
		{
			String name = StringUtils.toCamelCase(pathStrings[i], String.valueOf(getCamelCaseSeparator()));

			if (i < pathStrings.length - 1)
			{
				name = StringUtils.uncapitalize(name);
			}

			controllerStrings.add(name);
		}

		return controllerStrings;
	}

	private String createPartiallyQualifiedControllerName(ArrayList<String> controllerStrings)
	{
		String controllerName = null;

		if (!controllerStrings.isEmpty())
		{
			controllerName = StringUtils.arrayToDelimitedString(controllerStrings.toArray(), PACKAGE_SEPARATOR);
		}

		return controllerName;
	}

	private String createFullyQualifiedControllerName(String partiallyQualifiedName)
	{
		if (partiallyQualifiedName == null) return null;

		StringBuilder sb = new StringBuilder(getControllerPackageBase());

		if (StringUtils.isNotNullNorEmpty(getControllerPackageBase())
		    && !getControllerPackageBase().endsWith(PACKAGE_SEPARATOR))
		{
			sb.append(PACKAGE_SEPARATOR);
		}

		sb.append(partiallyQualifiedName);
		sb.append(getControllerNameSuffix());
		return sb.toString();
	}

	private String getControllerBasedURL(Class<? extends MultiActionController> controllerClass)
	{
		String controllerName = controllerClass.getName();
		String relativeClassName = controllerName.replace(getControllerPackageBase(), "");

		if (relativeClassName.startsWith(PACKAGE_SEPARATOR))
		{
			relativeClassName = relativeClassName.substring(PACKAGE_SEPARATOR.length());
		}

		StringBuilder result = new StringBuilder();
		result.append(PATH_SEPARATOR);
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

	/**
     * @param parameters
     * @param result
     */
    private void generateParametersInto(StringBuilder result, Map<String, Object> parameters)
    {
	    if (parameters != null)
		{
			boolean isFirstEntry = true;
	
			for (Entry<String, Object> aspectEntry : parameters.entrySet())
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
				
				result.append(StringUtils.urlEncode(aspectEntry.getKey()));
				result.append("=");
				result.append(StringUtils.urlEncode(aspectEntry.getValue().toString()));
			}
		}
    }
}
