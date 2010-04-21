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

/**
 * 
 * 
 * @author Todd Fredrich
 * @since  Apr 6, 2004
 * @version $Revision: 1.3 $
 */
public class PresentationModelTest
	extends TestCase
{
	/**
	 * Constructor for DefaultDataTransferObjectTest.
	 */
	public PresentationModelTest(String testName)
	{
		super(testName);
	}

	public void testIsOk()
	{
		PersonModel dto = new PersonModel();
		assertFalse("Presentation model isAmazed() is true", dto.isAmazed());
		
		dto.setAmazed(true);
		assertTrue("Presentation model isAmazed() is still false", dto.isAmazed());
	}
}
