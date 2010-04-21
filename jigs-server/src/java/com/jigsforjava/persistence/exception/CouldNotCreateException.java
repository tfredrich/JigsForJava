package com.jigsforjava.persistence.exception;

@SuppressWarnings("serial")
public class CouldNotCreateException
	extends PersistenceException
{

	public CouldNotCreateException()
	{
		super();
	}

	public CouldNotCreateException(String message, Throwable throwable)
	{
		super(message, throwable);
	}

	public CouldNotCreateException(Throwable throwable)
	{
		super(throwable);
	}

	public CouldNotCreateException(String message)
	{
		super(message);
	}
}
