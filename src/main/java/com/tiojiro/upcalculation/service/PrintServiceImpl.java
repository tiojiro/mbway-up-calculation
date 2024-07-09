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
        calculationService.getDaily(numberOfDays).forEach(d-> daily.append(d).append(LINE_BREAK));

        StringBuffer weekly = new StringBuffer();
        calculationService.getMonthly(numberOfWeeks).forEach(d-> weekly.append(d).append(LINE_BREAK));

        StringBuffer monthly = new StringBuffer();
        calculationService.getDaily(numberOfMonths).forEach(d-> monthly.append(d).append(LINE_BREAK));

        return MessageFormat.format(README_TEMPLATE, daily, weekly, monthly);
    }
}