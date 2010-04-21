package com.jigsforjava.persistence.exception;

@SuppressWarnings("serial")
public class CouldNotDeleteException
	extends PersistenceException
{
	public CouldNotDeleteException()
	{
		super();
	}

	public CouldNotDeleteException(String arg0, Throwable arg1)
	{
		super(arg0, arg1);
	}

	public CouldNotDeleteException(Throwable arg0)
	{
		super(arg0);
	}

	public CouldNotDeleteException(String s)
	{
		super(s);
	}
}
