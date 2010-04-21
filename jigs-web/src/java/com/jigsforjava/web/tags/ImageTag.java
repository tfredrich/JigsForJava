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

import org.springframework.web.servlet.tags.form.TagWriter;

/**
 * @author toddf
 * @since Aug 18, 2008
 */
public class ImageTag
extends HtmlTag
{
	// SECTION: CONSTANTS
	
	private static final String SRC_ATTRIBUTE = "src";
	private static final String ALT_ATTRIBUTE = "alt";
	
	
	// SECTION: CONSTRUCTORS
	
	public ImageTag()
	{
		super("img");
	}


	// SECTION: TAG GENERATION

    @Override
    protected int writeTagContents(TagWriter tagWriter)
    throws Exception
    {
    	tagWriter.writeAttribute(SRC_ATTRIBUTE, getRequestContext().getContextPath() + getSrc());
    	tagWriter.writeOptionalAttributeValue(ALT_ATTRIBUTE, getAlt());
	    return SKIP_BODY;
    }
    

    // SECTION: ACCESSORS/MUTATORS

    public void setAlt(String value)
    {
    	setValue(ALT_ATTRIBUTE, value);
    }
    
    public String getAlt()
    {
    	return (String) getValue(ALT_ATTRIBUTE);
    }
    
    public void setSrc(String value)
    {
    	setValue(SRC_ATTRIBUTE, value);
    }
    
    public String getSrc()
    {
    	return (String) getValue(SRC_ATTRIBUTE);
    }
}
