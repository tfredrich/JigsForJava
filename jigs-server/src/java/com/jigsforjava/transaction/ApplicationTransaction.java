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
package com.jigsforjava.transaction;

import com.jigsforjava.persistence.exception.CouldNotBeginException;
import com.jigsforjava.persistence.exception.CouldNotCommitException;
import com.jigsforjava.persistence.exception.CouldNotRollbackException;

/**
 * Defines the interface for an application-controlled transaction.
 * 
 * @author toddf
 * @since Aug 5, 2008
 */
public interface ApplicationTransaction
{
	public void begin()
	throws CouldNotBeginException;
	
	public void commit()
	throws CouldNotCommitException;
	
	public void rollback()
	throws CouldNotRollbackException;
}
