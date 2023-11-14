package christmas.utils;

import java.time.LocalDate;
import java.time.DayOfWeek;

import static christmas.contents.ContentNumbers.*;

public class DateUtil {

    public static boolean isWeekend(int day) {
        LocalDate date = LocalDate.of(YEAR.getValue(), MONTH.getValue(), day);
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        return dayOfWeek == DayOfWeek.FRIDAY || dayOfWeek == DayOfWeek.SATURDAY;
    }

    public static boolean isSpecialDiscountDay(int day) {
        return day == CHRISTMAS_DAY.getValue() || day % WEEK.getValue() == CORRECT_SUNDAY.getValue();
    }
}
