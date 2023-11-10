package christmas.function;

import static org.junit.jupiter.api.Assertions.assertEquals;

import christmas.application.EventBenefitCalculator;
import christmas.domain.Order;
import christmas.contents.MenuCatalog;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class EventBenefitCalculatorTest {

    @Test
    @DisplayName("10,000원 미만 주문시 할인 적용 안됨")
    public void discountNotAppliedForLowOrder() {
        // given
        Order order = new Order();
        order.addItem(MenuCatalog.ICE_CREAM, 1); // 5,000원
        int day = 15;

        // when
        int totalDiscount = EventBenefitCalculator.calculateTotalDiscount(order, day);

        // then
        assertEquals(0, totalDiscount);
    }

    @Test
    @DisplayName("주말에 메인 메뉴 할인 적용")
    public void applyWeekendDiscountForMain() {
        // given
        Order order = new Order();
        order.addItem(MenuCatalog.T_BONE_STEAK, 1); // 55,000원
        int day = 3; // 주말 일요일

        // when
        int totalDiscount = EventBenefitCalculator.calculateWeekendDiscount(order, day);

        // then
        assertEquals(2023, totalDiscount);
    }

    // 평일에 디저트 메뉴에 대한 할인이 정확히 적용되는지 테스트
    @Test
    @DisplayName("평일 디저트 할인 적용")
    public void applyWeekdayDiscountForDessert() {
        // given
        Order order = new Order();
        order.addItem(MenuCatalog.CHOCOLATE_CAKE, 2); // 총 30,000원 (디저트)
        int day = 4; // 평일

        // when
        int totalDiscount = EventBenefitCalculator.calculateWeekdayDiscount(order, day);

        // then
        assertEquals(4046, totalDiscount); // 2,023원 * 2
    }

    // 특별 할인이 정확히 적용되는지 테스트
    @Test
    @DisplayName("특별 할인 적용")
    public void applySpecialDiscount() {
        // given
        Order order = new Order();
        order.addItem(MenuCatalog.T_BONE_STEAK, 1); // 메인 메뉴
        int day = 25; // 크리스마스 당일

        // when
        int totalDiscount = EventBenefitCalculator.calculateSpecialDiscount(order, day);

        // then
        assertEquals(1000, totalDiscount); // 크리스마스 당일 특별 할인
    }

    // 크리스마스 D-day 할인이 정확히 적용되는지 테스트
    @Test
    @DisplayName("크리스마스 D-day 할인 적용")
    public void applyChristmasDayDiscount() {
        // given
        Order order = new Order();
        order.addItem(MenuCatalog.SEAFOOD_PASTA, 2); // 총 70,000원
        int day = 20; // 12월 20일

        // when
        int totalDiscount = EventBenefitCalculator.calculateChristmasDayDiscount(order, day);

        // then
        assertEquals(2900, totalDiscount); // 1,000 + (20 - 1) * 100
    }

    // 증정 이벤트 할인이 정확히 적용되는지 테스트
    @Test
    @DisplayName("증정 이벤트 할인 적용")
    public void applyGiftEventDiscount() {
        // given
        Order order = new Order();
        order.addItem(MenuCatalog.BBQ_RIBS, 3); // 총 162,000원
        int day = 15; // 평일

        // when
        int totalDiscount = EventBenefitCalculator.calculateGiftEventDiscount(EventBenefitCalculator.calculateTotalBeforeDiscount(order));

        // then
        assertEquals(25000, totalDiscount); // 증정 이벤트 할인
    }

    // 주말에 디저트 메뉴에 대한 할인이 적용되지 않는 경우 테스트
    @Test
    @DisplayName("주말 디저트 할인 적용 안됨")
    public void noDiscountForDessertOnWeekend() {
        // given
        Order order = new Order();
        order.addItem(MenuCatalog.CHOCOLATE_CAKE, 2); // 총 30,000원 (디저트)
        int day = 2; // 주말 (토요일)

        // when
        int totalDiscount = EventBenefitCalculator.calculateWeekdayDiscount(order, day);

        // then
        assertEquals(0, totalDiscount);
    }

    // 평일에 메인 메뉴에 대한 할인이 적용되지 않는 경우 테스트
    @Test
    @DisplayName("평일 메인 메뉴 할인 적용 안됨")
    public void noDiscountForMainOnWeekday() {
        // given
        Order order = new Order();
        order.addItem(MenuCatalog.T_BONE_STEAK, 1); // 55,000원 (메인 메뉴)
        int day = 1; // 평일 (월요일)

        // when
        int totalDiscount = EventBenefitCalculator.calculateWeekendDiscount(order, day);

        // then
        assertEquals(0, totalDiscount);
    }


}
