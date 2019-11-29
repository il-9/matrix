package com.presenso.interview.java.matrix_math;

public class App {
    public static void main(String[] args) {
        int order = Integer.parseInt(args[0]);
        SquareMatrix left = new SquareMatrix(order);
        SquareMatrix right = new SquareMatrix(order);

        long before = System.nanoTime();
        left.multiply(right);
        long after = System.nanoTime();
        System.out.println("Execution time (sequential computing), nanoseconds: " + (after - before));

        before = System.nanoTime();
        left.multiplyParallel(right);
        after = System.nanoTime();
        System.out.println("Execution time (parallel computing), nanoseconds:   " + (after - before));
    }
}

