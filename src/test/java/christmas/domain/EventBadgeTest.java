package christmas.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class EventBadgeTest {
    @Test
    @DisplayName("혜택 금액에 따른 배지 부여 - 산타 배지")
    public void getBadgeByAmount_Santa() {
        // given
        int amount = 25000; // 산타 배지 기준 이상

        // when
        String badge = EventBadge.getBadgeByAmount(amount);

        // then
        assertEquals("산타", badge);
    }

    @Test
    @DisplayName("혜택 금액에 따른 배지 부여 - 별 배지")
    public void getBadgeByAmount_Star() {
        // given
        int amount = 6000; // 별 배지 기준 이상, 트리 배지 기준 미만

        // when
        String badge = EventBadge.getBadgeByAmount(amount);

        // then
        assertEquals("별", badge);
    }

    @Test
    @DisplayName("혜택 금액에 따른 배지 부여 - 트리 배지")
    public void getBadgeByAmount_Tree() {
        // given
        int amount = 12000; // 트리 배지 기준 이상, 산타 배지 기준 미만

        // when
        String badge = EventBadge.getBadgeByAmount(amount);

        // then
        assertEquals("트리", badge);
    }

    @Test
    @DisplayName("혜택 금액에 따른 배지 부여 - 배지 없음")
    public void getBadgeByAmount_None() {
        // given
        int amount = 3000; // 모든 배지 기준 미만

        // when
        String badge = EventBadge.getBadgeByAmount(amount);

        // then
        assertEquals("없음", badge);
    }

    @Test
    @DisplayName("혜택 금액 정확히 별 배지 기준일 때")
    public void getBadgeByAmount_ExactStar() {
        // given
        int amount = 5000; // 별 배지 기준 정확히

        // when
        String badge = EventBadge.getBadgeByAmount(amount);

        // then
        assertEquals("별", badge);
    }

    @Test
    @DisplayName("혜택 금액 정확히 트리 배지 기준일 때")
    public void getBadgeByAmount_ExactTree() {
        // given
        int amount = 10000; // 트리 배지 기준 정확히

        // when
        String badge = EventBadge.getBadgeByAmount(amount);

        // then
        assertEquals("트리", badge);
    }
}
