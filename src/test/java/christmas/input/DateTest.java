package christmas.input;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import christmas.domain.vo.DateVO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DateTest {

    @Test
    @DisplayName("유효한 날짜 문자열은 예외를 발생시키지 않아야 함")
    public void validDateString() {
        // given
        String validInput = "15";

        // when & then
        assertDoesNotThrow(() -> new DateVO(validInput));
    }

    @Test
    @DisplayName("유효하지 않은 날짜 문자열은 예외를 발생시켜야 함")
    public void invalidDateString() {
        // given
        String invalidInput = "32";

        // when & then
        assertThrows(IllegalArgumentException.class, () -> new DateVO(invalidInput));
    }

    @Test
    @DisplayName("숫자가 아닌 문자열은 예외를 발생시켜야 함")
    public void nonNumericDateString() {
        // given
        String nonNumericInput = "abc";

        // when & then
        assertThrows(IllegalArgumentException.class, () -> new DateVO(nonNumericInput));
    }

    @Test
    @DisplayName("유효한 한 자리 날짜 문자열은 예외를 발생시키지 않아야 함")
    public void singleDigitDateString() {
        // given
        String singleDigitInput = "5";

        // when & then
        assertDoesNotThrow(() -> new DateVO(singleDigitInput));
    }

    @Test
    @DisplayName("앞에 0이 붙은 날짜 문자열도 유효해야 함")
    public void leadingZeroDateString() {
        // given
        String leadingZeroInput = "05";

        // when & then
        assertDoesNotThrow(() -> new DateVO(leadingZeroInput));
    }
}