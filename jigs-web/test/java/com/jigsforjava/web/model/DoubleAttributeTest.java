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

import com.jigsforjava.web.model.DoubleAttribute;

import junit.framework.TestCase;

/**
 * @author Todd Fredrich
 * @since  Mar 30, 2004
 */
public class DoubleAttributeTest
	extends TestCase
{
	public DoubleAttributeTest(String testName)
	{
		super(testName);
	}
	
	public void testDefaultConstructor()
	{
		DoubleAttribute attr = new DoubleAttribute("test");
		assertEquals("Should be set to zero", 0.0d, attr.getValue().doubleValue(), 0.01);
		assertFalse("Should not be changed", attr.isModified());
	}
	
	public void testValueConstructor()
	{
		DoubleAttribute attr = new DoubleAttribute("test", 1234.56);
		assertEquals("Should be set", 1234.56, attr.getValue().doubleValue(), 0.01);
		assertFalse("Should not be changed", attr.isModified());
	}
	
	public void testCompareTo()
	{
		DoubleAttribute middle = new DoubleAttribute("test1", 10.24);
		DoubleAttribute middle2 = new DoubleAttribute("test2", 10.24);
		DoubleAttribute less = new DoubleAttribute("test3", 5.22);
		DoubleAttribute more = new DoubleAttribute("test4", 56.78);
		
		assertTrue("middle equals middle2", middle.compareTo(middle2) == 0);
		assertTrue("middle2 equals middle", middle2.compareTo(middle) == 0);

		assertTrue("middle < more", middle.compareTo(more) < 0);
		assertTrue("more > attr", more.compareTo(middle) > 0);

		assertTrue("middle > less", middle.compareTo(less) > 0);
		assertTrue("less < middle", less.compareTo(middle) < 0);

		assertTrue("more > less", more.compareTo(less) > 0);
		assertTrue("less < more", less.compareTo(more) < 0);
	}
	
	public void testSetValue()
	{
		DoubleAttribute attr = new DoubleAttribute("test");
		assertFalse("Should not be changed", attr.isModified());

		attr.setValue(attr.getValue());
		assertFalse("Should not be changed", attr.isModified());

		attr.setValue(47);
		assertTrue("Should be changed", attr.isModified());
	}
}
