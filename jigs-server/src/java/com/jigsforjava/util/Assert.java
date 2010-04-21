/*
	Copyright 2005 Strategic Gains, Inc.

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

import com.jigsforjava.exception.AssertionFailedError;


/**
 * @author Todd Fredrich
 * @since Mar 11, 2005
 * @version $Revision: 1.2 $
 */
public abstract class Assert
{
	public static void notNull(Object object)
	{
		if (object == null)
		{
			throw new AssertionFailedError("Object must not be null: " + object.toString());
		}
	}
	
	public static void unReachable()
	{
		unReachable("Block of code is not to be reachable.");
	}

	public static void unReachable(String message)
	{
		throw new AssertionFailedError(message);
	}
}
