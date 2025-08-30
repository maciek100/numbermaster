package org.yoshi.numbermaster.service;

import org.junit.jupiter.api.Test;
import org.yoshi.dto.PrimeCheckRequest;
import org.yoshi.dto.PrimeCheckResponse;

import static org.junit.jupiter.api.Assertions.*;

public class NumberEvaluationServiceTest {
    private NumberEvaluationService neService = new NumberEvaluationService();

    @Test
    void testIsPrimeWithPrimeNumber() {
        PrimeCheckRequest request = new PrimeCheckRequest(13, 1);
        PrimeCheckResponse expected = new PrimeCheckResponse(13,1,true, false,
                "Prime number: 13 (not Mersenne)");
        PrimeCheckResponse response = neService.evaluateNumber(request);
        assertEquals(expected, response);
    }

    @Test
    void testIsPrimeWithPrimeAndMersennePrimeNumber() {
        PrimeCheckRequest request = new PrimeCheckRequest(8191, 1);
        PrimeCheckResponse expected = new PrimeCheckResponse(8191,1,true, true,
                "MERSENNE PRIME FOUND! 8191 = 2^13 - 1");
        PrimeCheckResponse response = neService.evaluateNumber(request);
        assertEquals(expected, response);
    }

    @Test
    void testIsPrimeWithNonPrimeNumber() {
        PrimeCheckRequest request = new PrimeCheckRequest(24, 1);
        PrimeCheckResponse expected = new PrimeCheckResponse(24,1,false, false,
                "Composite number: 24");
        PrimeCheckResponse response = neService.evaluateNumber(request);
        assertEquals(expected, response);
    }

    @Test
    void testIsPrimeWithEdgeCases() {
        PrimeCheckRequest request = new PrimeCheckRequest(0, 1);
        PrimeCheckResponse expected = new PrimeCheckResponse(0,1,false, false,
                "This number is clasified as neither prime nor composite");
        PrimeCheckResponse response = neService.evaluateNumber(request);
        assertEquals(expected, response);
        request = new PrimeCheckRequest(-123477, 1);
        expected = new PrimeCheckResponse(-123477,1,false, false,
                "This number is clasified as neither prime nor composite");
        response = neService.evaluateNumber(request);
        assertEquals(expected, response);
    }
}
