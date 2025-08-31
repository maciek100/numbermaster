package org.yoshi.numbermaster.controller;


import org.yoshi.dto.NumberCheckRequest;
import org.yoshi.numbermaster.model.NumberAnalysis;
import org.yoshi.numbermaster.service.NumberEvaluationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/numbers")
public class NumberController {
    private final NumberEvaluationService evaluationService;


    public NumberController(NumberEvaluationService evaluationService) {
        this.evaluationService = evaluationService;
    }

    @PostMapping("/evaluate")
    public ResponseEntity<NumberAnalysis> evaluateNumber(@RequestBody NumberCheckRequest request) {
        NumberAnalysis response = evaluationService.evaluateNumber(request);
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




