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
package com.jigsforjava.web.tags;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;

import org.springframework.web.servlet.tags.HtmlEscapingAwareTag;
import org.springframework.web.servlet.tags.form.TagWriter;

/**
 * Base class for all non-data-bound HTML tags.
 * 
 * @author toddf
 * @since Aug 18, 2008
 */
public abstract class HtmlTag
extends HtmlEscapingAwareTag
{
	// SECTION: CONSTANTS

	public static final String ID_ATTRIBUTE = "id";
	public static final String NAME_ATTRIBUTE = "name";
	public static final String CLASS_ATTRIBUTE = "class";
	public static final String STYLE_ATTRIBUTE = "style";
	public static final String TITLE_ATTRIBUTE = "title";
	public static final String TABINDEX_ATTRIBUTE = "tabindex";
	public static final String ONCLICK_ATTRIBUTE = "onclick";
	public static final String ONDBLCLICK_ATTRIBUTE = "ondblclick";
	public static final String ONMOUSEDOWN_ATTRIBUTE = "onmousedown";
	public static final String ONMOUSEUP_ATTRIBUTE = "onmouseup";
	public static final String ONMOUSEOVER_ATTRIBUTE = "onmouseover";
	public static final String ONMOUSEMOVE_ATTRIBUTE = "onmousemove";
	public static final String ONMOUSEOUT_ATTRIBUTE = "onmouseout";
	public static final String ONKEYPRESS_ATTRIBUTE = "onkeypress";
	public static final String ONKEYUP_ATTRIBUTE = "onkeyup";
	public static final String ONKEYDOWN_ATTRIBUTE = "onkeydown";
	
	
	// SECTION: INSTANCE VARIABLES
	
	private String tagName;
	private TagWriter writer;

	
	// SECTION: CONSTRUCTORS
	
	public HtmlTag(String name)
	{
		super();
		this.tagName = name;
	}

	// SECTION: ACCESSORS/MUTATORS

	public String getTagName()
	{
		return tagName;
	}

	/**
	 * Set the value of the '<code>class</code>' attribute. May be a runtime
	 * expression.
	 */
	public void setCssClass(String cssClass)
	{
		setValue(CLASS_ATTRIBUTE, cssClass);
	}

	/**
	 * Get the value of the '<code>class</code>' attribute. May be a runtime
	 * expression.
	 */
	protected String getCssClass()
	{
		return (String) getValue(CLASS_ATTRIBUTE);
	}

	/**
	 * Set the value of the '<code>style</code>' attribute. May be a runtime
	 * expression.
	 */
	public void setCssStyle(String cssStyle)
	{
		setValue(STYLE_ATTRIBUTE, cssStyle);
	}

	/**
	 * Get the value of the '<code>style</code>' attribute. May be a runtime
	 * expression.
	 */
	protected String getCssStyle()
	{
		return (String) getValue(STYLE_ATTRIBUTE);
	}

	public void setId(String id)
	{
		setValue(ID_ATTRIBUTE, id);
	}
	
	public String getId()
	{
		return (String) getValue(ID_ATTRIBUTE);
	}
	
	public void setName(String name)
	{
		setValue(NAME_ATTRIBUTE, name);
	}
	
	public String getName()
	{
		return (String) getValue(NAME_ATTRIBUTE);
	}

	/**
	 * Set the value of the '<code>title</code>' attribute. May be a runtime
	 * expression.
	 */
	public void setTitle(String title)
	{
		setValue(TITLE_ATTRIBUTE, title);
	}

	/**
	 * Get the value of the '<code>title</code>' attribute. May be a runtime
	 * expression.
	 */
	protected String getTitle()
	{
		return (String) getValue(TITLE_ATTRIBUTE);
	}

	/**
	 * Set the value of the '<code>tabindex</code>' attribute. May be a runtime
	 * expression.
	 */
	public void setTabindex(String tabindex)
	{
		setValue(TABINDEX_ATTRIBUTE, tabindex);
	}

	/**
	 * Get the value of the '<code>tabindex</code>' attribute. May be a runtime
	 * expression.
	 */
	protected String getTabindex()
	{
		return (String) getValue(TABINDEX_ATTRIBUTE);
	}

	/**
	 * Set the value of the '<code>onclick</code>' attribute. May be a runtime
	 * expression.
	 */
	public void setOnclick(String onclick)
	{
		setValue(ONCLICK_ATTRIBUTE, onclick);
	}

	/**
	 * Get the value of the '<code>onclick</code>' attribute. May be a runtime
	 * expression.
	 */
	protected String getOnclick()
	{
		return (String) getValue(ONCLICK_ATTRIBUTE);
	}

	/**
	 * Set the value of the '<code>ondblclick</code>' attribute. May be a
	 * runtime expression.
	 */
	public void setOndblclick(String ondblclick)
	{
		setValue(ONDBLCLICK_ATTRIBUTE, ondblclick);
	}

	/**
	 * Get the value of the '<code>ondblclick</code>' attribute. May be a
	 * runtime expression.
	 */
	protected String getOndblclick()
	{
		return (String) getValue(ONDBLCLICK_ATTRIBUTE);
	}

	/**
	 * Set the value of the '<code>onmousedown</code>' attribute. May be a
	 * runtime expression.
	 */
	public void setOnmousedown(String onmousedown)
	{
		setValue(ONMOUSEDOWN_ATTRIBUTE, onmousedown);
	}

	/**
	 * Get the value of the '<code>onmousedown</code>' attribute. May be a
	 * runtime expression.
	 */
	protected String getOnmousedown()
	{
		return (String) getValue(ONMOUSEDOWN_ATTRIBUTE);
	}

	/**
	 * Set the value of the '<code>onmouseup</code>' attribute. May be a runtime
	 * expression.
	 */
	public void setOnmouseup(String onmouseup)
	{
		setValue(ONMOUSEUP_ATTRIBUTE, onmouseup);
	}

	/**
	 * Get the value of the '<code>onmouseup</code>' attribute. May be a runtime
	 * expression.
	 */
	protected String getOnmouseup()
	{
		return (String) getValue(ONMOUSEUP_ATTRIBUTE);
	}

	/**
	 * Set the value of the '<code>onmouseover</code>' attribute. May be a
	 * runtime expression.
	 */
	public void setOnmouseover(String onmouseover)
	{
		setValue(ONMOUSEOVER_ATTRIBUTE, onmouseover);
	}

	/**
	 * Get the value of the '<code>onmouseover</code>' attribute. May be a
	 * runtime expression.
	 */
	protected String getOnmouseover()
	{
		return (String) getValue(ONMOUSEOVER_ATTRIBUTE);
	}

	/**
	 * Set the value of the '<code>onmousemove</code>' attribute. May be a
	 * runtime expression.
	 */
	public void setOnmousemove(String onmousemove)
	{
		setValue(ONMOUSEMOVE_ATTRIBUTE, onmousemove);
	}

	/**
	 * Get the value of the '<code>onmousemove</code>' attribute. May be a
	 * runtime expression.
	 */
	protected String getOnmousemove()
	{
		return (String) getValue(ONMOUSEMOVE_ATTRIBUTE);
	}

	/**
	 * Set the value of the '<code>onmouseout</code>' attribute. May be a
	 * runtime expression.
	 */
	public void setOnmouseout(String onmouseout)
	{
		setValue(ONMOUSEOUT_ATTRIBUTE, onmouseout);
	}

	/**
	 * Get the value of the '<code>onmouseout</code>' attribute. May be a
	 * runtime expression.
	 */
	protected String getOnmouseout()
	{
		return (String) getValue(ONMOUSEOUT_ATTRIBUTE);
	}

	/**
	 * Set the value of the '<code>onkeypress</code>' attribute. May be a
	 * runtime expression.
	 */
	public void setOnkeypress(String onkeypress)
	{
		setValue(ONKEYPRESS_ATTRIBUTE, onkeypress);
	}

	/**
	 * Get the value of the '<code>onkeypress</code>' attribute. May be a
	 * runtime expression.
	 */
	protected String getOnkeypress()
	{
		return (String) getValue(ONKEYPRESS_ATTRIBUTE);
	}

	/**
	 * Set the value of the '<code>onkeyup</code>' attribute. May be a runtime
	 * expression.
	 */
	public void setOnkeyup(String onkeyup)
	{
		setValue(ONKEYUP_ATTRIBUTE, onkeyup);
	}

	/**
	 * Get the value of the '<code>onkeyup</code>' attribute. May be a runtime
	 * expression.
	 */
	protected String getOnkeyup()
	{
		return (String) getValue(ONKEYUP_ATTRIBUTE);
	}

	/**
	 * Set the value of the '<code>onkeydown</code>' attribute. May be a runtime
	 * expression.
	 */
	public void setOnkeydown(String onkeydown)
	{
		setValue(ONKEYDOWN_ATTRIBUTE, onkeydown);
	}

	/**
	 * Get the value of the '<code>onkeydown</code>' attribute. May be a runtime
	 * expression.
	 */
	protected String getOnkeydown()
	{
		return (String) getValue(ONKEYDOWN_ATTRIBUTE);
	}


	// SECTION: REQUEST CONTEXT AWARE TAG

	@Override
	protected int doStartTagInternal()
	throws Exception
	{
		writer = new TagWriter(this.pageContext);
    	writer.startTag(getTagName());
		int returnValue = writeTagContents(writer);
    	writeAttributes(writer);
		postStartTagInternal(writer);
		return returnValue;
	}

	protected void postStartTagInternal(TagWriter writer)
	{
	}
	
	@Override
	public int doEndTag()
	{
		try
        {
	        writer.endTag();
        }
        catch (JspException e)
        {
	        e.printStackTrace();
        }
       
		return EVAL_PAGE;
	}

    private void writeAttributes(TagWriter tagWriter)
    throws JspException
    {
    	tagWriter.writeOptionalAttributeValue(ONCLICK_ATTRIBUTE, getOnclick());
    	tagWriter.writeOptionalAttributeValue(ID_ATTRIBUTE, getId());
    	tagWriter.writeOptionalAttributeValue(NAME_ATTRIBUTE, getName());
    	tagWriter.writeOptionalAttributeValue(CLASS_ATTRIBUTE, getCssClass());
    	tagWriter.writeOptionalAttributeValue(STYLE_ATTRIBUTE, getCssStyle());
    	tagWriter.writeOptionalAttributeValue(TITLE_ATTRIBUTE, getTitle());
    	tagWriter.writeOptionalAttributeValue(TABINDEX_ATTRIBUTE, getTabindex());
    	tagWriter.writeOptionalAttributeValue(ONCLICK_ATTRIBUTE, getOnclick());
    	tagWriter.writeOptionalAttributeValue(ONDBLCLICK_ATTRIBUTE, getOndblclick());
    	tagWriter.writeOptionalAttributeValue(ONMOUSEDOWN_ATTRIBUTE, getOnmousedown());
    	tagWriter.writeOptionalAttributeValue(ONMOUSEUP_ATTRIBUTE, getOnmouseup());
    	tagWriter.writeOptionalAttributeValue(ONMOUSEOVER_ATTRIBUTE, getOnmouseover());
    	tagWriter.writeOptionalAttributeValue(ONMOUSEMOVE_ATTRIBUTE, getOnmousemove());
    	tagWriter.writeOptionalAttributeValue(ONMOUSEOUT_ATTRIBUTE, getOnmouseout());
    	tagWriter.writeOptionalAttributeValue(ONKEYPRESS_ATTRIBUTE, getOnkeypress());
    	tagWriter.writeOptionalAttributeValue(ONKEYUP_ATTRIBUTE, getOnkeyup());
    	tagWriter.writeOptionalAttributeValue(ONKEYDOWN_ATTRIBUTE, getOnkeydown());
    }

	protected abstract int writeTagContents(TagWriter tagWriter)
	throws Exception;
	
	
	// SECTION: UTILITY
	
	protected HttpServletRequest getRequest()
	{
		return ((HttpServletRequest) pageContext.getRequest());
	}
}
