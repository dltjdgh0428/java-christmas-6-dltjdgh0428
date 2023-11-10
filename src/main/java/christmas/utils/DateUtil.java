package christmas.utils;

import java.time.LocalDate;
import java.time.DayOfWeek;

import static christmas.contents.ContentNumbers.*;
import static christmas.contents.DiscountAmounts.*;

public class DateUtil {

    public static boolean isWeekend(int day) {
        LocalDate date = LocalDate.of(YEAR.getValue(), MONTH.getValue(), day);
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        return dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY;
    }

    public static boolean isSpecialDiscountDay(int day) {
        return day == CHRISTMAS_DAY.getValue() || day % WEEK.getValue() == CORRECT_SUNDAY.getValue();
    }

}
