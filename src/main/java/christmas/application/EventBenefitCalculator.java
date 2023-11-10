package christmas.application;

import static christmas.contents.ContentNumbers.MAX_DISCOUNT_DAY;
import static christmas.contents.ContentNumbers.START_DAY_OF_MONTH;
import static christmas.contents.DiscountAmounts.*;

import christmas.domain.Order;
import christmas.utils.DateUtil;

public class EventBenefitCalculator {
    public static int calculateTotalDiscount(Order order, int day) {
        int totalBeforeDiscount = calculateTotalBeforeDiscount(order);

        if (totalBeforeDiscount < MINIMUM_ORDER_AMOUNT_FOR_DISCOUNTS.getAmount()) {
            return NO_DISCOUNT.getAmount();
        }

        int totalDiscount = calculateWeekdayDiscount(order, day)
                + calculateWeekendDiscount(order, day)
                + calculateSpecialDiscount(order, day)
                + calculateChristmasDayDiscount(order, day)
                + calculateGiftEventDiscount(totalBeforeDiscount);

        return totalDiscount;
    }

    public static int calculateWeekdayDiscount(Order order, int day) {
        if (DateUtil.isWeekend(day) || !isEligibleForDiscount(order)) {
            return NO_DISCOUNT.getAmount();
        }
        return order.getOrderItems().entrySet().stream()
                .filter(entry -> entry.getKey().isDessert())
                .mapToInt(entry -> WEEKDAY_DISCOUNT_AMOUNT.getAmount() * entry.getValue())
                .sum();
    }

    public static int calculateWeekendDiscount(Order order, int day) {
        if (!DateUtil.isWeekend(day) || !isEligibleForDiscount(order)) {
            return NO_DISCOUNT.getAmount();
        }
        return order.getOrderItems().entrySet().stream()
                .filter(entry -> entry.getKey().isMain())
                .mapToInt(entry -> WEEKEND_DISCOUNT_AMOUNT.getAmount() * entry.getValue())
                .sum();
    }

    public static int calculateSpecialDiscount(Order order, int day) {
        if (DateUtil.isSpecialDiscountDay(day) || isEligibleForDiscount(order)) {
            return SPECIAL_DISCOUNT_AMOUNT.getAmount();
        }
        return NO_DISCOUNT.getAmount();
    }

    public static int calculateChristmasDayDiscount(Order order, int day) {
        if (day > MAX_DISCOUNT_DAY.getValue() || !isEligibleForDiscount(order)) {
            return NO_DISCOUNT.getAmount();
        }
        return INITIAL_CHRISTMAS_DAY_DISCOUNT.getAmount() + (day - START_DAY_OF_MONTH.getValue()) * DAILY_INCREMENT.getAmount();
    }

    public static int calculateTotalBeforeDiscount(Order order) {
        return order.getOrderItems().entrySet().stream()
                .mapToInt(entry -> entry.getKey().getPrice() * entry.getValue())
                .sum();
    }

    public static int calculateGiftEventDiscount(int totalBeforeDiscount) {
        if (totalBeforeDiscount >= GIFT_EVENT_THRESHOLD.getAmount()) {
            return GIFT_EVENT_DISCOUNT_AMOUNT.getAmount();
        }
        return NO_DISCOUNT.getAmount();
    }

    private static boolean isEligibleForDiscount(Order order) {
        int totalBeforeDiscount = calculateTotalBeforeDiscount(order);
        return totalBeforeDiscount >= MINIMUM_ORDER_AMOUNT_FOR_DISCOUNTS.getAmount();
    }
}
