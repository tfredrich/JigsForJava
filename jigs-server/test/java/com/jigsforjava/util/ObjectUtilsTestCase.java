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

import java.util.List;

import com.jigsforjava.domain.AbstractEntity;
import com.jigsforjava.domain.ValidationError;

import junit.framework.TestCase;

/**
 * @author todd
 * @since Jun 2, 2008
 */
public class ObjectUtilsTestCase
extends TestCase
{
	/**
	 * Test method for {@link com.jigsforjava.util.ObjectUtils#areEqual(java.lang.Object, java.lang.Object)}.
	 */
	public void testAreEqual()
	{
		assertTrue(ObjectUtils.areEqual(new BasicEntity(1), new BasicEntity(1)));
		assertFalse(ObjectUtils.areEqual(new BasicEntity(1), new BasicEntity(2)));
		assertFalse(ObjectUtils.areEqual(new BasicEntity(2), new BasicEntity(1)));
	}

	/**
	 * Test method for {@link com.jigsforjava.util.ObjectUtils#areEqualComparables(java.lang.Comparable, java.lang.Comparable)}.
	 */
	public void testAreEqualComparables()
	{
		assertTrue(ObjectUtils.areEqualComparables(new BasicEntity(1), new BasicEntity(1)));
		assertFalse(ObjectUtils.areEqualComparables(new BasicEntity(1), new BasicEntity(2)));
		assertFalse(ObjectUtils.areEqualComparables(new BasicEntity(2), new BasicEntity(1)));
	}

	/**
	 * Test method for {@link com.jigsforjava.util.ObjectUtils#compareTo(java.lang.Object, java.lang.Object)}.
	 */
	public void testCompareToWithLong()
	{
		assertEquals(-1, ObjectUtils.compareTo(new Long(1), new Long(2)));
		assertEquals(0, ObjectUtils.compareTo(new Long(2), new Long(2)));
		assertEquals(1, ObjectUtils.compareTo(new Long(2), new Long(1)));
	}

	/**
	 * Test method for {@link com.jigsforjava.util.ObjectUtils#compareTo(java.lang.Object, java.lang.Object)}.
	 */
	public void testCompareToWithInt()
	{
		assertEquals(-1, ObjectUtils.compareTo(1, 2));
		assertEquals(0, ObjectUtils.compareTo(2, 2));
		assertEquals(1, ObjectUtils.compareTo(2, 1));
	}

	/**
	 * Test method for {@link com.jigsforjava.util.ObjectUtils#isComparable(java.lang.Object)}.
	 */
	public void testIsComparable()
	{
		assertTrue(ObjectUtils.isComparable(new Long(0)));
		assertFalse(ObjectUtils.isComparable(new Object()));
	}
	
	
	// SECTION: UTILITY
	
	private class BasicEntity
	extends AbstractEntity<BasicEntity>
	{
		public BasicEntity(int oid)
		{
			super();
			setOid(new Long(oid));
		}

		/* (non-Javadoc)
         * @see com.jigsforjava.domain.DomainObject#validateInto(java.util.List)
         */
        @Override
        protected void validateInto(List<ValidationError> validationErrors)
        {
        }
	}
}
