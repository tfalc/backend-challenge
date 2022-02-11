package com.amdocs.backend;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

public class SLAAnalyzer {
    /**
     * Method will receive a particular problem opening date and amount of working hours (business hours) it should be
     * solved and return the maximum date and time it should be solved.
     * It should be considered:
     * - Business hours are from 8AM to 5PM excluding weekends and holidays
     * - Logic should consider only month of August 2019, Sao Carlos location
     * - Method signature cannot be changed
     * <p>
     * Aug 15 = holiday
     *
     * @param iOpeningDateTime - Problem opening date
     * @param iSLA             - Quantity of hours to solve the problem
     * @return Maximum date and time that problem should be solved
     */

    public static LocalDateTime calculateSLA(LocalDateTime iOpeningDateTime, Integer iSLA) {

        LocalDateTime startingBusinessHour = LocalDateTime.of(iOpeningDateTime.getYear(),
                iOpeningDateTime.getMonth(),
                iOpeningDateTime.getDayOfMonth(),
                8, 0, 0);
        LocalDateTime endingBusinessHour = startingBusinessHour.plusHours(9);

        boolean isAugustHoliday = iOpeningDateTime.getDayOfMonth() == 15;
        boolean isWeekend = iOpeningDateTime.getDayOfWeek().equals(DayOfWeek.SATURDAY)
                || iOpeningDateTime.getDayOfWeek().equals(DayOfWeek.SUNDAY);


        if (iOpeningDateTime.isBefore(startingBusinessHour) && !isAugustHoliday && !isWeekend) {
            iOpeningDateTime = startingBusinessHour.plusHours(iSLA);
        } else if (iOpeningDateTime.isAfter(endingBusinessHour) && !isAugustHoliday && !isWeekend) {
            iOpeningDateTime = startingBusinessHour.plusDays(1).plusHours(iSLA);
            iOpeningDateTime = isWeekendOrHoliday(iOpeningDateTime);
        } else if (iOpeningDateTime.isAfter(startingBusinessHour) && iOpeningDateTime.isBefore(endingBusinessHour)
                && !isAugustHoliday && !isWeekend) {
            LocalDateTime auxIOpeningDateTime = iOpeningDateTime;
            iOpeningDateTime = iOpeningDateTime.plusHours(iSLA);
            if (iOpeningDateTime.isAfter(endingBusinessHour)) {
                long countedHours = endingBusinessHour.getHour() - auxIOpeningDateTime.getHour();
                iOpeningDateTime = startingBusinessHour.plusDays(1).plusHours(iSLA).minusHours(countedHours);
                iOpeningDateTime = isWeekendOrHoliday(iOpeningDateTime);
            }
        } else if (isAugustHoliday) {
            iOpeningDateTime = startingBusinessHour.plusDays(1).plusHours(iSLA);
        }

        return iOpeningDateTime;
    }

    private static LocalDateTime isWeekendOrHoliday(LocalDateTime iOpeningDateTime) {
        if (iOpeningDateTime.getDayOfWeek().equals(DayOfWeek.SUNDAY)) {
            iOpeningDateTime = iOpeningDateTime.plusDays(1);
        }
        if (iOpeningDateTime.getDayOfWeek().equals(DayOfWeek.SATURDAY)) {
            iOpeningDateTime = iOpeningDateTime.plusDays(2);
        }
        if (iOpeningDateTime.getDayOfMonth() == 15)
            iOpeningDateTime = iOpeningDateTime.plusDays(1);
        return iOpeningDateTime;
    }

}
