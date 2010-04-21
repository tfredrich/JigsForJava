/*
 * Copyright 2008, eCollege, Inc.  All rights reserved.
 */
package com.jigsforjava.string;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.jigsforjava.string.MapMessageFormat;

/**
 * @author toddf
 * @since Nov 24, 2008
 */
public class MapMessageFormatTest
{
	@Test
	public void testFormatWithDefaultDelimiters()
	{
		MapMessageFormat formatter = new MapMessageFormat();
		String template = "{last_name}, {first_name} {middle_initial}.";
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("first_name", "Todd");
		parameters.put("middle_initial", "A");
		parameters.put("last_name", "Fredrich");
		String result = formatter.format(template, parameters);
		assertEquals("Fredrich, Todd A.", result);
	}

	@Test
	public void testFormatWithSpecifiedDelimiters()
	{
		MapMessageFormat formatter = new MapMessageFormat("[", "]");
		String template = "[last_name], [first_name] [middle_initial].";
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("first_name", "Todd");
		parameters.put("middle_initial", "A");
		parameters.put("last_name", "Fredrich");
		String result = formatter.format(template, parameters);
		assertEquals("Fredrich, Todd A.", result);
	}

	@Test
	public void testFormatWithStringDelimiters()
	{
		MapMessageFormat formatter = new MapMessageFormat("<start>", "<end>");
		String template = "<start>last_name<end>, <start>first_name<end> <start>middle_initial<end>.";
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("first_name", "Todd");
		parameters.put("middle_initial", "A");
		parameters.put("last_name", "Fredrich");
		String result = formatter.format(template, parameters);
		assertEquals("Fredrich, Todd A.", result);
	}
}
