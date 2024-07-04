package com.tiojiro.upcalculation.service;

import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static com.tiojiro.upcalculation.model.Constants.*;

@Component
public class CalculationServiceImpl implements CalculationService{
    @Override
    public List<String> getDaily(long numberOfDays) {
        List<String> dayList = new ArrayList<>();
        LocalDateTime calcDate = dailyStartDate;
        while(!calcDate.toLocalDate().isEqual(LocalDate.now().plusDays(numberOfDays))){
            calcDate = calcDate.plusDays(ONE);
            calcDate = isHourExceptionRule(calcDate.getHour()) ? calcDate.minusHours(SIXTEEN) : calcDate.plusHours(ONE);
            calcDate = isMinuteExceptionRule(calcDate.getMinute()) ? calcDate.minusMinutes(THIRTY_SIX) : calcDate.plusMinutes(TWENTY_FOUR);

            addDayToList(calcDate, dayList);
        }
        return dayList;
    }

    @Override
    public List<String> getWeekly(long numberOfWeeks) {
        List<String> dayList = new ArrayList<>();
        LocalDateTime calcDate = weeklyStartDate;
        for (int count = ZERO; calcDate.toLocalDate().isBefore(LocalDate.now().plusWeeks(numberOfWeeks)) ; count++){
            calcDate = getNextWeek(calcDate);
            calcDate = getWeeklyHour(calcDate, calcDate.getDayOfWeek());
            calcDate = getWeeklyMinute(calcDate, count);

            addDayToList(calcDate, dayList);
        }
        return dayList;
    }

    @Override
    public List<String> getMonthly(long numberOfMonths) {
        List<String> dayList = new ArrayList<>();
        LocalDateTime calcDate = monthlyStartDate;
        while(calcDate.toLocalDate().isBefore(LocalDate.now().plusMonths(numberOfMonths))) {
            calcDate = calcDate.plusMonths(ONE);
            calcDate = getLastSaturdayMonth(calcDate);
            calcDate = isHourExceptionRule(calcDate.getHour()) ? calcDate.minusHours(SIXTEEN) : calcDate.plusHours(ONE);

            addDayToList(calcDate, dayList);
        }
        return dayList;
    }

    private boolean isHourExceptionRule(int hour){ return hour+ONE > TWENTY_THREE; }

    private boolean isMinuteExceptionRule(int minute){ return minute+TWENTY_FOUR > FIFTY_NINE; }

    private LocalDateTime getNextWeek(LocalDateTime ldt){
        if (ldt.getDayOfWeek() == DayOfWeek.SUNDAY)
            return ldt.plusDays(ONE);
        return ldt.plusDays(EIGHT);
    }

    private LocalDateTime getWeeklyHour(LocalDateTime ldt, DayOfWeek dow){
        return switch (dow) {
            case MONDAY -> ldt.withHour(ELEVEN_AM_O_CLOCK);
            case TUESDAY -> ldt.withHour(ONE_PM_O_CLOCK);
            case WEDNESDAY -> ldt.withHour(THREE_PM_O_CLOCK);
            case THURSDAY -> ldt.withHour(FIVE_PM_O_CLOCK);
            case FRIDAY -> ldt.withHour(SEVEN_PM_O_CLOCK);
            case SATURDAY -> ldt.withHour(NINE_PM_O_CLOCK);
            case SUNDAY -> ldt.withHour(NINE_AM_O_CLOCK);
        };
    }

    private LocalDateTime getWeeklyMinute(LocalDateTime ldt, Integer count){
        return count % TWO == ZERO ? ldt.withMinute(FIFTEEN_MINUTES) : ldt.withMinute(FORTY_FIVE_MINUTES);
    }

    private LocalDateTime getLastSaturdayMonth(LocalDateTime ldt){
        YearMonth ym = YearMonth.of(ldt.getYear(),ldt.getMonthValue());
        ldt = ldt.withDayOfMonth(ym.atEndOfMonth().getDayOfMonth());
        while (ldt.getDayOfWeek() != DayOfWeek.SATURDAY){
            ldt = ldt.minusDays(ONE);
        }
        return ldt;
    }

    private void addDayToList(LocalDateTime calcDate, List<String> dayList) {
        LocalDate ld = calcDate.toLocalDate();
        if(ld.isAfter(LocalDate.now()) || ld.isEqual(LocalDate.now()))
            dayList.add(dateFormatter(calcDate));
    }

    private String dateFormatter (LocalDateTime ldt) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(DATE_PATTERN);
        return dtf.format(LocalDateTime.of(ldt.toLocalDate(),ldt.toLocalTime()));
    }
}