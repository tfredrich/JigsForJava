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
package com.jigsforjava.web.annotation;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * Indicates that a multi-action controller method should provide transactional isolates
 * into which to bind edits.  However, these should not be committed to the database.
 * In other words, a transaction is created before the method is called to create a
 * transactional context to read objects into.  But the ApplicationTransaction is
 * released upon action-method completion. 
 * 
 * @author todd
 * @since April 22, 2008
 */
@Target(METHOD)
@Retention(RUNTIME)
public @interface BufferEdits
{
}
