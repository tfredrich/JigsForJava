package com.jigsforjava.persistence.exception;

@SuppressWarnings("serial")
public class CouldNotRollbackException
extends ApplicationTransactionException
{
	public CouldNotRollbackException()
	{
		super();
	}

	public CouldNotRollbackException(String message)
	{
		super(message);
	}

	public CouldNotRollbackException(String message, Throwable cause)
	{
		super(message, cause);
	}

	public CouldNotRollbackException(Throwable cause)
	{
		super(cause);
	}
}
