package com.groovygames.groovymath;

import com.groovygames.groovymath.impl.Matrix;

public class MatrixFactory
{
	public static IMatrix createMatrix(final int x, final int y)
	{
		final IMatrix matrix = new Matrix(x, y);

		MatrixBuilder.build(matrix);

		return matrix;
	}

	public static IMatrix createMatrix(final int x, final int y, final IFillInAction action)
	{
		final IMatrix matrix = new Matrix(x, y);

		MatrixBuilder.build(matrix, action);

		return matrix;
	}
}
