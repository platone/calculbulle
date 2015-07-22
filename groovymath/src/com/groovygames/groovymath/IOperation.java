package com.groovygames.groovymath;

public interface IOperation
{
	public abstract int getResult();

	public abstract int getSecond();

	public abstract int getFirst();

	public abstract int getY3();

	public abstract int getX3();

	public abstract int getY2();

	public abstract int getX2();

	public abstract int getY1();

	public abstract int getX1();

	public abstract boolean isSamePosition(final IOperation target);

	public abstract boolean isSameValue(final IOperation target);

	public abstract int getScore();
}
