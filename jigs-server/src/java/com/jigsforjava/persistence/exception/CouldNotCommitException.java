package com.jigsforjava.persistence.exception;

@SuppressWarnings("serial")
public class CouldNotCommitException
	extends ApplicationTransactionException
{
	public CouldNotCommitException()
	{
		super();
	}

	public CouldNotCommitException(String message)
	{
		super(message);
	}

	public CouldNotCommitException(String message, Throwable cause)
	{
		super(message, cause);
	}

	public CouldNotCommitException(Throwable cause)
	{
		super(cause);
	}
}
