package christmas.contents;

public enum ContentNumbers {
    INITIAL_VALUE(0),
    EVENT_MIN_DATE(1),
    EVENT_MAX_DATE(31),
    MENU_NAME_INDEX(0),
    MENU_QUANTITY_INDEX(1),
    ORDER_PARTS_COUNT(2),
    MIN_QUANTITY(1),
    MAX_QUANTITY(20);

    private final int value;

    ContentNumbers(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}