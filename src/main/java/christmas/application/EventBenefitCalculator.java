package christmas.application;

import static christmas.contents.ContentNumbers.MAX_DISCOUNT_DAY;
import static christmas.contents.ContentNumbers.START_DAY_OF_MONTH;
import static christmas.contents.DiscountAmounts.DAILY_INCREMENT;
import static christmas.contents.DiscountAmounts.GIFT_EVENT_DISCOUNT_AMOUNT;
import static christmas.contents.DiscountAmounts.GIFT_EVENT_THRESHOLD;
import static christmas.contents.DiscountAmounts.INITIAL_CHRISTMAS_DAY_DISCOUNT;
import static christmas.contents.DiscountAmounts.MINIMUM_ORDER_AMOUNT_FOR_DISCOUNTS;
import static christmas.contents.DiscountAmounts.NO_DISCOUNT;
import static christmas.contents.DiscountAmounts.SPECIAL_DISCOUNT_AMOUNT;
import static christmas.contents.DiscountAmounts.WEEKDAY_DISCOUNT_AMOUNT;
import static christmas.contents.DiscountAmounts.WEEKEND_DISCOUNT_AMOUNT;

import christmas.dto.OrderDTO;
import christmas.utils.DateUtil;

public class EventBenefitCalculator {
    public static int calculateTotalDiscount(OrderDTO orderDTO, int day) {
        int totalBeforeDiscount = calculateTotalBeforeDiscount(orderDTO);

        if (totalBeforeDiscount < MINIMUM_ORDER_AMOUNT_FOR_DISCOUNTS.getAmount()) {
            return NO_DISCOUNT.getAmount();
        }

        int totalDiscount = calculateWeekdayDiscount(orderDTO, day)
                + calculateWeekendDiscount(orderDTO, day)
                + calculateSpecialDiscount(orderDTO, day)
                + calculateChristmasDayDiscount(orderDTO, day);

        return totalDiscount;
    }

    public static int calculateTotalBenefit(OrderDTO orderDTO, int day){
        return calculateWeekdayDiscount(orderDTO, day)
                + calculateWeekendDiscount(orderDTO, day)
                + calculateSpecialDiscount(orderDTO, day)
                + calculateChristmasDayDiscount(orderDTO, day)
                + calculateGiftEventDiscount(calculateTotalBeforeDiscount(orderDTO));
    }

    public static int calculateWeekdayDiscount(OrderDTO orderDTO, int day) {
        if (DateUtil.isWeekend(day) || !isEligibleForDiscount(orderDTO)) {
            return NO_DISCOUNT.getAmount();
        }
        return orderDTO.order().entrySet().stream()
                .filter(entry -> entry.getKey().isDessert())
                .mapToInt(entry -> WEEKDAY_DISCOUNT_AMOUNT.getAmount() * entry.getValue())
                .sum();

    }

    public static int calculateWeekendDiscount(OrderDTO orderDTO, int day) {
        if (!DateUtil.isWeekend(day) || !isEligibleForDiscount(orderDTO)) {
            return NO_DISCOUNT.getAmount();
        }
        return orderDTO.order().entrySet().stream()
                .filter(entry -> entry.getKey().isMain())
                .mapToInt(entry -> WEEKEND_DISCOUNT_AMOUNT.getAmount() * entry.getValue())
                .sum();
    }

    public static int calculateSpecialDiscount(OrderDTO orderDTO, int day) {
        if (DateUtil.isSpecialDiscountDay(day) || isEligibleForDiscount(orderDTO)) {
            return SPECIAL_DISCOUNT_AMOUNT.getAmount();
        }
        return NO_DISCOUNT.getAmount();
    }

    public static int calculateChristmasDayDiscount(OrderDTO orderDTO, int day) {
        if (day > MAX_DISCOUNT_DAY.getValue() || !isEligibleForDiscount(orderDTO)) {
            return NO_DISCOUNT.getAmount();
        }
        return INITIAL_CHRISTMAS_DAY_DISCOUNT.getAmount() + (day - START_DAY_OF_MONTH.getValue()) * DAILY_INCREMENT.getAmount();
    }

    public static int calculateTotalBeforeDiscount(OrderDTO orderDTO) {
        return orderDTO.order().entrySet().stream()
                .mapToInt(entry -> entry.getKey().getPrice() * entry.getValue())
                .sum();
    }

    public static int calculateGiftEventDiscount(int totalBeforeDiscount) {
        if (totalBeforeDiscount >= GIFT_EVENT_THRESHOLD.getAmount()) {
            return GIFT_EVENT_DISCOUNT_AMOUNT.getAmount();
        }
        return NO_DISCOUNT.getAmount();
    }

    public static boolean calculateGiftEvent(OrderDTO orderDTO){
        if(calculateTotalBeforeDiscount(orderDTO) >= GIFT_EVENT_THRESHOLD.getAmount()){
            return true;
        }
        return false;
    }

    private static boolean isEligibleForDiscount(OrderDTO orderDTO) {
        int totalBeforeDiscount = calculateTotalBeforeDiscount(orderDTO);
        return totalBeforeDiscount >= MINIMUM_ORDER_AMOUNT_FOR_DISCOUNTS.getAmount();
    }
}
