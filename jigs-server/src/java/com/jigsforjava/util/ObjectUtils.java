/*
	Copyright 2005 Strategic Gains, Inc.

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
package com.jigsforjava.util;

/**
 * @author Todd Fredrich
 * @since Mar 11, 2005
 * @version $Revision: 1.2 $
 */
public abstract class ObjectUtils
extends org.springframework.util.ObjectUtils
{
	public static boolean areEqual(Object object1, Object object2)
	{
		if (object1 == null)
		{
			return (object2 == null);
		}
		else if (object2 == null)
		{
			return false;
		}

		return object1.equals(object2);
	}

	public static <T extends Comparable<T>> boolean areEqualComparables(T object1, T object2)
	{
		if (object1 == null)
		{
			return (object2 == null);
		}
		else if (object2 == null)
		{
			return false;
		}

		return (object1.compareTo(object2) == 0);
	}

	@SuppressWarnings("unchecked")
	public static int compareTo(Object object1, Object object2)
	{
		if (object1 == null)
			return (object2 == null) ? 0 : -1;
		else if (object2 == null)
			return (object1 == null) ? 0 : 1;
		else
		{
			if (areComparable(object1, object2))
			{
				return ((Comparable) object1).compareTo(((Comparable) object2));
			}
			else
			{
				return (object1.toString().compareTo(object2.toString()));
			}
		}
	}

	public static boolean areComparable(Object o1, Object o2)
	{
		if (o1 == null || o2 == null)
		{
			return false;
		}
		
		if (isComparable(o1) && isComparable(o2))
		{
			if (o1.getClass().isAssignableFrom(o2.getClass()) ||
					o2.getClass().isAssignableFrom(o1.getClass()))
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		
		return false;
	}

	public static boolean isComparable(Object object)
	{
		return object instanceof Comparable;
	}
}
