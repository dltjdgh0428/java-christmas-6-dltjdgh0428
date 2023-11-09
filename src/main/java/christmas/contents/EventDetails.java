package christmas.contents;

public enum EventDetails {
    MIN_DATE(1),
    MAX_DATE(31);

    private final int value;

    EventDetails(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}