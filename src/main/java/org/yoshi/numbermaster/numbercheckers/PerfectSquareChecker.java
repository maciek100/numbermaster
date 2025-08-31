package org.yoshi.numbermaster.numbercheckers;

import org.yoshi.numbermaster.model.PerfectSquareStatus;

public class PerfectSquareChecker {
    public PerfectSquareStatus isPerfectSquare(long number) {
        long sqrt = (long) Math.sqrt(number);
        boolean isPerfectSqr = sqrt * sqrt == number;
        return new PerfectSquareStatus(number, isPerfectSqr, isPerfectSqr ? sqrt : 0L);
    }
}
