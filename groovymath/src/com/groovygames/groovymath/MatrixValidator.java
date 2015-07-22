package com.groovygames.groovymath;

public class MatrixValidator
{
	public static boolean validate(final IMatrix matrix)
	{
		final IOperation operation = MatrixUtils.selectRandomOperation(matrix);

		return null != operation;
	}
}
