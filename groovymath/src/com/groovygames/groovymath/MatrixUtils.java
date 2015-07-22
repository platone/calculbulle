package com.groovygames.groovymath;

import java.util.LinkedList;
import java.util.List;

import com.groovygames.groovymath.impl.Operation;
import com.groovygames.groovymath.impl.action.BringDownAction;
import com.groovygames.groovymath.impl.action.FindOperationAction;

public class MatrixUtils
{
	public static IOperation selectRandomOperation(final IMatrix matrix)
	{
		final IOperation[] operations = MatrixUtils.selectOperations(matrix);

		if (null != operations && operations.length > 0)
		{
			return operations[(int) (Math.random() * (double) operations.length)];
		}

		return null;
	}

	public static IOperation[] selectOperations(final IMatrix matrix)
	{
		final List<IOperation> operations = new LinkedList<IOperation>();

		MatrixNavigator.navigate(matrix, new FindOperationAction(operations));

		return operations.toArray(new IOperation[operations.size()]);
	}

	public static void removeOperation(final IOperation operation, final IMatrix matrix)
	{
		matrix.set(operation.getX1(), operation.getY1(), IMatrix.EMPTY);
		matrix.set(operation.getX2(), operation.getY2(), IMatrix.EMPTY);
		matrix.set(operation.getX3(), operation.getY3(), IMatrix.EMPTY);
	}

	public static void bringDown(final IMatrix matrix)
	{
		MatrixNavigator.navigate(matrix, new BringDownAction());
	}

	public static void fillIn(final IMatrix matrix, final IFillInAction action)
	{
		MatrixNavigator.navigate(matrix, action);
	}

	public static IOperation findOperation(IMatrix matrix, int column, int raw, int column2, int raw2, int column3, int raw3)
	{
		IOperation temp = new Operation(matrix, column, raw, column2, raw2, raw2, raw3);

		IOperation[] operations = MatrixUtils.selectOperations(matrix);

		for (IOperation operation : operations)
		{
			if (temp.isSamePosition(operation) && temp.isSameValue(operation))
				return operation;
		}

		return null;
	}

	static public boolean isSamePosition(final int ax1, final int ay1, final int ax2, final int ay2, final int ax3, final int ay3, final int bx1,
			final int by1, final int bx2, final int by2, final int bx3, final int by3)
	{
		return ax1 == bx1 && ay1 == by1 && ax2 == bx2 && ay2 == by2 && ax3 == bx3 && ay3 == by3;
	}
	
	static public IOperation createOperation(final IMatrix matrix, final int x1, final int y1, final int x2,
			final int y2, final int x3, final int y3)
	{
		final float valueA = matrix.get(x1,  y1);
		final float valueB = matrix.get(x2,  y2);
		final float valueC = matrix.get(x3,  y3);
		
		if ((valueA / valueB) == valueC)
			return OperationFactory.createDivide(matrix, x1, y1, x2, y2, x3, y3);

		if ((valueA * valueB) == valueC)
			return OperationFactory.createMulti(matrix, x1, y1, x2, y2, x3, y3);

		if ((valueA - valueB) == valueC)
			return OperationFactory.createMinus(matrix, x1, y1, x2, y2, x3, y3);

		if ((valueA + valueB) == valueC)
			return OperationFactory.createPlus(matrix, x1, y1, x2, y2, x3, y3);
		
		return null;
	}
}
