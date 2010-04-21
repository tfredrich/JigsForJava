package com.jigsforjava.persistence.eclipselink;

import org.eclipse.persistence.sessions.DatabaseLogin;
import org.eclipse.persistence.sessions.Login;
import org.eclipse.persistence.sessions.Project;

@SuppressWarnings("serial")
public abstract class AbstractEclipseLinkProject
extends Project
{
	// SECTION: CONSTRUCTORS

	public AbstractEclipseLinkProject()
	{
		super();
		configureDescriptors();
	}

	public AbstractEclipseLinkProject(Login login)
	{
		super(login);
		configureDescriptors();
	}

	public AbstractEclipseLinkProject(DatabaseLogin login)
	{
		super(login);
		configureDescriptors();
	}

	
	// SECTION: SUB-CLASS RESPONSIBILITIES

	protected abstract void configureDescriptors();
}
