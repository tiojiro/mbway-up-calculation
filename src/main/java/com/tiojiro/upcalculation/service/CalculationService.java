package com.tiojiro.upcalculation.service;

import java.util.List;

public interface CalculationService {

    List<String> getDaily(long numberOfDays);
    List<String> getWeekly(long numberOfWeeks);
    List<String> getMonthly(long numberOfMonths);
}