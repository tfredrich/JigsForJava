package com.jigsforjava.domain;

import com.jigsforjava.identity.Identifiable;


/**
 * @author Todd Fredrich
 * @since Dec 16, 2004
 * @version $Revision: 1.2 $
 */
public interface Entity<T>
extends Identifiable<T>
{
	public Long getOid();
	public void setOid(Long oid);
	public boolean isPersistent();
}
