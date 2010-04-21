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
package com.jigsforjava.domain;

import java.util.UUID;

import com.jigsforjava.identity.Identity;
import com.jigsforjava.identity.EntityIdentity;
import com.jigsforjava.identity.TransientObjectIdentity;
import com.jigsforjava.persistence.annotation.UnpersistedProperty;

/**
 * Base object for domain objects that are persistent (stored in a database)
 * and have object identity.
 * 
 * @author Todd Fredrich
 * @created  December 10, 2004
 */
public abstract class AbstractEntity<T extends AbstractEntity<T>>
extends DomainObject<T>
implements Comparable<T>, Entity<T>
{
	// SECTION: CONSTANTS
	
	public final static String OID_PROPERTY = "oid";


	//SECTION: VARIABLES

	/**
	 * The unique object identifier for this domain object, if persistent.
	 * Null if not persistent (yet).
	 */
	private Long oid;
	
	/**
	 * The unique object identifier for this domain object before it is
	 * persistent; the transient identifier.
	 */
	@UnpersistedProperty
	private UUID uuid;


	//SECTION: CONSTRUCTORS

	public AbstractEntity()
	{
		super();
		initializeIdentity();
	}
	
	
	//SECTION: INITIALIZATION

	private void initializeIdentity()
	{
		oid = null;
		uuid = UUID.randomUUID();
	}

	// PROTOCOL: COMPARING

	/**
	 * Compares this object to that based on the primary identities of the two objects.
	 *  
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(T that)
	{
		return getIdentity().compareTo(that.getIdentity());
	}

	/**
	 * Tests equality of the primary identities.
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
    @Override
	@SuppressWarnings("unchecked")
	public final boolean equals(Object that)
	{
		if (!(that instanceof AbstractEntity))
			return false;

		return getIdentity().equals(((AbstractEntity<T>)that).getIdentity());
	}
	
	@Override
	public int hashCode()
	{
		return getIdentity().hashCode();
	}


	// PROTOCOL: ACCESSING

    @Override
	@SuppressWarnings("unchecked")
	public Identity<T> getIdentity()
	{
		if (isTransient())
		{
			return new TransientObjectIdentity<T>((Class<T>)getClass(), getTransientIdentifier());
		}

		return new EntityIdentity<T>((Class<T>)getClass(), getOid());
	}
	
	protected UUID getTransientIdentifier()
	{
		return uuid;
	}
	
	protected boolean isTransient()
	{
		return !isPersistent();
	}
	
	public boolean isPersistent()
	{
		return hasOid();
	}
	
	public Long getOid()
	{
		return oid;
	}
	
	public boolean hasOid()
	{
		return (getOid() != null);
	}
	
	public void setOid(Long oid)
	{
		this.oid = oid;
	}
}
