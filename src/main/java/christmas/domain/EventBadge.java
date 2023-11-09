package christmas.domain;

import java.util.Arrays;
import java.util.Comparator;

public enum EventBadge {
    STAR(5000, "별"),
    TREE(10000, "트리"),
    SANTA(20000, "산타"),
    NONE(0, "없음");

    private final int minimumAmount;
    private final String displayName;

    EventBadge(int minimumAmount, String displayName) {
        this.minimumAmount = minimumAmount;
        this.displayName = displayName;
    }

    public int getMinimumAmount() {
        return minimumAmount;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static String getBadgeByAmount(int amount) {
        return Arrays.stream(EventBadge.values())
                .filter(badge -> amount >= badge.getMinimumAmount())
                .max(Comparator.comparingInt(EventBadge::getMinimumAmount))
                .orElse(NONE)
                .getDisplayName();
    }
}
