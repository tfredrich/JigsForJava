package com.jigsforjava.string;

import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 *  Description of the Class
 *
 * @author  Todd Fredrich
 * @created  October 29, 2001
 * @version  $Revision: 1.1 $
 */
public class ParameterizedString
{
	private final static String DEFAULT_DELIMITER = "?";
	private ArrayList<String> tokens;
	private String[] parameters;


	/**
	 *  Constructor for the ParameterizedString object
	 *
	 * @param  string Description of Parameter
	 */
	public ParameterizedString(String string)
	{
		this(string, DEFAULT_DELIMITER);
	}


	/**
	 *  Constructor for the ParameterizedString object
	 *
	 * @param  string Description of Parameter
	 * @param  delimiter Description of Parameter
	 */
	public ParameterizedString(String string, String delimiter)
	{
		tokens = new ArrayList<String>(5);
		StringTokenizer st = new StringTokenizer(string, delimiter, true);
		int parameterCount = 0;

		while (st.hasMoreTokens())
		{
			String token = st.nextToken();

			if (token.equals(delimiter))
			{
				parameterCount++;
			}
			else
			{
				tokens.add(token);
			}
		}

		parameters = new String[parameterCount];
	}


	public void bind (String[] values)
	{
		for (int i = 0; i < getParameterCount(); ++i)
		{
			setString(i + 1, values[i]);
		}
	}


	/**
	 *  Gets the Integer attribute of the ParameterizedString object
	 *
	 * @param  index Description of Parameter
	 * @return  The Integer value
	 */
	public int getInt(int index)
	{
		return Integer.valueOf(parameters[index - 1]).intValue();
	}


	/**
	 *  Gets the String attribute of the ParameterizedString object
	 *MarketLanguageTestSuite
	 * @param  index Description of Parameter
	 * @return  The String value
	 */
	public String getString(int index)
	{
		return parameters[index - 1];
	}


	/**
	 *  Sets the Int attribute of the ParameterizedString object
	 *
	 * @param  index The new Int value
	 * @param  parameter The new Int value
	 */
	public void setInt(int index, int parameter)
	{
		parameters[index - 1] = String.valueOf(parameter);
	}


	/**
	 *  Sets the String attribute of the ParameterizedString object
	 *
	 * @param  index The new String value
	 * @param  parameter The new String value
	 */
	public void setString(int index, String parameter)
	{
		parameters[index - 1] = parameter;
	}


	/**
	 *  Description of the Method
	 *
	 * @return  Description of the Returned Value
	 */
	public String toString()
	{
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < tokens.size(); ++i)
		{
			sb.append((String) tokens.get(i));

			if (i < parameters.length && parameters[i] != null)
			{
				sb.append(parameters[i]);
			}
		}

		return sb.toString();
	}

	public int getParameterCount()
	{
		return parameters.length;
	}
}
