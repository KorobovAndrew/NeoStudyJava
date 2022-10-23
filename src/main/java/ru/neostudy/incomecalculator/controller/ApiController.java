package ru.neostudy.incomecalculator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.neostudy.incomecalculator.service.CalculationService;

@RequestMapping("/api/v1")
@RestController
public class ApiController {

    private final CalculationService calculationService;

    @Autowired
    public ApiController(CalculationService calculationService) {
        this.calculationService = calculationService;
    }

    @GetMapping("/calculate")
    public ResponseEntity<?> getVacationIncome(
            @RequestParam double averageSalary,
            @RequestParam int vacationDays
    ) {

        var vacationIncome = calculationService.calculateSalary(averageSalary, vacationDays);

        return ResponseEntity.ok(
                vacationIncome
        );
    }
}
