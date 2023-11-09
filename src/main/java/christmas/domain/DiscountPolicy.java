package christmas.domain;

public enum DiscountPolicy {
    CHRISTMAS_DAY_DISCOUNT("크리스마스 디데이 할인: -%d원"),
    WEEKDAY_DISCOUNT("평일 할인: -%d원"),
    SPECIAL_DISCOUNT("특별 할인: -%d원"),
    GIFT_EVENT_DISCOUNT("증정 이벤트: -%d원");

    private final String format;

    DiscountPolicy(String format) {
        this.format = format;
    }

    public String getFormattedDescription(int discountAmount) {
        return String.format(format, discountAmount);
    }
}
