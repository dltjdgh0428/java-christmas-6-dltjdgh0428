package christmas.domain.vo;

import christmas.utils.ParserUtil;
import christmas.utils.ValidationUtil;

public class DateVO {
    private final int day;

    public DateVO(String inputDate) {
        ValidationUtil.validateDateFormat(inputDate);
        this.day = ParserUtil.parseDate(inputDate);
        ValidationUtil.validateDate(this.day);
    }

    public int getDate() {
        return day;
    }
}
