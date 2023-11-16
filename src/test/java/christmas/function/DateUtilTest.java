package christmas.function;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import christmas.utils.DateUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class DateUtilTest {

    @ParameterizedTest
    @DisplayName("주말 테스트 (금, 토)")
    @ValueSource(ints = {15, 16}) // 15일 금요일, 16일 토요일
    void returnsTrueForWeekend(int day) {
        assertTrue(DateUtil.isWeekend(day));
    }

    @ParameterizedTest
    @DisplayName("주중 테스트 (일 ~ 목)")
    @ValueSource(ints = {18, 19, 20, 21}) // 18일 월요일, 19일 화요일, 20일 수요일, 21일 목요일
    void returnsFalseForWeekdays(int day) {
        assertFalse(DateUtil.isWeekend(day));
    }

    @ParameterizedTest
    @DisplayName("스페셜데이 테스트 (크리스마스, 일)")
    @ValueSource(ints = {25, 31}) // 25일 크리스마스, 31일 일요일
    void returnsTrueForSpecialDiscountDay(int day) {
        assertTrue(DateUtil.isSpecialDiscountDay(day));
    }

    @ParameterizedTest
    @DisplayName("스페셜데이가 아닌 날 테스트")
    @ValueSource(ints = {22, 26}) // 22일 금요일, 26일 화요일
    void returnsFalseForNonSpecialDiscountDays(int day) {
        assertFalse(DateUtil.isSpecialDiscountDay(day));
    }

}
