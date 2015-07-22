package com.groovygames.groovymath;

public interface IMatrix
{
	static final public int EMPTY = -1;
	static final public int BLOCKED = -2;

	public abstract int get(int x, int y);

	public abstract void set(int x, int y, int value);

	public abstract int getWidth();

	public abstract int getHeight();
}