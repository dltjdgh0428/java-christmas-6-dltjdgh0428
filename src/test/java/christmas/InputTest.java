package christmas;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import christmas.utils.ValidationUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class InputTest {

    @Test
    @DisplayName("유효한 날짜 문자열은 예외를 발생시키지 않아야 함")
    public void validDateString() {
        // given
        String validInput = "15";

        // when & then
        assertDoesNotThrow(() -> ValidationUtil.validateDateString(validInput));
    }

    @Test
    @DisplayName("유효하지 않은 날짜 문자열은 예외를 발생시켜야 함")
    public void invalidDateString() {
        // given
        String invalidInput = "32";

        // when & then
        assertThrows(IllegalArgumentException.class, () -> ValidationUtil.validateDateString(invalidInput));
    }

    @Test
    @DisplayName("유효한 날짜 정수는 예외를 발생시키지 않아야 함")
    public void validDateInteger() {
        // given
        int validDate = 15;

        // when & then
        assertDoesNotThrow(() -> ValidationUtil.validateDate(validDate));
    }

    @Test
    @DisplayName("유효하지 않은 날짜 정수는 예외를 발생시켜야 함")
    public void invalidDateInteger() {
        // given
        int invalidDate = 32;

        // when & then
        assertThrows(IllegalArgumentException.class, () -> ValidationUtil.validateDate(invalidDate));
    }

    @Test
    @DisplayName("숫자가 아닌 문자열은 예외를 발생시켜야 함")
    public void nonNumericDateString() {
        // given
        String nonNumericInput = "abc";

        // when & then
        assertThrows(IllegalArgumentException.class, () -> ValidationUtil.validateDateString(nonNumericInput));
    }

    @Test
    @DisplayName("유효한 한 자리 날짜 문자열은 예외를 발생시키지 않아야 함")
    public void singleDigitDateString() {
        // given
        String singleDigitInput = "5";

        // when & then
        assertDoesNotThrow(() -> ValidationUtil.validateDateString(singleDigitInput));
    }

    @Test
    @DisplayName("앞에 0이 붙은 날짜 문자열도 유효해야 함")
    public void leadingZeroDateString() {
        // given
        String leadingZeroInput = "05";

        // when & then
        assertDoesNotThrow(() -> ValidationUtil.validateDateString(leadingZeroInput));
    }
}
