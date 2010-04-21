package com.jigsforjava.identity;



/**
 *  Interface to implement when an object contains an (primary) identity.
 *
 * @author  Todd Fredrich
 * @created  September 2, 2002
 * @version $Revision: 1.3 $
 */
public interface Identifiable<T>
{
	/**
	 *  Gets the Identity an Identifiable object.
	 *
	 * @return  The Identity value.
	 */
	public Identity<T> getIdentity();
}
