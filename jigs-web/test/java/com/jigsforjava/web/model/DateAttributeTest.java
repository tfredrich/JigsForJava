/*
	Copyright 2004-2008 Strategic Gains, Inc.
	
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
package com.jigsforjava.web.model;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import com.jigsforjava.web.model.DateAttribute;

import junit.framework.TestCase;

/**
 * @author Todd Fredrich
 * @since  Mar 30, 2004
 */
public class DateAttributeTest
	extends TestCase
{
	public DateAttributeTest(String testName)
	{
		super(testName);
	}
	
	public void testDefaultConstructor()
	{
		DateAttribute attr = new DateAttribute("test");
		assertTrue("Should be set to something", attr.getValue().getTime() > 0);
		assertFalse("Should not be changed", attr.isModified());
	}
	
	public void testValueConstructor()
	{
		Date now = new Date();
		DateAttribute attr = new DateAttribute("test", now);
		assertEquals("Should be set to now", now, attr.getValue());
		assertFalse("Should not be changed", attr.isModified());
	}
	
	public void testCompareTo()
	{
		GregorianCalendar gc = new GregorianCalendar();
		DateAttribute now = new DateAttribute("test1", gc.getTime());
		DateAttribute now2 = new DateAttribute("test2", gc.getTime());
		
		gc.set(Calendar.YEAR, gc.get(Calendar.YEAR) + 5);
		DateAttribute later = new DateAttribute("test3", gc.getTime());

		gc.set(1964, Calendar.DECEMBER, 17);
		DateAttribute earlier = new DateAttribute("test4", gc.getTime());
		
		assertTrue("now equals now2", now.compareTo(now2) == 0);
		assertTrue("now2 equals now", now2.compareTo(now) == 0);

		assertTrue("now < later", now.compareTo(later) < 0);
		assertTrue("later > now", later.compareTo(now) > 0);

		assertTrue("now > earlier", now.compareTo(earlier) > 0);
		assertTrue("earlier < now", earlier.compareTo(now) < 0);

		assertTrue("later > earlier", later.compareTo(earlier) > 0);
		assertTrue("earlier < later", earlier.compareTo(later) < 0);
	}
	
	public void testSetValue()
	{
		DateAttribute attr = new DateAttribute("test");
		assertFalse("Should not be changed", attr.isModified());

		attr.setValue(attr.getValue());
		assertFalse("Should not be changed", attr.isModified());

		GregorianCalendar gc = new GregorianCalendar();
		gc.set(Calendar.YEAR, gc.get(Calendar.YEAR) + 5);
		attr.setValue(gc.getTime());
		assertTrue("Should be changed", attr.isModified());
	}
}
