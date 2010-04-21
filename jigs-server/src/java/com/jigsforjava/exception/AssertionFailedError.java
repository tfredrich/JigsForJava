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
package com.jigsforjava.exception;

/**
 * @author Todd Fredrich
 * @since Mar 11, 2005
 * @version $Revision: 1.1 $
 */
@SuppressWarnings("serial")
public class AssertionFailedError
extends RuntimeException
{
	/**
	 * 
	 */
	public AssertionFailedError()
	{
		super();
	}

	/**
	 * @param message
	 */
	public AssertionFailedError(String message)
	{
		super(message);
	}

	/**
	 * @param message
	 * @param throwable
	 */
	public AssertionFailedError(String message, Throwable throwable)
	{
		super(message, throwable);
	}

	/**
	 * @param throwable
	 */
	public AssertionFailedError(Throwable throwable)
	{
		super(throwable);
	}
}
