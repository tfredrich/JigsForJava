package com.jigsforjava.util;

import java.util.Calendar;
import java.util.Date;

import junit.framework.TestCase;


public class RangeTestCase
extends TestCase
{
	Date one;
	Date two;
	Date three;
	Date four;

	public void setUp()
	{
		three = new Date(2005, Calendar.DECEMBER, 17);
		one = new Date(2006, Calendar.JANUARY, 6);
		two = new Date(2008, Calendar.MAY, 15);
		four = new Date(2009, Calendar.JUNE, 22);
	}

	public void testDateRangeEqualsRange()
	{
		Range<Date> dateRange = new Range<Date>(one, two);
		assertTrue(dateRange.equals(dateRange));
	}

	public void testDateRangeUnequalRange()
	{
		Range<Date> dateRange1 = new Range<Date>(one, two);
		Range<Date> dateRange2 = new Range<Date>(three, four);
		assertFalse(dateRange1.equals(dateRange2));
	}
	
	public void testDateRangeIncludesDate()
	{
		Range<Date> range = new Range<Date>(one, four);
		assertTrue(range.includes(one));
		assertTrue(range.includes(two));
		assertTrue(range.includes(four));
		assertFalse(range.includes(three));
	}

	public void testDateRangeIncludesRange()
	{
		Range<Date> range1 = new Range<Date>(three, four);
		Range<Date> range2 = new Range<Date>(one, two);
		assertTrue(range1.includes(range2));
	}
	
	public void testDateRangeNotincludesRange()
	{
		Range<Date> range1 = new Range<Date>(three, one);
		Range<Date> range2 = new Range<Date>(two, four);
		assertFalse(range1.includes(range2));
		
		Range<Date> range3 = new Range<Date>(one, two);
		assertFalse(range1.includes(range3));
	}
	
	public void testDateRangeOverlaps()
	{
		Range<Date> range1 = new Range<Date>(three, one);
		Range<Date> range2 = new Range<Date>(one, two);
		assertTrue(range1.overlaps(range2));
	}
	
	public void testDateRangeNotOverlapping()
	{
		Range<Date> range1 = new Range<Date>(three, one);
		Range<Date> range2 = new Range<Date>(two, four);
		assertFalse(range1.overlaps(range2));
	}
	
	public void testDateRangeFromRange()
	{
		Range<Date> range1 = new Range<Date>(one, two);
		Range<Date> range2 = new Range<Date>(range1);
		
		assert(range1.equals(range2));
	}
}
