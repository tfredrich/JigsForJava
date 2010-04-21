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
package com.jigsforjava.web.model;

import java.util.ArrayList;
import java.util.Collection;

import com.jigsforjava.i18n.I18n;

/**
 * CollectionAttribute contains a collection of PresentationAttributes.
 * 
 * @author Todd Fredrich
 * @since Feb 8, 2006
 * @version $Revision: 1.2 $
 */
public class CollectionAttribute
extends AttributeModel<CollectionAttribute>
{
	private Collection<AttributeModel<CollectionAttribute>> collection = null;
	
	public CollectionAttribute(String name)
	{
		super(name, "Collection");
		initialize(null);
	}

	protected void initialize(Collection<AttributeModel<CollectionAttribute>> value)
	{
		if (collection != null)
		{
			collection = value;
		}
		else
		{
			collection = new ArrayList<AttributeModel<CollectionAttribute>>();
		}
	}

	public void addAttribute(AttributeModel<CollectionAttribute> attribute)
	{
		collection.add(attribute);
	}

	public boolean removeAttribute(AttributeModel<CollectionAttribute> attribute)
	{
		return collection.remove(attribute);
	}

	public boolean containsAttribute(AttributeModel<CollectionAttribute> attribute)
	{
		return collection.contains(attribute);
	}

	public Collection<AttributeModel<CollectionAttribute>> getCollection()
	{
		return collection;
	}

	public void setValue(String valueString)
		throws PresentationModelException
	{
	}

	public String localize(I18n i18n)
	{
		return null;
	}

	public int compareTo(CollectionAttribute arg0)
	{
		return 0;
	}
}
