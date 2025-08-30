package org.yoshi.numbermaster.service;

import org.yoshi.dto.PrimeCheckRequest;
import org.yoshi.dto.PrimeCheckResponse;
import org.yoshi.numbermaster.model.MersennePrimeRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class NumberEvaluationService {

    private static final Logger logger = LoggerFactory.getLogger(NumberEvaluationService.class);

    // In-memory storage for Mersenne primes (in production, use a database)
    private final Map<Long, MersennePrimeRecord> storedMersennePrimes = new ConcurrentHashMap<>();

    private long counter = 0L;

    // Known Mersenne primes for verification (2^p - 1 where p is prime)
    private static final Set<Integer> KNOWN_MERSENNE_PRIMES = Set.of(
            3, 7, 31, 127, 8191, 131071, 524287, 2147483647
    );

    public PrimeCheckResponse evaluateNumber(PrimeCheckRequest request) {
        long number = request.number();
        long requestId = request.requestId();
        counter++;
        logger.info("Evaluating number: {} (requestId: {})", number, requestId);
        Optional<PrimeCheckResponse> optResponse = checkSmallIntegers(number, requestId);
        if (optResponse.isPresent()) return optResponse.get();
        boolean isPrime = isPrime(number);
        boolean isMersenne = false;
        String message;

        if (isPrime) {
            isMersenne = isMersennePrime(number);

            if (isMersenne) {
                // Store the Mersenne prime
                MersennePrimeRecord record = new MersennePrimeRecord(
                        number,
                        findMersenneExponent(number),
                        LocalDateTime.now(),
                        "Found by NumberMaker service"
                );
                storedMersennePrimes.put(number, record);

                message = String.format("MERSENNE PRIME FOUND! %d = 2^%d - 1",
                        number, findMersenneExponent(number));

                logger.info("Mersenne prime stored: {}", message);
            } else {
                message = String.format("Prime number: %d (not Mersenne)", number);
            }
        } else {
            message = String.format("Composite number: %d", number);
        }

        return new PrimeCheckResponse(number, requestId, isPrime, isMersenne, message);
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

    public Collection<MersennePrimeRecord> getStoredMersennePrimes() {
        return new ArrayList<>(storedMersennePrimes.values());
    }

    private Optional<PrimeCheckResponse> checkSmallIntegers ( long number, long requestId) {
        if ( number < 2)
            return Optional.of(new PrimeCheckResponse(number, requestId, false, false,
                    "This number is clasified as neither prime nor composite"));
        return Optional.empty();
    }

    public long getCounter () {
        return counter;
    }
}
