package org.yoshi.numbermaster.numberchecks;

import org.junit.jupiter.api.Test;
import org.yoshi.numbermaster.model.PerfectSquareStatus;
import org.yoshi.numbermaster.numbercheckers.PerfectSquareChecker;

import static org.junit.jupiter.api.Assertions.*;

public class PerfectSquareCheckerTest {

    private PerfectSquareChecker perfectSquareChecker = new PerfectSquareChecker();

    @Test
    public void testSquareChecking () {
        PerfectSquareStatus status81 = new PerfectSquareStatus(81, true, 9);
        PerfectSquareStatus status275 = new PerfectSquareStatus(275, false, 0);
        assertEquals(status81, perfectSquareChecker.isPerfectSquare(81));
        assertEquals(status275, perfectSquareChecker.isPerfectSquare(275));
    }
}
