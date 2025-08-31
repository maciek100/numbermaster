package org.yoshi.numbermaster.numbercheckers;

import org.yoshi.numbermaster.model.PrimeStatus;

import java.util.Optional;

public class PrimeChecker {

    public PrimeStatus checkPrimality(long number) {
        if (number <= 1) {
            return new PrimeStatus(number, false, false, 0);
        }

        boolean isPrime = isPrime(number);
        if (!isPrime || number % 2 == 0) {
            return new PrimeStatus(number, isPrime, false, 0);
        }
        int mersenneExponent = isMersennePrime(number)
                ? findMersenneExponent(number)
                : 0;
        return new PrimeStatus(number, true, mersenneExponent > 0, mersenneExponent);
    }

    private boolean isMersennePrime(long n) {
        // A Mersenne prime is a prime of the form 2^p - 1 where p is also prime
        // Check if n + 1 is a power of 2
        long candidate = n + 1;

        // Check if candidate is a power of 2
        if ((candidate & (candidate - 1)) != 0) {
            return false;
        }

        // Find the exponent p
        long p = Long.numberOfTrailingZeros(candidate);

        // Check if p is prime
        return isPrime(p);
    }

    private int findMersenneExponent(long mersennePrime) {
        long candidate = mersennePrime + 1;
        return Long.numberOfTrailingZeros(candidate);
    }

    private boolean isPrime(long n) {
        if (n <= 1) return false;
        if (n <= 3) return true;
        if (n % 2 == 0 || n % 3 == 0) return false;

        for (long i = 5; i * i <= n; i += 6) {
            if (n % i == 0 || n % (i + 2) == 0) {
                return false;
            }
        }
        return true;
    }
}
