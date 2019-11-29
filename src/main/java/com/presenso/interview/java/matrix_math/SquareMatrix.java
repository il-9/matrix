package com.presenso.interview.java.matrix_math;

import java.util.Arrays;
import java.util.Random;

public final class SquareMatrix {

    private final float[][] entries;

    public SquareMatrix(float[][] entries) {
        this.entries = entries;
    }

    public SquareMatrix(int order) {
        entries = new float[order][order];
        fillRandomly(entries);
    }

    private static void fillRandomly(float[][] entries) {
        Random random = new Random();
        for (int i = 0; i < entries.length; i++) {
            for (int j = 0; j < entries.length; j++) {
                entries[i][j] = random.nextFloat();
            }
        }
    }

    public SquareMatrix multiply(SquareMatrix that) {
        if (that.entries.length != this.entries.length) {
            throw new IllegalArgumentException("Unable to multiply matrices as orders differ");
        }

        int order = this.entries.length;
        float[][] result = new float[order][order];
        for (int i = 0; i < order; i++) {
            for (int j = 0; j < order; j++) {
                for (int k = 0; k < order; k++) {
                    result[i][j] += this.entries[i][k] * that.entries[k][j];
                }
            }
        }

        return new SquareMatrix(result);
    }

    public SquareMatrix multiplyParallel(SquareMatrix that) {
        if (that.entries.length != this.entries.length) {
            throw new IllegalArgumentException("Unable to multiply matrices as orders differ");
        }

        int order = this.entries.length;
        float[][] result = new float[order][order];
        Thread[] threads = new Thread[order];
        for (int i = 0; i < order; i++) {
            CalculatedRow calculatedRow = new CalculatedRow(this.entries[i], that.entries);
            threads[i] = new Thread(calculatedRow);
            threads[i].start();
            try {
                threads[i].join();
                result[i] = calculatedRow.getResult();
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }

        return new SquareMatrix(result);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }
        SquareMatrix that = (SquareMatrix) obj;
        return Arrays.deepEquals(this.entries, that.entries);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < entries.length; i++) {
            for (int j = 0; j < entries.length; j++) {
                sb.append(entries[i][j]).append('\t');
            }
            sb.append('\n');
        }
        return sb.toString();
    }

    private static class CalculatedRow implements Runnable {

        private final float[] row;
        private final float[][] matrix;
        private final float[] result;

        private CalculatedRow(float[] row, float[][] matrix) {
            this.row = row;
            this.matrix = matrix;
            this.result = new float[row.length];
        }

        @Override
        public void run() {
            int order = this.row.length;
            for (int i = 0; i < order; i++) {
                for (int k = 0; k < order; k++) {
                    result[i] += row[k] * matrix[k][i];
                }
            }
        }

        private float[] getResult() {
            return result;
        }
    }
}
