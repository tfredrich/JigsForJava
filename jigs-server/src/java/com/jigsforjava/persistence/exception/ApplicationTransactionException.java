package com.jigsforjava.persistence.exception;

@SuppressWarnings("serial")
public class ApplicationTransactionException
extends Exception
{
	public ApplicationTransactionException()
	{
		super();
	}

	public ApplicationTransactionException(String message)
	{
		super(message);
	}

	public ApplicationTransactionException(String message, Throwable cause)
	{
		super(message, cause);
	}

	public ApplicationTransactionException(Throwable cause)
	{
		super(cause);
	}
}
