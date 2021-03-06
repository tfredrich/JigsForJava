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

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * Annotates an object property, specifying a privately-held, one-to-one
 * relationship to another object.
 * 
 * @author toddf
 * @since Sep 6, 2007
 */
@Target (FIELD)
@Retention (RUNTIME)
public @interface OwnsOne
{
	Class<? extends com.jigsforjava.domain.Entity<?>> target();
	
	String foreignKey() default "";
	
	boolean useTargetForeignKey() default false;

	String targetForeignKey() default "";
	
	boolean isReadOnly() default false;
}
