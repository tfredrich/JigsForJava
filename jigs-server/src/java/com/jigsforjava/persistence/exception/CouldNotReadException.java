package com.jigsforjava.persistence.exception;

@SuppressWarnings("serial")
public class CouldNotReadException
	extends PersistenceException
{
	public CouldNotReadException()
	{
		super();
	}

	public CouldNotReadException(String arg0, Throwable arg1)
	{
		super(arg0, arg1);
	}

	public CouldNotReadException(Throwable arg0)
	{
		super(arg0);
	}

	public CouldNotReadException(String s)
	{
		super(s);
	}
}
