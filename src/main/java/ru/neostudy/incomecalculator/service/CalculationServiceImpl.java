package ru.neostudy.incomecalculator.service;

import org.springframework.stereotype.Service;

@Service
public class CalculationServiceImpl implements CalculationService {
    @Override
    public double calculateSalary(double averageSalary, int vacationDays) {
        return averageSalary / 30 * vacationDays;
    }
}
