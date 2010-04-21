package com.jigsforjava.persistence.exception;

@SuppressWarnings("serial")
public class CouldNotUpdateException
	extends PersistenceException
{
	public CouldNotUpdateException()
	{
		super();
	}

	public CouldNotUpdateException(String arg0, Throwable arg1)
	{
		super(arg0, arg1);
	}

	public CouldNotUpdateException(Throwable arg0)
	{
		super(arg0);
	}

	public CouldNotUpdateException(String s)
	{
		super(s);
	}
}
