package com.tiojiro.upcalculation.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.tiojiro.upcalculation.model.Constants.*;

@Component
@AllArgsConstructor
public class CalculationServiceImpl implements CalculationService {

    private final CalculationHelper helper;

    @Override
    public List<String> getDaily(long numberOfDays) {
        List<String> dayList = new ArrayList<>();
        LocalDateTime calcDate = DAILY_START_DATE;
        while (!calcDate.toLocalDate().isEqual(LocalDate.now().plusDays(numberOfDays))) {
            calcDate = calcDate.plusDays(ONE);
            calcDate = helper.isHourExceptionRule(calcDate.getHour()) ? calcDate.minusHours(SIXTEEN) : calcDate.plusHours(ONE);
            calcDate = helper.isMinuteExceptionRule(calcDate.getMinute()) ? calcDate.minusMinutes(THIRTY_SIX) : calcDate.plusMinutes(TWENTY_FOUR);

            helper.addDayToList(calcDate, dayList);
        }
        return dayList;
    }

    @Override
    public List<String> getWeekly(long numberOfWeeks) {
        List<String> dayList = new ArrayList<>();
        LocalDateTime calcDate = WEEKLY_START_DATE;
        for (int count = ZERO; calcDate.toLocalDate().isBefore(LocalDate.now().plusWeeks(numberOfWeeks)) ; count++) {
            calcDate = helper.getNextWeek(calcDate);
            calcDate = helper.getWeeklyHour(calcDate, calcDate.getDayOfWeek());
            calcDate = helper.getWeeklyMinute(calcDate, count);

            helper.addDayToList(calcDate, dayList);
        }
        return dayList;
    }

    @Override
    public List<String> getMonthly(long numberOfMonths) {
        List<String> dayList = new ArrayList<>();
        LocalDateTime calcDate = MONTHLY_START_DATE;
        while (calcDate.toLocalDate().isBefore(LocalDate.now().plusMonths(numberOfMonths))) {
            calcDate = calcDate.plusMonths(ONE);
            calcDate = helper.getLastSaturdayMonth(calcDate);
            calcDate = helper.isHourExceptionRule(calcDate.getHour()) ? calcDate.minusHours(SIXTEEN) : calcDate.plusHours(ONE);

            helper.addDayToList(calcDate, dayList);
        }
        return dayList;
    }
}