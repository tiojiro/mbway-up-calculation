package com.tiojiro.upcalculation.web;

import com.tiojiro.upcalculation.service.CalculationService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/calc")
@AllArgsConstructor
public class CalculationController {

    private final CalculationService service;

    @GetMapping("/daily")
    public ResponseEntity<List<String>> getDaily(@RequestParam(required = true) long numberOfDays) {
        return ResponseEntity.ok(service.getDaily(numberOfDays));
    }

    @GetMapping("/weekly")
    public ResponseEntity<List<String>> getWeekly(@RequestParam(required = true) long numberOfWeeks) {
        return ResponseEntity.ok(service.getWeekly(numberOfWeeks));
    }

    @GetMapping("/monthly")
    public ResponseEntity<List<String>> getMonthly(@RequestParam(required = true) long numberOfMonths) {
        return ResponseEntity.ok(service.getMonthly(numberOfMonths));
    }
}