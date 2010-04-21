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
 * Used on object properties to denote that a DirectToField mapping
 * for the property will not be automatically created for this field.
 * It can either be mapped by hand or not persisted.
 * 
 * @author toddf
 * @since Aug 28, 2007
 */
@Target (FIELD)
@Retention (RUNTIME)
public @interface UnpersistedProperty
{
}
