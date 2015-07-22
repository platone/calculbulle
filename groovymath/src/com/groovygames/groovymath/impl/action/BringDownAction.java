package com.groovygames.groovymath.impl.action;

import com.groovygames.groovymath.IAction;
import com.groovygames.groovymath.IMatrix;

public class BringDownAction implements IAction
{
	@Override
	public void execute(final IMatrix matrix, final int x, final int y)
	{
		if (matrix.get(x, y) == IMatrix.EMPTY)
		{
			if (y < matrix.getHeight())
			{
				this.bringDown(matrix, x, y);
			}
		}
	}

	private void bringDown(final IMatrix matrix, final int x, final int y)
	{
		int limit = matrix.getHeight();

		while (matrix.get(x, y) == IMatrix.EMPTY && limit > 0 && y + 1 < matrix.getHeight())
		{
			matrix.set(x, y, matrix.get(x, y + 1));
			matrix.set(x, y + 1, IMatrix.EMPTY);

			this.bringDown(matrix, x, y + 1);

			limit--;
		}
	}
}
