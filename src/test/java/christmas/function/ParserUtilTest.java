package christmas.function;

import static org.junit.jupiter.api.Assertions.*;

import christmas.contents.MenuCatalog;
import christmas.utils.ParserUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;


class ParserUtilTest {

    @Test
    @DisplayName("주문 입력 파싱_1")
    void parseValidOrderInput() {
        //given
        String input = "초코케이크-2,레드와인-1";

        //when
        Map<MenuCatalog, Integer> result = ParserUtil.parseOrder(input);

        //then
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(2, result.get(MenuCatalog.CHOCOLATE_CAKE));
        assertEquals(1, result.get(MenuCatalog.RED_WINE));
    }

    @Test
    @DisplayName("주문 입력 파싱_2")
    void parseValidOrderInput_2() {
        //given
        String input = "초코케이크-10,레드와인-10";

        //when
        Map<MenuCatalog, Integer> result = ParserUtil.parseOrder(input);

        //then
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(10, result.get(MenuCatalog.CHOCOLATE_CAKE));
        assertEquals(10, result.get(MenuCatalog.RED_WINE));
    }

    @Test
    @DisplayName("주문 입력 파싱_3")
    void parseValidOrderInput_3() {
        //given
        String input = "초코케이크-3,레드와인-1,티본스테이크-3,시저샐러드-3";

        //when
        Map<MenuCatalog, Integer> result = ParserUtil.parseOrder(input);

        //then
        assertNotNull(result);
        assertEquals(4, result.size());
        assertEquals(3, result.get(MenuCatalog.CHOCOLATE_CAKE));
        assertEquals(1, result.get(MenuCatalog.RED_WINE));
        assertEquals(3, result.get(MenuCatalog.T_BONE_STEAK));
        assertEquals(3, result.get(MenuCatalog.CAESAR_SALAD));
    }
}


