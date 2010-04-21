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
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Annotates an object property, specifying a one-to-many relationship to
 * another object.  Note that the referenced object must have a corresponding
 * <pre>@BelongsTo</pre> annotation on the attribute holding the one-to-one 
 * back-reference. 
 * 
 * @author toddf
 * @since Sep 6, 2007
 */
@Target (FIELD)
@Retention (RUNTIME)
public @interface HasMany
{
	Class<? extends com.jigsforjava.domain.Entity<?>> target();
	
	boolean isReadOnly() default false;
	
	boolean isMap() default false;
	
	Class<?> collectionClass() default ArrayList.class;
	
	Class<?> mapClass() default HashMap.class;
	
	String mapKeyAccessor() default "";
}
