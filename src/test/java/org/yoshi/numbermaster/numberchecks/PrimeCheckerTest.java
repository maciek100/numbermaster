package org.yoshi.numbermaster.numberchecks;

import org.junit.jupiter.api.Test;
import org.yoshi.numbermaster.model.PrimeStatus;
import org.yoshi.numbermaster.numbercheckers.PrimeChecker;

import static org.junit.jupiter.api.Assertions.*;

class PrimeCheckerTest {

    private final PrimeChecker primeChecker = new PrimeChecker();

    @Test
    void testNonPrimes() {
        assertFalse(primeChecker.checkPrimality(1).isPrime());
        assertFalse(primeChecker.checkPrimality(0).isPrime());
        assertFalse(primeChecker.checkPrimality(-7).isPrime());
        assertFalse(primeChecker.checkPrimality(100).isPrime());
    }

    @Test
    void testPrimes() {
        assertTrue(primeChecker.checkPrimality(2).isPrime());
        assertTrue(primeChecker.checkPrimality(3).isPrime());
        assertTrue(primeChecker.checkPrimality(13).isPrime());
        assertTrue(primeChecker.checkPrimality(17).isPrime());
    }

    @Test
    void testMersennePrimes() {
        PrimeStatus status3 = primeChecker.checkPrimality(3); // 2^2 -1
        assertTrue(status3.isPrime());
        assertTrue(status3.isMersennePrime());
        assertEquals(2, status3.mersenneExponent());

        PrimeStatus status7 = primeChecker.checkPrimality(7); // 2^3 -1
        assertTrue(status7.isPrime());
        assertTrue(status7.isMersennePrime());
        assertEquals(3, status7.mersenneExponent());

        PrimeStatus status31 = primeChecker.checkPrimality(31); // 2^5 -1
        assertTrue(status31.isPrime());
        assertTrue(status31.isMersennePrime());
        assertEquals(5, status31.mersenneExponent());
    }

    @Test
    void testNonMersennePrimes() {
        PrimeStatus status13 = primeChecker.checkPrimality(13);
        assertTrue(status13.isPrime());
        assertFalse(status13.isMersennePrime());
        assertEquals(0, status13.mersenneExponent());
    }
}
