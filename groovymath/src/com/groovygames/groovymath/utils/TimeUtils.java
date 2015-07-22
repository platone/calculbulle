package com.groovygames.groovymath.utils;

public class TimeUtils
{
	static private final TimeUtils instance = new TimeUtils();

	private long newTime;
	private long oldTime;

	private TimeUtils()
	{

	}

	static public TimeUtils getInstance()
	{
		return TimeUtils.instance;
	}

	public long _getPastTime()
	{
		newTime = System.currentTimeMillis();

		if (oldTime == 0)
		{
			oldTime = newTime;
		}

		final long diff = newTime - oldTime;

		oldTime = newTime;

		return diff;
	}

	public void _printPastTime(final Object tag)
	{
		final long pastTime = this._getPastTime();

		System.out.println(" >> " + tag + " >> " + pastTime + " ms");
	}

	static public void printPastTime(final Object tag)
	{
		TimeUtils.getInstance()._printPastTime(tag);
	}

	static public void printPastTime(final Object target, final Object tag)
	{
		TimeUtils.getInstance()._printPastTime(target.getClass().getSimpleName() + "::" + tag);
	}
}
