package christmas.contents;
public enum DiscountAmounts {
    INITIAL_CHRISTMAS_DAY_DISCOUNT(1000),
    DAILY_INCREMENT(100),
    WEEKDAY_DISCOUNT_AMOUNT(2023),
    WEEKEND_DISCOUNT_AMOUNT(2023),
    SPECIAL_DISCOUNT_AMOUNT(1000),
    GIFT_EVENT_DISCOUNT_AMOUNT(25_000),
    GIFT_EVENT_THRESHOLD(120_000),
    MINIMUM_ORDER_AMOUNT_FOR_DISCOUNTS(10_000),
    NO_DISCOUNT(0);

    private final int amount;

    DiscountAmounts(int amount) {
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }
}
