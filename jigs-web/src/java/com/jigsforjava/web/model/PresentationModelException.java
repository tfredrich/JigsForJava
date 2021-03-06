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

/**
 * @author Todd Fredrich
 * @since Dec 13, 2004
 * @version $Revision: 1.3 $
 */
public class PresentationModelException
extends Exception
{
	public PresentationModelException()
	{
		super();
	}

	public PresentationModelException(String message)
	{
		super(message);
	}

	public PresentationModelException(Throwable cause)
	{
		super(cause);
	}

	public PresentationModelException(String message, Throwable cause)
	{
		super(message, cause);
	}
}
