package com.jigsforjava.string;

import com.jigsforjava.string.ParameterizedString;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 *  Unit Test for the ParameterizedString object.
 *
 * @author  Todd Fredrich
 * @created  October 29, 2001
 * @version  $Revision: 1.1 $
 */
public class ParameterizedStringTest
	extends TestCase
{
	/**
	 *  Constructor for the ParameterizedStringTest object
	 *
	 * @param  testMethodName Description of Parameter
	 */
	public ParameterizedStringTest(String testMethodName)
	{
		super(testMethodName);
	}


	/**
	 *  A unit test for JUnit
	 */
	public void testSettersAndGetters()
	{
		String string = "y = ?";
		ParameterizedString ps = new ParameterizedString(string);
		assertEquals(1, ps.getParameterCount());
		ps.setString(1, "hello");
		assertTrue(ps.toString().equals("y = hello"));
		assertTrue(ps.getString(1).equals("hello"));
		ps.setInt(1, 42);
		assertTrue(ps.toString().equals("y = 42"));
		assertEquals(42, ps.getInt(1));
	}


	/**
	 *  A unit test for JUnit
	 */
	public void testDefaultDelimiter()
	{
		String string = "one ? two ? three ? = ? four";
		ParameterizedString ps = new ParameterizedString(string);
		assertEquals(4, ps.getParameterCount());
		ps.setInt(1, 1);
		ps.setInt(2, 2);
		ps.setInt(3, 3);
		ps.setInt(4, 4);
		assertTrue(ps.toString().equals("one 1 two 2 three 3 = 4 four"));
	}


	/**
	 *  A unit test for JUnit
	 */
	public void testAlternateDelimiter()
	{
		String string = "one * two * three * = * four";
		ParameterizedString ps = new ParameterizedString(string, "*");
		assertEquals(4, ps.getParameterCount());
	}


	/**
	 *  A unit test suite for JUnit. Adds all the methods beginning with
	 *  'test...' to the test suite.
	 *
	 * @return  The newly created test suite
	 */
	public static Test suite()
	{
		return new TestSuite(ParameterizedStringTest.class);
	}


	/**
	 *  The main method if this unit test gets run individually from the
	 *  command line.
	 *
	 * @param  args Command line arguments. None required or used.
	 */
	public static void main(String args[])
	{
		System.out.println("Testing: ParameterizedString");
		junit.textui.TestRunner.run(ParameterizedStringTest.class);
	}
}

