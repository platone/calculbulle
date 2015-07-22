package com.groovygames.groovymath.unit;

import junit.framework.Assert;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.groovygames.groovymath.IAction;
import com.groovygames.groovymath.IMatrix;
import com.groovygames.groovymath.IOperation;
import com.groovygames.groovymath.MatrixFactory;
import com.groovygames.groovymath.MatrixNavigator;
import com.groovygames.groovymath.MatrixUtils;
import com.groovygames.groovymath.MatrixValidator;
import com.groovygames.groovymath.impl.Matrix;
import com.groovygames.groovymath.impl.action.RandomValueAction;
import com.groovygames.groovymath.impl.operation.PlusOperation;

public class MatrixUnit
{
    private static final int MIN_X = 6;
    private static final int MIN_Y = 6;

    private static final int MAX_X = 10;
    private static final int MAX_Y = 10;

    private static final int MIN_VALUE = 1;
    private static final int MAX_VALUE = 64;

    private static final int NUMBER_OF_BRING = 8;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception
    {}

    @AfterClass
    public static void tearDownAfterClass() throws Exception
    {}

    @Before
    public void setUp() throws Exception
    {}

    @After
    public void tearDown() throws Exception
    {}

    @Test
    public void testSquareMatrix()
    {
        for (int x = MIN_X; x < MAX_X; x++)
        {
            for (int y = MIN_Y; y < MAX_Y; y++)
            {
                RandomValueAction action = new RandomValueAction(MIN_VALUE, MAX_VALUE);

                IMatrix matrix = MatrixFactory.createMatrix(x, y, action);

                Assert.assertNotNull(matrix);

                Assert.assertTrue(MatrixValidator.validate(matrix));

                IOperation operation = MatrixUtils.selectRandomOperation(matrix);

                Assert.assertNotNull(operation);

                MatrixUtils.removeOperation(operation, matrix);

                MatrixUtils.fillIn(matrix, action);

                Assert.assertTrue(MatrixValidator.validate(matrix));
            }
        }
    }

    @Test
    public void testBringDownMatrix()
    {
        Matrix matrix = new Matrix(10, 10);

        MatrixNavigator.navigate(matrix, new IAction()
        {
            private int i = 0;

            @Override
            public void execute(IMatrix matrix, int x, int y)
            {
                matrix.set(x, y, i++);
            }
        });

        Assert.assertTrue(MatrixValidator.validate(matrix));

        IOperation operation = new PlusOperation(matrix, 1, 1, 2, 2, 3, 3);

        int a1 = matrix.get(1, 1);
        int b1 = matrix.get(2, 2);
        int c1 = matrix.get(3, 3);

        int a2 = matrix.get(1, 2);
        int b2 = matrix.get(2, 3);
        int c2 = matrix.get(3, 4);

        MatrixUtils.removeOperation(operation, matrix);

        MatrixUtils.bringDown(matrix);

        int a3 = matrix.get(1, 1);
        int b3 = matrix.get(2, 2);
        int c3 = matrix.get(3, 3);

        Assert.assertTrue(matrix.get(1, matrix.getHeight() - 1) == IMatrix.EMPTY);
        Assert.assertTrue(matrix.get(2, matrix.getHeight() - 1) == IMatrix.EMPTY);
        Assert.assertTrue(matrix.get(3, matrix.getHeight() - 1) == IMatrix.EMPTY);

        Assert.assertTrue(matrix.get(1, 1) != IMatrix.EMPTY);
        Assert.assertTrue(matrix.get(2, 2) != IMatrix.EMPTY);
        Assert.assertTrue(matrix.get(3, 3) != IMatrix.EMPTY);

        Assert.assertTrue(a2 == a3);
        Assert.assertTrue(b2 == b3);
        Assert.assertTrue(c2 == c3);
    }

    @Test
    public void testBringDownWithRandomMatrix()
    {
        RandomValueAction action = new RandomValueAction(MIN_VALUE, MAX_VALUE);

        IMatrix matrix = MatrixFactory.createMatrix(MAX_X, MAX_Y, action);

        for (int i = 0; i < NUMBER_OF_BRING; i++)
        {
            Assert.assertTrue(MatrixValidator.validate(matrix));

            IOperation operation = MatrixUtils.selectRandomOperation(matrix);

            Assert.assertNotNull(operation);

            MatrixUtils.removeOperation(operation, matrix);

            MatrixUtils.bringDown(matrix);

            Assert.assertTrue(MatrixValidator.validate(matrix));

            MatrixUtils.fillIn(matrix, action);
        }
    }
}
