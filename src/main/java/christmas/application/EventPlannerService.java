package christmas.application;

import christmas.domain.Date;
import christmas.domain.EventBadge;
import christmas.domain.Order;
import christmas.dto.DateDTO;

public class EventPlannerService {

    public String determineEventBadge(int totalDiscount) {
        return EventBadge.getBadgeByAmount(totalDiscount);
    }

    public int calculateFinalAmount(Order order, int day) {
        int totalBeforeDiscount = EventBenefitCalculator.calculateTotalBeforeDiscount(order);
        int totalDiscount = EventBenefitCalculator.calculateTotalDiscount(order, day);
        return totalBeforeDiscount - totalDiscount;
    }

    public DateDTO convertToDateDto(Date date) {
        return new DateDTO(date.getDay());
    }

}
