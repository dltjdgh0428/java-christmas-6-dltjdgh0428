package christmas.utils;

import christmas.contents.EventDetails;

import static christmas.contents.ErrorMessages.INVALID_DATE;

public class ValidationUtil {
    //입력 String이 1~31의 값을 갖는지 확인하는 REGEX입니다
    public static final String DATE_REGEX = "^(3[01]|[12][0-9]|0?[1-9])$";

    public static void validateDateString(String inputDate) {
        if (!inputDate.matches(DATE_REGEX)) {
            throw new IllegalArgumentException(INVALID_DATE.getMessage());
        }
    }

    public static void validateDate(int date) {
        if (date < EventDetails.MIN_DATE.getValue() || date > EventDetails.MAX_DATE.getValue()) {
            throw new IllegalArgumentException(INVALID_DATE.getMessage());
        }
    }

}
