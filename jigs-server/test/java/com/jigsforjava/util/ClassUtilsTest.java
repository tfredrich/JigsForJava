/*
    Copyright 2008, Strategic Gains, Inc.

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

import static org.junit.Assert.*;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;

import org.junit.Test;

/**
 * @author toddf
 * @since Aug 18, 2008
 */
public class ClassUtilsTest
{
	@Test
	public void testGetAllDeclaredFields()
	{
		List<Field> fields = ClassUtils.getAllDeclaredFields(A.class);
		assert(fields.size() == 1);
		assertEquals("fromA", fields.get(0).getName());

		fields = ClassUtils.getAllDeclaredFields(B.class);
		assert(fields.size() == 2);
		assertEquals("fromB", fields.get(0).getName());
		assertEquals("fromA", fields.get(1).getName());

		fields = ClassUtils.getAllDeclaredFields(D.class);
		assert(fields.size() == 2);
		assertEquals("fromD", fields.get(0).getName());
		assertEquals("fromA", fields.get(1).getName());

		fields = ClassUtils.getAllDeclaredFields(C.class);
		assert(fields.size() == 3);
		assertEquals("fromC", fields.get(0).getName());
		assertEquals("fromB", fields.get(1).getName());
		assertEquals("fromA", fields.get(2).getName());
	}

	@Test
	public void testGetAllDeclaredFieldsByName()
	{
		HashMap<String, Field> fields = ClassUtils.getAllDeclaredFieldsByName(A.class);
		assert(fields.size() == 1);
		assertTrue(fields.get("fromA") != null);

		fields = ClassUtils.getAllDeclaredFieldsByName(B.class);
		assert(fields.size() == 2);
		assertTrue(fields.get("fromA") != null);
		assertTrue(fields.get("fromB") != null);

		fields = ClassUtils.getAllDeclaredFieldsByName(D.class);
		assert(fields.size() == 2);
		assertTrue(fields.get("fromA") != null);
		assertTrue(fields.get("fromD") != null);

		fields = ClassUtils.getAllDeclaredFieldsByName(C.class);
		assert(fields.size() == 3);
		assertTrue(fields.get("fromA") != null);
		assertTrue(fields.get("fromB") != null);
		assertTrue(fields.get("fromC") != null);
	}
	
	
	// SECTION: INNER CLASSES

	private class A
	{
		private static final String A_CONSTANT = "string";
		private int fromA;
	}
	
	private class B
	extends A
	{
		private int fromB;
	}
	
	private class C
	extends B
	{
		private int fromC;
	}
	
	private class D
	extends A
	{
		private int fromD;
	}
}
