package com.groovygames.groovymath;

import com.groovygames.groovymath.impl.operation.DivideOperation;
import com.groovygames.groovymath.impl.operation.MinusOperation;
import com.groovygames.groovymath.impl.operation.MultiOperation;
import com.groovygames.groovymath.impl.operation.PlusOperation;

public class OperationFactory
{

	public static IOperation createPlus(final IMatrix matrix, final int x1, final int y1, final int x2, final int y2, final int x3, final int y3)
	{
		return new PlusOperation(matrix, x1, y1, x2, y2, x3, y3);
	}

	public static IOperation createMinus(final IMatrix matrix, final int x1, final int y1, final int x2, final int y2, final int x3, final int y3)
	{
		return new MinusOperation(matrix, x1, y1, x2, y2, x3, y3);
	}

	public static IOperation createMulti(final IMatrix matrix, final int x1, final int y1, final int x2, final int y2, final int x3, final int y3)
	{
		return new MultiOperation(matrix, x1, y1, x2, y2, x3, y3);
	}

	public static IOperation createDivide(final IMatrix matrix, final int x1, final int y1, final int x2, final int y2, final int x3, final int y3)
	{
		return new DivideOperation(matrix, x1, y1, x2, y2, x3, y3);
	}

}
