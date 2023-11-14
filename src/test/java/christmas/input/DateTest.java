package christmas.input;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import christmas.domain.vo.DateVO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DateTest {

    @Test
    @DisplayName("유효한 날짜 문자열은 정상 처리")
    public void validDateString() {
        // given
        String validInput = "15";

        // when & then
        assertDoesNotThrow(() -> new DateVO(validInput));
    }

    @Test
    @DisplayName("유효하지 않은 날짜 문자열은 예외 발생")
    public void invalidDateString() {
        // given
        String invalidInput = "32";

        // when & then
        assertThrows(IllegalArgumentException.class, () -> new DateVO(invalidInput));
    }

    @Test
    @DisplayName("숫자가 아닌 문자열은 예외 발생_1")
    public void nonNumericDateString_1() {
        // given
        String nonNumericInput = "*";

        // when & then
        assertThrows(IllegalArgumentException.class, () -> new DateVO(nonNumericInput));
    }

    @Test
    @DisplayName("숫자가 아닌 문자열은 예외 발생_2")
    public void nonNumericDateString_2() {
        // given
        String nonNumericInput = "_2";

        // when & then
        assertThrows(IllegalArgumentException.class, () -> new DateVO(nonNumericInput));
    }

    @Test
    @DisplayName("숫자가 아닌 문자열은 예외 발생_3")
    public void nonNumericDateString_3() {
        // given
        String nonNumericInput = "abc";

        // when & then
        assertThrows(IllegalArgumentException.class, () -> new DateVO(nonNumericInput));
    }

    @Test
    @DisplayName("유효한 한 자리 날짜 문자열은 정상 처리")
    public void singleDigitDateString() {
        // given
        String singleDigitInput = "5";

        // when & then
        assertDoesNotThrow(() -> new DateVO(singleDigitInput));
    }

    @Test
    @DisplayName("앞에 0이 붙은 날짜 문자열도 유효")
    public void leadingZeroDateString() {
        // given
        String leadingZeroInput = "05";

        // when & then
        assertDoesNotThrow(() -> new DateVO(leadingZeroInput));
    }
}
