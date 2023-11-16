package christmas.function;

import static org.junit.jupiter.api.Assertions.*;

import christmas.utils.ValidationUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import christmas.contents.MenuCatalog;
import java.util.Map;
import java.util.HashMap;

public class ValidationUtilTest {

    @Test
    @DisplayName("유효한 날짜 포맷 검증")
    void testValidDate() {
        // given
        String validDate = "15";

        // when & then
        assertDoesNotThrow(() -> ValidationUtil.validateDateBeforeParse(validDate));
    }

    @Test
    @DisplayName("유효하지 않은 날짜 포맷 예외 발생")
    void testInvalidDateFormat() {
        // given
        String invalidDate = "32";

        // when & then
        assertThrows(IllegalArgumentException.class, () -> ValidationUtil.validateDateBeforeParse(invalidDate));
    }

    @Test
    @DisplayName("주문 포맷 검증 - 성공 케이스")
    void testValidOrderFormat() {
        // given
        String validOrder = "티본스테이크-1,제로콜라-2";

        // when & then
        assertDoesNotThrow(() -> ValidationUtil.validateOrderBeforeParse(validOrder));
    }

    @Test
    @DisplayName("주문 포맷 검증 - 숫자가 아닌 수량으로 인한 예외")
    void testInvalidOrderQuantityFormat() {
        // given
        String invalidOrder = "티본스테이크-abc";

        // when & then
        assertThrows(IllegalArgumentException.class, () -> ValidationUtil.validateOrderBeforeParse(invalidOrder));
    }

    @Test
    @DisplayName("중복 메뉴 검증 - 중복 메뉴가 있는 경우 예외 발생")
    void testDuplicateMenu() {
        // given
        String orderWithDuplicates = "티본스테이크-1,티본스테이크-2";

        // when & then
        assertThrows(IllegalArgumentException.class, () -> ValidationUtil.validateOrderBeforeParse(orderWithDuplicates));
    }

    @Test
    @DisplayName("총 주문 수량 검증 - 최대 주문 수량 초과")
    void testTotalOrderQuantityExceedsMax() {
        // given
        Map<MenuCatalog, Integer> orderMap = new HashMap<>();
        orderMap.put(MenuCatalog.T_BONE_STEAK, 10);
        orderMap.put(MenuCatalog.RED_WINE, 12);

        // when & then
        assertThrows(IllegalArgumentException.class, () -> ValidationUtil.validateTotalOrderQuantity(orderMap));
    }

    @Test
    @DisplayName("음료만 주문시 예외 처리")
    void testDrinksOnlyOrder() {
        // given
        Map<MenuCatalog, Integer> drinksOnlyOrder = new HashMap<>();
        drinksOnlyOrder.put(MenuCatalog.ZERO_COLA, 3);
        drinksOnlyOrder.put(MenuCatalog.RED_WINE, 2);

        // when & then
        assertThrows(IllegalArgumentException.class, () -> ValidationUtil.validateDrinksOnlyOrder(drinksOnlyOrder));
    }
}

