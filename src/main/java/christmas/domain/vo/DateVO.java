package christmas.domain.vo;

import christmas.utils.ParserUtil;
import christmas.utils.ValidationUtil;

public class DateVO {
    private final int day;

    public DateVO(String inputDate) {
        ValidationUtil.validateDateBeforeParse(inputDate);
        this.day = ParserUtil.parseDate(inputDate);
        ValidationUtil.validateDateAfterParse(this.day);
    }

    public int getDate() {
        return day;
    }
}
