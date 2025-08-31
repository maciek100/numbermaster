package org.yoshi.numbermaster.numbercheckers;

import org.yoshi.numbermaster.model.FibonacciStatus;

public class FibonacciChecker {

    public FibonacciStatus isFibonacciNumber (long number) {
        boolean isFib = isFibonacci(number);
        return new FibonacciStatus(number, isFib, isFib ? fibonacciIndex(number) : 0);
    }
    /**
     * Checks if the given number is a Fibonacci number.
     *
     * @param n the number to check
     * @return true if n is a Fibonacci number, false otherwise
     */
    public boolean isFibonacci(long n) {
        if (n < 0) {
            return false;
        }
        long x1 = 5L * n * n + 4;
        long x2 = 5L * n * n - 4;
        return isPerfectSquare(x1) || isPerfectSquare(x2);
    }

    /**
     * Finds the index (0-based) of a Fibonacci number.
     * Returns -1 if the number is not in the sequence.
     *
     * Example: fibonacciIndex(0) = 0, fibonacciIndex(1) = 1, fibonacciIndex(2) = 3
     */
    public int fibonacciIndex(long n) {
        if (!isFibonacci(n)) {
            return -1;
        }
        long a = 0;
        long b = 1;
        int index = 0; // start with F(0) = 0
        while (a < n) {
            long temp = a + b;
            a = b;
            b = temp;
            index++;
        }
        return index; // when a == n
    }

    /**
     * Helper method to check if a number is a perfect square.
     */
    private boolean isPerfectSquare(long x) {
        long sqrt = (long) Math.sqrt(x);
        return sqrt * sqrt == x;
    }
}
