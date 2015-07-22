package com.groovygames.groovymath.impl;

import java.util.Arrays;

import com.groovygames.groovymath.IMatrix;
import com.groovygames.groovymath.MatrixNavigator;
import com.groovygames.groovymath.impl.action.EmptyAction;

public class Matrix implements IMatrix
{
	private int width;

	private int height;

	private int[][] datas;

	public Matrix(final int width, final int height)
	{
		super();

		this.init(width, height);
	}

	private void init(final int width, final int height)
	{
		this.width = width;
		this.height = height;

		datas = new int[width][height];

		MatrixNavigator.navigate(this, new EmptyAction());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.groovygames.groovymath.IMatrix#get(int, int)
	 */
	@Override
	public int get(final int x, final int y)
	{
		return datas[x][y];
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.groovygames.groovymath.IMatrix#getWidth()
	 */
	@Override
	public int getWidth()
	{
		return width;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.groovygames.groovymath.IMatrix#getHeight()
	 */
	@Override
	public int getHeight()
	{
		return height;
	}

	@Override
	public void set(final int x, final int y, final int value)
	{
		datas[x][y] = value;
	}

	@Override
	public String toString()
	{
		final StringBuilder builder = new StringBuilder();

		builder.append("Matrix [width=");
		builder.append(width);
		builder.append(", height=");
		builder.append(height);
		builder.append(", datas=[");
		for (final int[] subdatas : datas)
		{
			builder.append("\r\n");
			builder.append(Arrays.toString(subdatas));
			builder.append("");
		}
		builder.append("]");

		return builder.toString();
	}
}
