package com.groovygames.groovymath.impl.action;

import com.groovygames.groovymath.IAction;
import com.groovygames.groovymath.IMatrix;

public class EmptyAction implements IAction
{
	public void execute(final IMatrix matrix, final int x, final int y)
	{
		matrix.set(x, y, IMatrix.EMPTY);
	}
}