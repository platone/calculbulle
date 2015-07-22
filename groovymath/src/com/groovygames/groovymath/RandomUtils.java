package com.groovygames.groovymath;

public class RandomUtils
{
	static public double random(final double min, final double max)
	{
		final double random = Math.random();
		
		return random * (max - min) + min;
	}
}
