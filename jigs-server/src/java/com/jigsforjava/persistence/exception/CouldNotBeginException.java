package com.jigsforjava.persistence.exception;

@SuppressWarnings("serial")
public class CouldNotBeginException
extends ApplicationTransactionException
{

	public CouldNotBeginException()
	{
		super();
	}

	public CouldNotBeginException(String message)
	{
		super(message);
	}

	public CouldNotBeginException(String message, Throwable cause)
	{
		super(message, cause);
	}

	public CouldNotBeginException(Throwable cause)
	{
		super(cause);
	}
}
