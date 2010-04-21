/*
	Copyright 2008 Strategic Gains, Inc.

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
package com.jigsforjava.identity;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Todd Fredrich
 * @since Jan 4, 2008
 * @version $Revision: 1.2 $
 */
public class ValueIdentity<T>
extends Identity<T>
{
	//SECTION: VARIABLES

	private LinkedHashMap<String, Object> identifyingAspects;

	
	// SECTION: CONSTRUCTORS

	public ValueIdentity(Class<T> identifiedClass, Map<String, Object> values)
	{
		super(identifiedClass);
		identifyingAspects = new LinkedHashMap<String, Object>(values);
	}


	// SECTION: ACCESSING

	@Override
	public Map<String, Object> getIdentifyingAspects()
	{
		return identifyingAspects;
	}
}
