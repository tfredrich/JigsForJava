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

import com.jigsforjava.web.model.IntegerAttribute;

import junit.framework.TestCase;

/**
 * @author Todd Fredrich
 * @since  Apr 1, 2004
 */
public class IntegerAttributeTest
	extends TestCase
{
	public IntegerAttributeTest(String testName)
	{
		super(testName);
	}
	
	public void testDefaultConstructor()
	{
		IntegerAttribute attr = new IntegerAttribute("test");
		assertEquals("Should be set to zero", 0.0d, attr.getValue().intValue(), 0.01);
		assertFalse("Should not be changed", attr.isModified());
	}
	
	public void testValueConstructor()
	{
		IntegerAttribute attr = new IntegerAttribute("test", 1234);
		assertEquals("Should be set", 1234, attr.getValue().intValue());
		assertFalse("Should not be changed", attr.isModified());
	}
	
	public void testCompareTo()
	{
		IntegerAttribute middle = new IntegerAttribute("test1", 10);
		IntegerAttribute middle2 = new IntegerAttribute("test2", 10);
		IntegerAttribute less = new IntegerAttribute("test3", 5);
		IntegerAttribute more = new IntegerAttribute("test4", 56);
		
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
		IntegerAttribute attr = new IntegerAttribute("test");
		assertFalse("Should not be changed", attr.isModified());

		attr.setValue(attr.getValue());
		assertFalse("Should not be changed", attr.isModified());

		attr.setValue(47);
		assertTrue("Should be changed", attr.isModified());
	}
}
