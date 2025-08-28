package numbermaster.controller;


import numbermaster.dto.NumberRequest;
import numbermaster.dto.NumberResponse;
import numbermaster.service.NumberEvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<NumberResponse> evaluateNumber(@RequestBody NumberRequest request) {
        NumberResponse response = evaluationService.evaluateNumber(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/stored")
    public ResponseEntity<?> getStoredMersennePrimes() {
        return ResponseEntity.ok(evaluationService.getStoredMersennePrimes());
    }
}
