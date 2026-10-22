package com.tiojiro.upcalculation.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;

import static com.tiojiro.upcalculation.model.Constants.*;

@Component
@AllArgsConstructor
public class PrintServiceImpl implements PrintService {

    private final CalculationService calculationService;

    @Override
    public String printReadMe(long numberOfDays, long numberOfWeeks, long numberOfMonths) {
        StringBuffer daily = new StringBuffer();
        calculationService.getDaily(numberOfDays).forEach(d -> {
            if (daily.isEmpty()) {
                daily.append(System.lineSeparator()).append(INDENT3).append(d);
            } else {
                daily.append(System.lineSeparator()).append(INDENT3).append(LINE_BREAK).append(d);
            }
        });

        StringBuffer weekly = new StringBuffer();
        calculationService.getWeekly(numberOfWeeks).forEach(d -> {
            if (weekly.isEmpty()) {
                weekly.append(System.lineSeparator()).append(INDENT3).append(d);
            } else {
                weekly.append(System.lineSeparator()).append(INDENT3).append(LINE_BREAK).append(d);
            }
        });

        StringBuffer monthly = new StringBuffer();
        calculationService.getMonthly(numberOfMonths).forEach(d -> {
            if (monthly.isEmpty()) {
                monthly.append(System.lineSeparator()).append(INDENT3).append(d);
            } else {
                monthly.append(System.lineSeparator()).append(INDENT3).append(LINE_BREAK).append(d);
            }
        });
        return MessageFormat.format(README_TEMPLATE, daily, weekly, monthly);
    }
}