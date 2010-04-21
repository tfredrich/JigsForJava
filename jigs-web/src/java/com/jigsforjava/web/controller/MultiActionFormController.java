/*
    Copyright 2008, Strategic Gains, Inc.

	Licensed under the Apache License, Version 2.0 (the "License");
	you may not use this file except in compliance with the License.
	You may obtain a copy of the License at

		http://www.apache.org/licenses/LICENSE-2.0

	Unless required by applicable law or agreed to in writing, software
	distributed under the License is distributed on an "AS IS" BASIS,
	WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
	See the License for the specific language governing permissions and
	limitations under the License.
*/
package com.jigsforjava.web.controller;

import org.springframework.validation.BindingResult;

import com.jigsforjava.web.annotation.NoCommandBean;
import com.jigsforjava.web.annotation.SuppressValidation;
import com.jigsforjava.web.context.ActionContext;
import com.jigsforjava.web.context.ViewContext;

/**
 * @author toddf
 * @since Aug 29, 2008
 */
public abstract class MultiActionFormController<T>
extends TransactionalMultiActionController
{
	// SECTION: INSTANCE VARIABLES
	
//	private Binder binder;
//	private Validator validator;
	
	
	// SECTION: CONSTRUCTORS

    public MultiActionFormController()
    {
	    super();
    }

    public MultiActionFormController(Object delegate)
    {
	    super(delegate);
    }
    

    // SECTION: MESSAGE HANDLING

	@Override
    protected void preHandlerInvocation(ActionContext actionContext)
    {
	    super.preHandlerInvocation(actionContext);
	    
	    if (!actionContext.hasHandlerAnnotation(NoCommandBean.class))
	    {
	    	readCommandBean(actionContext);
	    }
    }

	@Override
    protected void postHandlerInvocation(ActionContext actionContext, ViewContext viewContext)
    {
		BindingResult bindingResult = performBinding(actionContext, viewContext);
		performValidation(actionContext, viewContext, bindingResult);
	    super.postHandlerInvocation(actionContext, viewContext);
    }
    
    
    // SECTION: BINDING & VALIDATION
    
	private BindingResult performBinding(ActionContext actionContext, ViewContext viewContext)
	{
		prebinding(actionContext, viewContext);

		//TODO: perform binding.
		BindingResult bindingResult = null;

		postbinding(actionContext, viewContext, bindingResult);
		return bindingResult;
	}
	
	private void performValidation(ActionContext actionContext, ViewContext viewContext, BindingResult bindingResult)
	{
		if (!actionContext.hasHandlerAnnotation(SuppressValidation.class))
		{
			//TODO: perform validation.
		}
	}

	
	// SECTION: SUB-CLASS RESPONSIBILITIES
	
	protected abstract void readCommandBean(ActionContext actionContext);

	protected void prebinding(ActionContext actionContext, ViewContext viewContext)
	{
	}

	protected void postbinding(ActionContext actionContext, ViewContext viewContext, BindingResult bindingResult)
	{
	}
}
