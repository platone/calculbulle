package com.groovygames.groovymath;

import com.groovygames.groovymath.impl.action.RandomValueAction;

public class MatrixBuilder
{
	public static void build(final IMatrix matrix)
	{
		MatrixBuilder.build(matrix, new RandomValueAction(1, Math.max(matrix.getWidth(), matrix.getHeight())));
	}

	public static void build(final IMatrix matrix, final int min, final int max)
	{
		MatrixBuilder.build(matrix, new RandomValueAction(min, max));
	}

	public static void build(final IMatrix matrix, final IFillInAction action)
	{
		MatrixNavigator.navigate(matrix, action);
	}
}
