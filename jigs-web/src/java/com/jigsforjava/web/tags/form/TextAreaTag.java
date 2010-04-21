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
package com.jigsforjava.web.tags.form;

import javax.servlet.jsp.JspException;

import org.springframework.web.servlet.tags.form.TagWriter;
import org.springframework.web.servlet.tags.form.TextareaTag;

import com.jigsforjava.string.StringUtils;

/**
 * TextArea tag that supports the limiting of length via the maxlength attribute.
 * Requires limitLength() javascript function in src/javascript/jig.js
 * 
 * @author toddf
 * @since Sep 10, 2008
 */
public class TextAreaTag
extends TextareaTag
{
	// SECTION: INSTANCE VARIABLES

	private String maxlength;

	
	// SECTION: ACCESSORS/MUTATORS

	public String getMaxlength()
    {
    	return maxlength;
    }

	public void setMaxlength(String maxLength)
    {
    	this.maxlength = maxLength;
    }
	
	public boolean hasMaxLength()
	{
		return StringUtils.isNotNullNorEmpty(getMaxlength());
	}

	// TAG CONTENT

	@Override
	protected int writeTagContent(TagWriter tagWriter)
	throws JspException
	{
		super.writeTagContent(tagWriter);

		if (hasMaxLength())
		{
			writeMaxLengthScript(tagWriter);
		}

		return EVAL_PAGE;
	}

	/**
     * @param tagWriter
     */
    private void writeMaxLengthScript(TagWriter writer)
	throws JspException
    {
		String elementId = createPath();
		writer.startTag("script");
		writeOptionalAttribute(writer, "type", "text/javascript");
		writer.appendValue("var element = ");
		writer.appendValue(asElement(elementId));
		writer.appendValue(";");
		writer.appendValue("element.onchange=");
		writeLengthLimitScript(writer);

		writer.appendValue("element.onkeyup=");
		writeLengthLimitScript(writer);
		writer.endTag();
    }

	private void writeLengthLimitScript(TagWriter writer)
	throws JspException
	{
		writer.appendValue("function() { limitLength(");
		writer.appendValue("element, ");
		writer.appendValue(getMaxlength());
		writer.appendValue("); };");
	}

	private String asElement(String elementId)
	{
		StringBuilder sb = new StringBuilder();
		sb.append("$('");
		sb.append(elementId);
		sb.append("')");
		return sb.toString();
	}

	private String createPath()
	throws JspException
	{
		String path = getPath();
		
		if (getNestedPath() != null)
			path = getNestedPath() + getPath();
		
		return path;
	}
}
