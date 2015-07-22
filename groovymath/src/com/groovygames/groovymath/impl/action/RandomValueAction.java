package com.groovygames.groovymath.impl.action;

import com.groovygames.groovymath.IMatrix;
import com.groovygames.groovymath.impl.FillInAction;

public class RandomValueAction extends FillInAction
{
	private final int min;

	private final int max;

	public RandomValueAction(final int min, final int max)
	{
		super();

		this.min = min;
		this.max = max;
	}

	@Override
	public void execute(final IMatrix matrix, final int x, final int y)
	{
		if (matrix.get(x, y) == IMatrix.EMPTY)
		{
			final double random = Math.random();
			final double value = random * (double) (max + 1);

			matrix.set(x, y, (value > min) ? (int) value : min);
		}
	}
}
