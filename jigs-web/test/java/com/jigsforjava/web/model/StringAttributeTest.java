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

import junit.framework.TestCase;

import com.jigsforjava.string.StringConstants;

/**
 * @author Todd Fredrich
 * @since  Mar 30, 2004
 */
public class StringAttributeTest
	extends TestCase
{
	public StringAttributeTest(String testName)
	{
		super(testName);
	}
	
	public void testDefaultConstructor()
	{
		StringAttribute attr = new StringAttribute("test");
		assertEquals("Should default to empty string", StringConstants.EMPTY, attr.getValue());
		assertFalse("Should not be changed", attr.isModified());
	}
	
	public void testValueConstructor()
	{
		StringAttribute attr = new StringAttribute("test", "OpenEF/J");
		assertEquals("Should be set to 'OpenEF/J'", "OpenEF/J", attr.getValue());
		assertFalse("Should not be changed", attr.isModified());
	}
	
	public void testCompareTo()
	{
		StringAttribute middle = new StringAttribute("test1", "OpenEF/J");
		StringAttribute middle2 = new StringAttribute("test2", "OpenEF/J");
		StringAttribute more = new StringAttribute("test3", "Strategic Gains, Inc.");
		StringAttribute less = new StringAttribute("test4", "Framework");
		
		assertTrue("'middle' equals 'middle2'", middle.compareTo(middle2) == 0);
		assertTrue("'middle2' equals 'middle'", middle2.compareTo(middle) == 0);

		assertTrue("'more' > 'less'", more.compareTo(less) > 0);
		assertTrue("'less' < 'more'", less.compareTo(more) < 0);

		assertTrue("'middle' > 'less'", middle.compareTo(less) > 0);
		assertTrue("'less' < 'middle'", less.compareTo(middle) < 0);		

		assertTrue("'middle' < 'more'", middle.compareTo(more) < 0);
		assertTrue("'more' > 'middle'", more.compareTo(middle) > 0);		
	}
	
	public void testSetValue()
	{
		StringAttribute attr = new StringAttribute("test", "OpenEF/J");
		assertEquals("Should be set to 'OpenEF/J'", "OpenEF/J", attr.getValue());
		assertFalse("Should not be changed", attr.isModified());

		attr.setValue("OpenEF/J");
		assertFalse("Should not be changed", attr.isModified());

		attr.setValue("Strategic Gains, Inc.");
		assertTrue("Should be changed", attr.isModified());
	}
}
