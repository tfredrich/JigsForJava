/**
 * Copyright (C)2008, Strategic Gains, Inc.  All rights reserved.
 */
package com.jigsforjava.util;

/**
 * A generic class that implements the range analysis pattern. See
 * http://martinfowler.com/ap2/range.html
 * 
 * @author Todd Fredrich
 * @since May 14, 2008
 */
public class Range<T extends Comparable<T>>
{
	// SECTION: INSTANCE VARIABLES

	private T start;
	private T end;


	// SECTION: CONSTRUCTORS
	
	public Range()
	{
		super();
	}

	public Range(T start, T end)
	{
		super();
		setStart(start);
		setEnd(end);
	}
	
	public Range(Range<T> range)
	{
		this(range.start, range.end);
	}

	// SECTION: ACCESSORS/MUTATORS

	public T getStart()
	{
		return start;
	}

	public void setStart(T start)
	{
		this.start = start;
	}

	public T getEnd()
	{
		return end;
	}

	public void setEnd(T end)
	{
		this.end = end;
	}

	// SECTION: RANGE

	public boolean includes(T inQuestion)
	{
		return (inQuestion.compareTo(start) >= 0
			&& inQuestion.compareTo(end) <= 0);
	}

	public boolean includes(Range<T> inQuestion)
	{
		return (this.includes(inQuestion.start) 
			&& this.includes(inQuestion.end));
	}

	public boolean overlaps(Range<T> inQuestion)
	{
	 	return (inQuestion.includes(start) 
	 		|| inQuestion.includes(end) 
	 		|| this.includes(inQuestion));
	}

    @Override
	@SuppressWarnings("unchecked")
	public boolean equals (Object obj)
	{
		if (!(obj instanceof Range)
			&& !(((Range)obj).getStart().getClass().isAssignableFrom(getStart().getClass())))
		{
			return false;
		}
		
		Range<T> other = (Range<T>) obj;
		return start.equals(other.start) && end.equals(other.end);
	}

	public int hashCode()
	{
		return (start.hashCode() ^ end.hashCode());
	}
	
	@Override
	public String toString()
	{
		return "Range[" + start.toString() + "," + end.toString() + "]";
	}
}
