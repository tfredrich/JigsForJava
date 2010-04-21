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

import com.jigsforjava.web.model.BooleanAttribute;

/**
 * @author Todd Fredrich
 * @since  Mar 30, 2004
 */
public class BooleanAttributeTest
	extends TestCase
{
	public BooleanAttributeTest(String testName)
	{
		super(testName);
	}
	
	public void testDefaultConstructor()
	{
		BooleanAttribute ba = new BooleanAttribute("test");
		assertFalse("Should default to 'false'", ba.getValue().booleanValue());
		assertFalse("Should not be changed", ba.isModified());
	}
	
	public void testValueConstructor()
	{
		BooleanAttribute ba = new BooleanAttribute("test", true);
		assertTrue("Should be set to 'true'", ba.getValue().booleanValue());
		assertFalse("Should not be changed", ba.isModified());
	}
	
	public void testCompareTo()
	{
		BooleanAttribute baTrue1 = new BooleanAttribute("test1", true);
		BooleanAttribute baTrue2 = new BooleanAttribute("test2", true);
		BooleanAttribute baFalse1 = new BooleanAttribute("test3");
		BooleanAttribute baFalse2 = new BooleanAttribute("test4");
		
		assertTrue("'true' equals 'true'", baTrue1.compareTo(baTrue2) == 0);
		assertTrue("'true' equals 'true'", baTrue2.compareTo(baTrue1) == 0);

		assertTrue("'false' equals 'false'", baFalse1.compareTo(baFalse2) == 0);
		assertTrue("'false' equals 'false'", baFalse2.compareTo(baFalse1) == 0);

		assertTrue("'true' greater-than 'false'", baTrue1.compareTo(baFalse1) > 0);
		assertTrue("'false' less-than 'true'", baFalse1.compareTo(baTrue1) < 0);		
	}
	
	public void testSetValue()
	{
		BooleanAttribute ba = new BooleanAttribute("test", true);
		assertTrue("Should be set to 'true'", ba.getValue().booleanValue());
		assertFalse("Should not be changed", ba.isModified());

		ba.setValue(true);
		assertFalse("Should not be changed", ba.isModified());

		ba.setValue(false);
		assertTrue("Should be changed", ba.isModified());
	}
}
