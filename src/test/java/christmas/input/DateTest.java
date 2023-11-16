package christmas.input;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import christmas.domain.vo.DateVO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class DateTest {

    @DisplayName("유효한 날짜 문자열은 정상 처리")
    @ValueSource(strings = {"15", "5", "05", "1", "31"})
    @ParameterizedTest
    public void validDateString(String input) {
        assertDoesNotThrow(() -> new DateVO(input));
    }

    @DisplayName("유효하지 않은 날짜 문자열은 예외 발생")
    @ValueSource(strings = {"32", "*", "_2", "abc", "00", "-1"})
    @ParameterizedTest
    public void invalidDateString(String input) {
        assertThrows(IllegalArgumentException.class, () -> new DateVO(input));
    }
}
