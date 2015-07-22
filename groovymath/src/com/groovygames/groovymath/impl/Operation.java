package com.groovygames.groovymath.impl;

import com.groovygames.groovymath.IMatrix;
import com.groovygames.groovymath.IOperation;

public class Operation implements IOperation
{
	private IMatrix matrix;

	private int x1;

	private int y1;

	private int x2;

	private int y2;

	private int x3;

	private int y3;

	public Operation()
	{
		super();
	}

	public Operation(final IMatrix matrix, final int x1, final int y1, final int x2, final int y2, final int x3, final int y3)
	{
		super();
		this.matrix = matrix;
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
		this.x3 = x3;
		this.y3 = y3;
	}

	public IMatrix getMatrix()
	{
		return matrix;
	}

	public void setMatrix(final IMatrix matrix)
	{
		this.matrix = matrix;
	}

	@Override
	public int getX1()
	{
		return x1;
	}

	public void setX1(final int x1)
	{
		this.x1 = x1;
	}

	@Override
	public int getY1()
	{
		return y1;
	}

	public void setY1(final int y1)
	{
		this.y1 = y1;
	}

	@Override
	public int getX2()
	{
		return x2;
	}

	public void setX2(final int x2)
	{
		this.x2 = x2;
	}

	@Override
	public int getY2()
	{
		return y2;
	}

	public void setY2(final int y2)
	{
		this.y2 = y2;
	}

	@Override
	public int getX3()
	{
		return x3;
	}

	public void setX3(final int x3)
	{
		this.x3 = x3;
	}

	@Override
	public int getY3()
	{
		return y3;
	}

	public void setY3(final int y3)
	{
		this.y3 = y3;
	}

	@Override
	public int getFirst()
	{
		return matrix.get(x1, y1);
	}

	@Override
	public int getSecond()
	{
		return matrix.get(x2, y2);
	}

	@Override
	public int getResult()
	{
		return matrix.get(x3, y3);
	}

	@Override
	public boolean equals(final Object obj)
	{
		if (this.getClass().isInstance(obj))
		{
			final Operation target = (Operation) obj;

			if (this.isSameValue(target) && this.isSamePosition(target))
			{
				return true;
			}
		}

		return false;
	}

	@Override
	public boolean isSameValue(final IOperation target)
	{
		return (this.getFirst() == target.getFirst() && this.getSecond() == target.getSecond() && this.getResult() == target.getResult())
				|| (this.getFirst() == target.getSecond() && this.getSecond() == target.getFirst() && this.getResult() == target.getResult());
	}

	@Override
	public  boolean isSamePosition(final IOperation target)
	{
		return this
				.isSamePosition(this.x1, this.y1, this.x2, this.y2, this.x3, this.y3, target.getX1(), target.getY1(), target.getX2(), target.getY2(), target.getX3(), target.getY3())
				|| this.isSamePosition(this.x1, this.y1, this.x2, this.y2, this.x3, this.y3, target.getX2(), target.getY2(), target.getX1(), target.getY1(), target.getX3(),
						target.getY3());
	}

	public  boolean isSamePosition(final int ax1, final int ay1, final int ax2, final int ay2, final int ax3, final int ay3, final int bx1, final int by1,
			final int bx2, final int by2, final int bx3, final int by3)
	{
		return ax1 == bx1 && ay1 == by1 && ax2 == bx2 && ay2 == by2 && ax3 == bx3 && ay3 == by3;
	}

	@Override
	public int getScore()
	{
		return 0;
	}
}
