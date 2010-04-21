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
package com.jigsforjava.persistence.annotation;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * Annotates the object, specifying persistence properties.
 * 
 * @author toddf
 * @since Sep 6, 2007
 */
@Target (TYPE)
@Retention (RUNTIME)
public @interface Entity
{
	/**
	 * Specifies a non-convention-based table name where the
	 * object is to be stored.
	 */
	String table() default "";
	
	
	/**
	 * Specifies a non-convention-based primary key name for
	 * the table where the object is to be stored.
	 */
	String primaryKey() default "";
	
	boolean isReadOnly() default false;
	
	/**
	 * Specifies the inheritance for this object, defining
	 * this object as a child of another--that must have a 
	 * corresponding parentOf annotation referring to this
	 * class.
	 */
	Class<?> childOf() default void.class;

	/**
	 * Specifies the inheritance for this object, defining
	 * this object as a parent of another (possibly several)
	 * objects--which must have corresponding childOf
	 * annotations referring to this class.
	 */
	Class<?>[] parentOf() default {};
}
