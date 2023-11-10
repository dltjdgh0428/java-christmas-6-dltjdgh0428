package christmas.input;

import static org.junit.jupiter.api.Assertions.*;

import christmas.contents.MenuCatalog;
import christmas.domain.vo.OrderVO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class OrderTest {

    @Test
    @DisplayName("유효하지 않은 주문 형식은 예외 발생")
    public void invalidOrderFormatThrowsException() {
        // given
        String invalidOrder = "바비큐립-3,타파스"; // 올바르지 않은 형식

        // when & then
        assertThrows(IllegalArgumentException.class, () -> new OrderVO(invalidOrder));
    }

    @Test
    @DisplayName("메뉴판에 없는 메뉴 입력 시 예외 발생")
    public void nonExistentMenuThrowsException() {
        // given
        String nonExistentMenuOrder = "화성튀김-2"; // 존재하지 않는 메뉴

        // when & then
        assertThrows(IllegalArgumentException.class, () -> new OrderVO(nonExistentMenuOrder));
    }

    @Test
    @DisplayName("주문 수량이 범위를 벗어난 경우 예외 발생")
    public void outOfRangeQuantityThrowsException() {
        // given
        String outOfRangeQuantityOrder = "바비큐립-0"; // 수량 범위 벗어남

        // when & then
        assertThrows(IllegalArgumentException.class, () -> new OrderVO(outOfRangeQuantityOrder));
    }

    @Test
    @DisplayName("중복된 메뉴를 입력할 경우 예외 발생")
    public void duplicateMenuThrowsException() {
        // given
        String duplicateMenuOrder = "바비큐립-1,바비큐립-2"; // 중복 메뉴

        // when & then
        assertThrows(IllegalArgumentException.class, () -> new OrderVO(duplicateMenuOrder));
    }

    @Test
    @DisplayName("음료만 주문하는 경우 예외 발생")
    public void drinksOnlyOrderThrowsException() {
        // given
        String drinksOnlyOrder = "제로콜라-2,레드와인-1"; // 음료만 주문

        // when & then
        assertThrows(IllegalArgumentException.class, () -> new OrderVO(drinksOnlyOrder));
    }

    @Test
    @DisplayName("유효한 주문 형식은 정상적으로 처리")
    public void validOrderFormat() {
        // given
        String validOrder = "타파스-2,티본스테이크-1";

        // when
        OrderVO order = new OrderVO(validOrder);

        // then
        assertNotNull(order);
        assertEquals(2, order.getOrder().size());
        assertTrue(order.getOrder().containsKey(MenuCatalog.TAPAS));
        assertTrue(order.getOrder().containsKey(MenuCatalog.T_BONE_STEAK));
        assertEquals(2, (int) order.getOrder().get(MenuCatalog.TAPAS));
        assertEquals(1, (int) order.getOrder().get(MenuCatalog.T_BONE_STEAK));
    }

    @Test
    @DisplayName("다양한 메뉴 조합 정상적으로 처리")
    public void multipleMenuItemsCase_1() {
        // given
        String multipleItemsOrder = "바비큐립-3,아이스크림-2,제로콜라-1";

        // when
        OrderVO order = new OrderVO(multipleItemsOrder);

        // then
        assertNotNull(order);
        assertEquals(3, order.getOrder().size());
        assertTrue(order.getOrder().containsKey(MenuCatalog.BBQ_RIBS));
    }

    @Test
    @DisplayName("최대 주문 수량 정상적으로 처리")
    public void maxQuantityOrder() {
        // given
        String maxQuantityOrder = "초코케이크-20";

        // when
        OrderVO order = new OrderVO(maxQuantityOrder);

        // then
        assertNotNull(order);
        assertEquals(1, order.getOrder().size());
        assertTrue(order.getOrder().containsKey(MenuCatalog.CHOCOLATE_CAKE));
        assertEquals(20, (int) order.getOrder().get(MenuCatalog.CHOCOLATE_CAKE));
    }

}