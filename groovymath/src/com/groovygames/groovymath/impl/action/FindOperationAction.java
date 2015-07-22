package com.groovygames.groovymath.impl.action;

import java.util.List;

import com.groovygames.groovymath.IAction;
import com.groovygames.groovymath.IMatrix;
import com.groovygames.groovymath.IOperation;
import com.groovygames.groovymath.MatrixNavigator;
import com.groovygames.groovymath.OperationFactory;
import com.groovygames.groovymath.impl.Action;

public class FindOperationAction extends Action
{
	private final List<IOperation> operations;

	public FindOperationAction(final List<IOperation> operations)
	{
		this.operations = operations;
	}

	@Override
	public void execute(final IMatrix matrix, final int x1, final int y1)
	{
		final int valueA = matrix.get(x1, y1);

		MatrixNavigator.navigate(matrix, new IAction()
		{
			@Override
			public void execute(final IMatrix matrix, final int x2, final int y2)
			{
				if (x1 != x2 && y1 != y2)
				{
					final int valueB = matrix.get(x2, y2);

					MatrixNavigator.navigate(matrix, new IAction()
					{
						@Override
						public void execute(final IMatrix matrix, final int x3, final int y3)
						{
							if (x1 != x3 && y1 != y3 && x2 != x3 && y2 != y3)
							{
								final int valueC = matrix.get(x3, y3);

								addOperations(valueA, valueB, valueC, matrix, x1, y1, x2, y2, x3, y3);
							}
						}
					});
				}
			}
		});
	}

	private void addOperations(final int valueA, final int valueB, final int valueC, final IMatrix matrix, final int x1, final int y1, final int x2,
			final int y2, final int x3, final int y3)
	{
		if ((valueA + valueB) == valueC)
		{
			this.addOperationIfNotExists(operations, OperationFactory.createPlus(matrix, x1, y1, x2, y2, x3, y3));
		}

		if ((valueA + valueB) == valueC)
		{
			this.addOperationIfNotExists(operations, OperationFactory.createMinus(matrix, x1, y1, x2, y2, x3, y3));
		}

		if ((valueA + valueB) == valueC)
		{
			this.addOperationIfNotExists(operations, OperationFactory.createMulti(matrix, x1, y1, x2, y2, x3, y3));
		}

		if ((valueA + valueB) == valueC)
		{
			this.addOperationIfNotExists(operations, OperationFactory.createDivide(matrix, x1, y1, x2, y2, x3, y3));
		}
	}

	private void addOperationIfNotExists(final List<IOperation> operations, final IOperation operation)
	{
		if (!operations.contains(operation))
		{
			operations.add(operation);
		}
	}
}
