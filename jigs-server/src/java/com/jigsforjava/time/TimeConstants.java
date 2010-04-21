/*
 * Copyright 2008, eCollege, Inc.  All rights reserved.
 */
package com.jigsforjava.time;

/**
 * Non-instantiable class of constants used in date and time computations.
 * 
 * @author toddf
 * @since Nov 25, 2008
 */
public final class TimeConstants
{
	// SECTION: CONSTANTS
	
	/**
	 * The number of days in a week (7).
	 */
	public static final int DAYS_PER_WEEK = 7;
	
	/**
	 * The number of seconds in a single minute (60).
	 */
	public static final int SECONDS_PER_MINUTE = 60;
	
	/**
	 * The number of seconds in a single hour.
	 */
	public static final int SECONDS_PER_HOUR = 3600;
	
	/**
	 * The number of seconds in a single 24-hour day.
	 */
	public static final int SECONDS_PER_DAY = 86400;
	
	/**
	 * The number of hours in a single day (24).
	 */
	public static final int HOURS_PER_DAY = 24;
	
	/**
	 * The number of hours in a week (of 7 days).
	 */
	public static final int HOURS_PER_WEEK = HOURS_PER_DAY * DAYS_PER_WEEK;
	
	/**
	 * The number of minutes in a single hour (60).
	 */
	public static final int MINUTES_PER_HOUR = 60;

	/**
	 * The number of minutes in a single, 24-hour day.
	 */
	public static final int MINUTES_PER_DAY = MINUTES_PER_HOUR * HOURS_PER_DAY;

	/**
	 * The number of milliseconds in a second (1000).
	 */
	public static final long MILLIS_PER_SECOND = 1000L;
	
	/**
	 * The number of milliseconds in a minute.
	 */
	public static final long MILLIS_PER_MINUTE = MILLIS_PER_SECOND * SECONDS_PER_MINUTE;
	
	/**
	 * The number of milliseconds in an hour.
	 */
	public static final long MILLIS_PER_HOUR = MILLIS_PER_MINUTE * MINUTES_PER_HOUR;
	
	/**
	 * The number of milliseconds in a typical day.
	 */
	public static final long MILLIS_PER_DAY = MILLIS_PER_HOUR * HOURS_PER_DAY;

	
	// SECTION: CONSTRUCTOR
	
	private TimeConstants()
	{
		// enforce non-instantiability.
	}
}
