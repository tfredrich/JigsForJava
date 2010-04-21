package com.jigsforjava.identity;

import java.util.Map;
import java.util.Map.Entry;

import com.jigsforjava.util.ObjectUtils;

/**
 *  An identifier for an object. Not necessarily unique.
 *
 * @author  Todd Fredrich
 * @created  October 11, 2002
 * @version $Revision: 1.2 $
 */
public abstract class Identity<T>
implements Comparable<Identity<T>>
{
	// SECTION: VARIABLES

	private Class<T> identifiedClass;


	// SECTION: CONSTRUCTORS

	public Identity(Class<T> identifiedClass)
	{
		this.identifiedClass = identifiedClass;
	}


	// SECTION: COMPARABLE

	public int compareTo(Identity<T> that)
	{
		int result = this.getIdentifiedClass().getName().compareTo(that.getIdentifiedClass().getName());
		
		if (result == 0)
		{
			result = compareIdentifyingAspects(that);
		}
		
		return result;
	}


	// SECTION: EQUALITY
	
    @Override
	@SuppressWarnings("unchecked")
	public boolean equals (Object object)
	{
		return equals((Identity<T>) object);
	}

	public boolean equals(Identity<T> otherIdentity)
	{
		return (compareTo(otherIdentity) == 0);
	}

	@Override
	public int hashCode()
	{
		return toString().hashCode();
	}
	

	// SECTION: ACCESSORS

	public Class<T> getIdentifiedClass()
	{
		return identifiedClass;
	}
	
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("identifiedClass=");
		sb.append(identifiedClass);
		return sb.toString();
	}
	
	public abstract <V> Map<String, Object> getIdentifyingAspects();
	
	
	// SECTION: UTILITY
	
	private int compareIdentifyingAspects(Identity<T> otherIdentity)
	{
		int result = 0;
		Map<String, Object> otherAspectMap = otherIdentity.getIdentifyingAspects();
		
		for (Entry<String, Object> aspectEntry : getIdentifyingAspects().entrySet())
		{
			Object thisAspect = aspectEntry.getValue();
			Object thatAspect = otherAspectMap.get(aspectEntry.getKey());
			result = ObjectUtils.compareTo(thisAspect, thatAspect);
			
			if (result != 0)
			{
				break;
			}
		}
		
		return result;
	}
}
