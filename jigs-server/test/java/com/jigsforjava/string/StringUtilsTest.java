/*
	Copyright 2004-2008 Strategic Gains, Inc.
	
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
package com.jigsforjava.string;

import com.jigsforjava.string.StringUtils;

import junit.framework.TestCase;

/**
 * @author Todd Fredrich
 * @since  Apr 28, 2004
 */
public class StringUtilsTest
extends TestCase
{
	public void testAreEqual()
	{
		String value = "Todd Fredrich";
		
		assertTrue(StringUtils.areEqual(value, "Todd Fredrich"));
		assertFalse(StringUtils.areEqual(value, "todd fredrich"));
		assertFalse(StringUtils.areEqual(value, "Todd"));
		assertFalse(StringUtils.areEqual(value, "Fredrich"));
		assertFalse(StringUtils.areEqual(value, null));
		assertFalse(StringUtils.areEqual(null, value));
	}

	public void testAreEqualIgnoringCase()
	{
		String value = "Todd Fredrich";
		
		assertTrue(StringUtils.areEqualIgnoringCase(value, "Todd Fredrich"));
		assertTrue(StringUtils.areEqualIgnoringCase(value, "todd fredrich"));
		assertFalse(StringUtils.areEqualIgnoringCase(value, "Todd"));
		assertFalse(StringUtils.areEqualIgnoringCase(value, "Fredrich"));
		assertFalse(StringUtils.areEqualIgnoringCase(value, null));
		assertFalse(StringUtils.areEqualIgnoringCase(null, value));
	}
	
	public void testHtmlEncode()
	{
		String toEncode = "\"bread\" & \"butter\"";
		String expected = "&quot;bread&quot; &amp; &quot;butter&quot;";
		assertEquals(expected, StringUtils.htmlEncode(toEncode));
	}

	public void testPluralization()
	{
		assertEquals("keys", StringUtils.pluralize("key"));
		assertEquals("words", StringUtils.pluralize("word"));
		assertEquals("properties", StringUtils.pluralize("property"));
		assertEquals("buses", StringUtils.pluralize("bus"));
		assertEquals("crosses", StringUtils.pluralize("cross"));
		assertEquals("lackeys", StringUtils.pluralize("lackey"));
		assertEquals("nouns", StringUtils.pluralize("noun"));
		assertEquals("knives", StringUtils.pluralize("knife"));
		assertEquals("children", StringUtils.pluralize("child"));
		assertEquals("people", StringUtils.pluralize("person"));
		assertEquals("feet", StringUtils.pluralize("foot"));
		assertEquals("women", StringUtils.pluralize("woman"));
		assertEquals("series", StringUtils.pluralize("series"));
		assertEquals("toys", StringUtils.pluralize("toy"));
		assertEquals("cookies", StringUtils.pluralize("cookie"));
		assertEquals("wolves", StringUtils.pluralize("wolf"));
		assertEquals("teeth", StringUtils.pluralize("tooth"));
		assertEquals("men", StringUtils.pluralize("man"));
		assertEquals("series", StringUtils.pluralize("series"));
		assertEquals("analyses", StringUtils.pluralize("analysis"));
		assertEquals("mice", StringUtils.pluralize("mouse"));
		assertEquals("titmice", StringUtils.pluralize("titmouse"));
		assertEquals("moose", StringUtils.pluralize("moose"));
		assertEquals("oxen", StringUtils.pluralize("ox"));
		assertEquals("foxes", StringUtils.pluralize("fox"));
		assertEquals("geese", StringUtils.pluralize("goose"));
	}
	
	public void testSingularize()
	{
		assertEquals("key", StringUtils.singularize("keys"));
		assertEquals("word", StringUtils.singularize("words"));
		assertEquals("property", StringUtils.singularize("properties"));
		assertEquals("bus", StringUtils.singularize("buses"));
		assertEquals("cross", StringUtils.singularize("crosses"));
		assertEquals("lackey", StringUtils.singularize("lackeys"));
		assertEquals("noun", StringUtils.singularize("nouns"));
		assertEquals("knife", StringUtils.singularize("knives"));
		assertEquals("child", StringUtils.singularize("children"));
		assertEquals("person", StringUtils.singularize("people"));
		assertEquals("foot", StringUtils.singularize("feet"));
		assertEquals("woman", StringUtils.singularize("women"));
		assertEquals("series", StringUtils.singularize("series"));
		assertEquals("toy", StringUtils.singularize("toys"));
		assertEquals("wolf", StringUtils.singularize("wolves"));
		assertEquals("tooth", StringUtils.singularize("teeth"));
		assertEquals("man", StringUtils.singularize("men"));
		assertEquals("analysis", StringUtils.singularize("analyses"));
		assertEquals("cookie", StringUtils.singularize("cookies"));
		assertEquals("ox", StringUtils.singularize("oxen"));
		assertEquals("mouse", StringUtils.singularize("mice"));
		assertEquals("titmouse", StringUtils.singularize("titmice"));
		assertEquals("goose", StringUtils.singularize("geese"));
		assertEquals("moose", StringUtils.singularize("moose"));
		assertEquals("fox", StringUtils.singularize("foxes"));
	}
	
	public void testToAccessorName()
	{
		assertEquals("getProperty", StringUtils.toAccessorName("property"));
		assertEquals("getAnotherProperty", StringUtils.toAccessorName("anotherProperty"));
	}
	
	public void testToMutatorName()
	{
		assertEquals("setProperty", StringUtils.toMutatorName("property"));
		assertEquals("setAnotherProperty", StringUtils.toMutatorName("anotherProperty"));
	}
}
