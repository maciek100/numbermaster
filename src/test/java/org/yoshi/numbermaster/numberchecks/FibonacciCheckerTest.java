package org.yoshi.numbermaster.numberchecks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.yoshi.numbermaster.model.FibonacciStatus;
import org.yoshi.numbermaster.numbercheckers.FibonacciChecker;

import static org.junit.jupiter.api.Assertions.*;

class FibonacciCheckerTest {

    private FibonacciChecker checker;

    @BeforeEach
    void setUp() {
        checker = new FibonacciChecker();
    }

    @Test
    void testKnownFibonacciNumbers() {
        long[] fibNumbers = {0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233};
        for (long n : fibNumbers) {
            assertTrue(checker.isFibonacci(n), n + " should be Fibonacci");
        }
    }

    @Test
    void testNonFibonacciNumbers() {
        long[] nonFibNumbers = {4, 6, 7, 9, 10, 15, 20, 22, 35, 50};
        for (long n : nonFibNumbers) {
            assertFalse(checker.isFibonacci(n), n + " should NOT be Fibonacci");
        }
    }

    @Test
    void testNegativeNumbers() {
        assertFalse(checker.isFibonacci(-1));
        assertFalse(checker.isFibonacci(-8));
        assertFalse(checker.isFibonacci(-144));
    }

    @Test
    void testFibonacciIndex() {
        assertEquals(0, checker.fibonacciIndex(0));
        assertEquals(1, checker.fibonacciIndex(1));
        assertEquals(5, checker.fibonacciIndex(5));
        assertEquals(12, checker.fibonacciIndex(144));
        assertEquals(-1, checker.fibonacciIndex(4)); // not Fibonacci
    }

    @Test
    public void testIs_0_Fibonacci () {
        FibonacciStatus zeroFibonacciStatus = new FibonacciStatus(0L, true, 0);
        assertEquals(zeroFibonacciStatus, checker.isFibonacciNumber(0L));
    }
}

