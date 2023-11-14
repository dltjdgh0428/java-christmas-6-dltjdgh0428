package christmas.function;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import christmas.utils.DateUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DateUtilTest {

    @Test
    @DisplayName("주말 테스트 금~토")
    void returnsTrueForWeekend() {
        assertTrue(DateUtil.isWeekend(15)); // 15일 금요일
        assertTrue(DateUtil.isWeekend(16)); // 16일 토요일
    }

    @Test
    @DisplayName("주중 테스트 일~목")
    void returnsFalseForWeekdays() {
        // 주중 (일요일 ~ 목요일)
        assertFalse(DateUtil.isWeekend(18)); // 18일 월요일
        assertFalse(DateUtil.isWeekend(19)); // 19일 화요일
        assertFalse(DateUtil.isWeekend(20)); // 20일 수요일
        assertFalse(DateUtil.isWeekend(21)); // 21일 목요일
    }

    @Test
    @DisplayName("스페셜데이 테스트 크리스마스,일")
    void returnsTrueForSpecialDiscountDay() {
        // 크리스마스와 일요일
        assertTrue(DateUtil.isSpecialDiscountDay(25)); // 크리스마스
        assertTrue(DateUtil.isSpecialDiscountDay(31)); // 31일 일요일
    }

    @Test
    @DisplayName("스페셜데이가 아닌날 테스트")
    void returnsFalseForNonSpecialDiscountDays() {
        // 특별 할인이 아닌 날
        assertFalse(DateUtil.isSpecialDiscountDay(22)); // 22일 금요일
        assertFalse(DateUtil.isSpecialDiscountDay(26)); // 26일 화요일
    }

}
