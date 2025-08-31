package org.yoshi.numbermaster.service;

import org.junit.jupiter.api.Test;
import org.yoshi.dto.NumberCheckRequest;
import org.yoshi.numbermaster.model.NumberAnalysis;

import static org.junit.jupiter.api.Assertions.*;

public class NumberEvaluationServiceTest {
    private NumberEvaluationService neService = new NumberEvaluationService();

    @Test
    void testIsPrimeWithPrimeNumber() {
        NumberCheckRequest request = new NumberCheckRequest(13, 1);
        NumberAnalysis  expected = new NumberAnalysis(13, 1, true, false, 0, true, 7, false, 0);
        NumberAnalysis response = neService.evaluateNumber(request);
        assertEquals(expected, response);
    }

    @Test
    void testIsPrimeWithPrimeAndMersennePrimeNumber() {
        NumberCheckRequest request = new NumberCheckRequest(8191, 1);
        NumberAnalysis  expected = new NumberAnalysis(8191, 1, true, true, 13, false, 0, false, 0);
        NumberAnalysis response = neService.evaluateNumber(request);
        assertEquals(expected, response);
    }

    @Test
    void testIsPrimeWithNonPrimeNumber() {
        NumberCheckRequest request = new NumberCheckRequest(24, 1);
        NumberAnalysis  expected = new NumberAnalysis(24, 1, false, false, 0, false, 0, false, 0);
        NumberAnalysis response = neService.evaluateNumber(request);
        assertEquals(expected, response);
    }

    @Test
    void testIsPrimeWithEdgeCases() {
        NumberCheckRequest request = new NumberCheckRequest(0, 1);
        NumberCheckResponse2 expected2 = new NumberCheckResponse2(0,1,false, false,
                "This number is clasified as neither prime nor composite");
        NumberAnalysis  expected = new NumberAnalysis(0, 1, false, false, 0, true, 0, true, 0);
        NumberAnalysis response = neService.evaluateNumber(request);
        assertEquals(expected, response);

        request = new NumberCheckRequest(13, 1);
        expected = new NumberAnalysis(13, 1, true, false, 0, true, 7, false, 0);
        response = neService.evaluateNumber(request);
        assertEquals(expected, response);

        request = new NumberCheckRequest(-123477, 1);
        expected = new NumberAnalysis(-123477, 1, false, false, 0, false, 0, false, 0);
        response = neService.evaluateNumber(request);
        assertEquals(expected, response);
    }
}
