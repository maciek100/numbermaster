package org.yoshi.numbermaster.controller;


import org.yoshi.dto.PrimeCheckRequest;
import org.yoshi.dto.PrimeCheckResponse;
import org.yoshi.numbermaster.service.NumberEvaluationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/numbers")
public class NumberController {
    private NumberEvaluationService evaluationService;


    public NumberController(NumberEvaluationService evaluationService) {
        this.evaluationService = evaluationService;
    }

    @PostMapping("/evaluate")
    public ResponseEntity<PrimeCheckResponse> evaluateNumber(@RequestBody PrimeCheckRequest request) {
        PrimeCheckResponse response = evaluationService.evaluateNumber(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/stored")
    public ResponseEntity<?> getStoredMersennePrimes() {
        return ResponseEntity.ok(evaluationService.getStoredMersennePrimes());
    }

    @GetMapping("/count")
    public ResponseEntity<Long> getCurrentCount () {
       return ResponseEntity.ok(evaluationService.getCounter());
    }
}




