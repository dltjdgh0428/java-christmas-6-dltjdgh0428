package christmas.contents;

public enum ContentNumbers {
    INITIAL_VALUE(0),
    EVENT_MIN_DATE(1),
    EVENT_MAX_DATE(31),
    MENU_NAME_INDEX(0),
    MENU_QUANTITY_INDEX(1),
    ORDER_PARTS_COUNT(2),
    MIN_QUANTITY(1),
    MAX_QUANTITY(20),
    YEAR(2023),
    MONTH(12),
    WEEK(7),
    CORRECT_SUNDAY(3),
    START_DAY_OF_MONTH(1),
    CHRISTMAS_DAY(25),
    MAX_DISCOUNT_DAY(25);

    private final int value;

    ContentNumbers(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}