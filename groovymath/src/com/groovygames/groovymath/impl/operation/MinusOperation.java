package com.groovygames.groovymath.impl.operation;

import com.groovygames.groovymath.IMatrix;
import com.groovygames.groovymath.impl.Operation;

public class MinusOperation extends Operation
{
	public MinusOperation(final IMatrix matrix, final int x1, final int y1, final int x2, final int y2, final int x3, final int y3)
	{
		super(matrix, x1, y1, x2, y2, x3, y3);
	}

	@Override
	public String toString()
	{
		return "-";
	}
	
	@Override
	public int getScore()
	{
		return getFirst() + getSecond() * 2;
	}
}
