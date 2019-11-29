package com.presenso.interview.java.matrix_math;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SquareMatrixTest {

    @Test
    public void test1x1matrixSequential() {
        SquareMatrix a = new SquareMatrix(new float[][]{{4F}});
        SquareMatrix b = new SquareMatrix(new float[][]{{2F}});

        SquareMatrix c = a.multiply(b);

        assertEquals(c, new SquareMatrix(new float[][]{{8F}}));
    }

    @Test
    public void test1x1matrixParallel() {
        SquareMatrix a = new SquareMatrix(new float[][]{{3F}});
        SquareMatrix b = new SquareMatrix(new float[][]{{3F}});

        SquareMatrix c = a.multiplyParallel(b);

        assertEquals(c, new SquareMatrix(new float[][]{{9F}}));
    }

    @Test
    public void test2x2matrixSequential() {
        SquareMatrix a = new SquareMatrix(new float[][]{{1F, 2F}, {3F, 4F}});
        SquareMatrix b = new SquareMatrix(new float[][]{{1F, 1F}, {2F, 0F}});

        SquareMatrix c = a.multiply(b);

        assertEquals(c, new SquareMatrix(new float[][]{{5F, 1F}, {11F, 3F}}));
    }

    @Test
    public void test2x2matrixParallel() {
        SquareMatrix a = new SquareMatrix(new float[][]{{1F, 2F}, {3F, 4F}});
        SquareMatrix b = new SquareMatrix(new float[][]{{1F, 1F}, {2F, 1F}});

        SquareMatrix c = a.multiplyParallel(b);

        assertEquals(c, new SquareMatrix(new float[][]{{5F, 3F}, {11F, 7F}}));
    }

    @Test
    public void testSequentialAndParallelAlgorithmsProduceTheSameResult() {
        SquareMatrix a = new SquareMatrix(100);
        SquareMatrix b = new SquareMatrix(100);

        SquareMatrix c1 = a.multiply(b);
        SquareMatrix c2 = a.multiplyParallel(b);

        assertEquals(c1, c2);
    }
}
