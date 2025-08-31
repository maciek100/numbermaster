package org.yoshi.numbermaster.service;

import org.yoshi.dto.NumberCheckRequest;
import org.yoshi.numbermaster.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.yoshi.numbermaster.numbercheckers.FibonacciChecker;
import org.yoshi.numbermaster.numbercheckers.PerfectSquareChecker;
import org.yoshi.numbermaster.numbercheckers.PrimeChecker;

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

    private final PrimeChecker primeChecker = new PrimeChecker();
    private final FibonacciChecker fibonacciChecker = new FibonacciChecker();
    private final PerfectSquareChecker perfectSquareChecker = new PerfectSquareChecker();

    public NumberAnalysis evaluateNumber(NumberCheckRequest request) {
        long number = request.number();
        long requestId = request.requestId();
        counter++;
        logger.info("Evaluating number: {} (requestId: {})", number, requestId);
        PrimeStatus primeStatus = primeChecker.checkPrimality(number);
        FibonacciStatus fibonacciStatus = fibonacciChecker.isFibonacciNumber(number);
        PerfectSquareStatus perfectSquareStatus = perfectSquareChecker.isPerfectSquare(number);
        return new NumberAnalysis(
                number,
                requestId,
                primeStatus.isPrime(),
                primeStatus.isMersennePrime(),
                primeStatus.mersenneExponent(),
                fibonacciStatus.isFibonacci(),
                fibonacciStatus.fibonacciIndex(),
                perfectSquareStatus.isPerfectSquare(),
                perfectSquareStatus.squareRoot()
        );
    }
/*
    private NumberAnalysis analyzeNumber (long number, long requestId) {
        PrimeStatus primeStatus = primeChecker.checkPrimality(number);
        FibonacciStatus fibonacciStatus = fibonacciChecker.isFibonacciNumber(number);
        PerfectSquareStatus perfectSquareStatus = perfectSquareChecker.isPerfectSquare(number);
        return new NumberAnalysis(
                number,
                requestId,
                primeStatus.isPrime(),
                primeStatus.isMersennePrime(),
                primeStatus.mersenneExponent(),
                fibonacciStatus.isFibonacci(),
                fibonacciStatus.fibonacciIndex(),
                perfectSquareStatus.isPerfectSquare(),
                perfectSquareStatus.squareRoot()
        );
    }
*/
    public Collection<MersennePrimeRecord> getStoredMersennePrimes() {
        return new ArrayList<>(storedMersennePrimes.values());
    }
/*
    private Optional<PrimeCheckResponse> checkSmallIntegers ( long number, long requestId) {
        if ( number < 2)
            return Optional.of(new PrimeCheckResponse(number, requestId, false, false,
                    "This number is classified as neither prime nor composite"));
        return Optional.empty();
    }
*/
    public long getCounter () {
        return counter;
    }
}
