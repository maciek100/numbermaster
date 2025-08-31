package org.yoshi.numbermaster.model;

public record PrimeStatus(long number, boolean isPrime, boolean isMersennePrime, int mersenneExponent) {}
