package com.tiojiro.upcalculation.service;

import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static com.tiojiro.upcalculation.model.Constants.*;

@Component
public class CalculationHelper {

    public boolean isHourExceptionRule(int hour) {
        return hour+ONE > TWENTY_THREE;
    }

    public boolean isMinuteExceptionRule(int minute) {
        return minute+TWENTY_FOUR > FIFTY_NINE;
    }

    public LocalDateTime getNextWeek(LocalDateTime ldt) {
        if (ldt.getDayOfWeek() == DayOfWeek.SUNDAY)
            return ldt.plusDays(ONE);
        return ldt.plusDays(EIGHT);
    }

    public LocalDateTime getWeeklyHour(LocalDateTime ldt, DayOfWeek dow) {
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

    public LocalDateTime getWeeklyMinute(LocalDateTime ldt, Integer count) {
        return count % TWO == ZERO ? ldt.withMinute(FIFTEEN_MINUTES) : ldt.withMinute(FORTY_FIVE_MINUTES);
    }

    public LocalDateTime getLastSaturdayMonth(LocalDateTime ldt) {
        YearMonth ym = YearMonth.of(ldt.getYear(),ldt.getMonthValue());
        ldt = ldt.withDayOfMonth(ym.atEndOfMonth().getDayOfMonth());
        while (ldt.getDayOfWeek() != DayOfWeek.SATURDAY) {
            ldt = ldt.minusDays(ONE);
        }
        return ldt;
    }

    public void addDayToList(LocalDateTime calcDate, List<String> dayList) {
        LocalDate ld = calcDate.toLocalDate();
        if(ld.isAfter(LocalDate.now()) || ld.isEqual(LocalDate.now()))
            dayList.add(dateFormatter(calcDate));
    }

    public String dateFormatter (LocalDateTime ldt) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(DATE_PATTERN);
        return dtf.format(LocalDateTime.of(ldt.toLocalDate(),ldt.toLocalTime()));
    }
}