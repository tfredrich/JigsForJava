/**
 * Copyright (C)2008, Strategic Gains, Inc.  All rights reserved.
 */
package com.jigsforjava.web.controller;

import com.jigsforjava.web.annotation.BufferEdits;
import com.jigsforjava.web.annotation.NoCommandBean;
import com.jigsforjava.web.annotation.SuppressValidation;
import com.jigsforjava.web.annotation.Transactional;
import com.jigsforjava.web.context.ActionContext;
import com.jigsforjava.web.context.ViewContext;

/**
 * A type of MultiActionFormController that handles transactions across request/response
 * cycles based on annotations: @BufferEdits, @NoCommandBean, @SuppressValidation, @Transactional.
 * 
 * @author Todd Fredrich
 * @since May 14, 2008
 * @see {@link BufferEdits}
 * @see {@link NoCommandBean}
 * @see {@link SuppressValidation}
 * @see {@link Transactional}
 */
public abstract class TransactionalMultiActionController
extends MultiActionController
{
	//SECTION: CONSTRUCTORS

    public TransactionalMultiActionController()
    {
    	super();
    }

    public TransactionalMultiActionController(Object delegate)
    {
    	super(delegate);
    }
    
    
    //SECTION: TEMPLATE METHODS - PRE/POST HANDLER INVOCATION
    
	@Override
    protected void preHandlerInvocation(ActionContext actionContext)
    {
		if (actionContext.hasHandlerAnnotation(Transactional.class)
			|| actionContext.hasHandlerAnnotation(BufferEdits.class))
		{
			// TODO: Start an application transaction (e.g. Eclipselink UoW).
		}
    }

	@Override
    protected void postHandlerInvocation(ActionContext actionContext, ViewContext viewContext)
    {
		if (actionContext.hasHandlerAnnotation(Transactional.class))
		{
			// TODO: Commit the transaction/UoW.
		}
		else if (actionContext.hasHandlerAnnotation(BufferEdits.class))
		{
			// TODO: Release/rollback the transaction/UoW.
		}
    }
}
