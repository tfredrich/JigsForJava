package com.jigsforjava.persistence.exception;

/**
 *  An exception thrown by objects in the persistence package when something
 *  to do with persistence goes awry.
 *
 * @author  Todd Fredrich
 * @created  July 6, 2001
 * @version  $Revision$
 */
@SuppressWarnings("serial")
public class PersistenceException
extends Exception
{
	/**
	 *  Constructor for the PersistenceException object
	 */
	public PersistenceException()
	{
		super();
	}

	/**
	 * @param message
	 * @param throwable
	 */
	public PersistenceException(String message, Throwable throwable)
	{
		super(message, throwable);
	}

	/**
	 * @param throwable
	 */
	public PersistenceException(Throwable throwable)
	{
		super(throwable);
	}

	/**
	 *  Constructor for the PersistenceException object
	 *
	 * @param message a message to include in the exception
	 */
	public PersistenceException(String message)
	{
		super(message);
	}
}
