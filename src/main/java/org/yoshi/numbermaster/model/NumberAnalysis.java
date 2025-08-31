package org.yoshi.numbermaster.model;

public record NumberAnalysis(long number,
                             long requestId,
                             boolean isPrime,
                             boolean isMersennePrime,
                             int mersenneExponent,
                             boolean isFibonacci,
                             int fibonacciIndex,
                             boolean isPerfectSquare,
                             long squareRoot
) {}
