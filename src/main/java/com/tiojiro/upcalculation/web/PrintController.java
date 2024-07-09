package com.tiojiro.upcalculation.web;

import com.tiojiro.upcalculation.service.PrintService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/print")
@AllArgsConstructor
public class PrintController {

    private PrintService service;

    @GetMapping("/readme")
    public ResponseEntity<String> printReadMe(@RequestParam long numberOfDays, @RequestParam long numberOfWeeks, @RequestParam long numberOfMonths) {
        return ResponseEntity.ok(service.printReadMe(numberOfDays, numberOfWeeks, numberOfMonths));
    }
}