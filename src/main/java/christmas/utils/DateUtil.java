package christmas.utils;

import static christmas.contents.ContentNumbers.CHRISTMAS_DAY;
import static christmas.contents.ContentNumbers.CORRECT_SUNDAY;
import static christmas.contents.ContentNumbers.WEEK;
import static christmas.contents.ContentNumbers.YEAR;
import static christmas.contents.ContentNumbers.MONTH;

import java.time.LocalDate;
import java.time.DayOfWeek;

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
