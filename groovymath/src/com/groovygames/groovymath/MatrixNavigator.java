package com.groovygames.groovymath;

public class MatrixNavigator
{
	static public void navigate(final IMatrix matrix, final IAction action)
	{
		for (int x = 0; x < matrix.getWidth(); x++)
		{
			for (int y = 0; y < matrix.getHeight(); y++)
			{
				action.execute(matrix, x, y);
			}
		}
	}
}
