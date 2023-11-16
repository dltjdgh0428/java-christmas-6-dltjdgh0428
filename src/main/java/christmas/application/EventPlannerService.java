package christmas.application;

import static christmas.contents.ContentNumbers.INITIAL_VALUE;
import static christmas.contents.DiscountAmounts.NO_DISCOUNT;

import christmas.domain.DiscountDetails;
import christmas.domain.DiscountPolicy;
import christmas.domain.EventBadge;
import christmas.domain.vo.DateVO;
import christmas.domain.vo.OrderVO;
import christmas.dto.DateDTO;
import christmas.dto.OrderDTO;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;


public class EventPlannerService {
    private List<Integer> calculateDiscountAmounts(OrderDTO orderDTO, int day) {
        int totalBeforeDiscount = EventBenefitCalculator.calculateTotalBeforeDiscount(orderDTO);

        if (totalBeforeDiscount <= NO_DISCOUNT.getAmount()) {
            return Collections.emptyList();
        }

        return calculateAllDiscounts(orderDTO, day, totalBeforeDiscount);
    }

    private List<Integer> calculateAllDiscounts(OrderDTO orderDTO, int day, int totalBeforeDiscount) {
        List<Integer> discounts = new ArrayList<>();

        discounts.add(EventBenefitCalculator.calculateChristmasDayDiscount(orderDTO, day));
        discounts.add(EventBenefitCalculator.calculateWeekdayDiscount(orderDTO, day));
        discounts.add(EventBenefitCalculator.calculateWeekendDiscount(orderDTO, day));
        discounts.add(EventBenefitCalculator.calculateSpecialDiscount(orderDTO, day));
        discounts.add(EventBenefitCalculator.calculateGiftEventDiscount(totalBeforeDiscount));

        return discounts;
    }

    public DiscountDetails calculateDiscountDetails(OrderDTO orderDTO, int day) {
        DiscountDetails discountDetails = new DiscountDetails();
        List<Integer> discountAmounts = calculateDiscountAmounts(orderDTO, day);

        IntStream.range(INITIAL_VALUE.getValue(), discountAmounts.size())
                .filter(i -> discountAmounts.get(i) > INITIAL_VALUE.getValue())
                .forEach(i -> {
                    DiscountPolicy policy = DiscountPolicy.values()[i];
                    String detail = policy.getFormattedDescription(discountAmounts.get(i));
                    discountDetails.addDetail(detail);
                });

        return discountDetails;
    }

    public boolean determineGiftEvent(OrderDTO orderDTO) {
        if (EventBenefitCalculator.calculateGiftEvent(orderDTO)) {
            return true;
        }
        return false;
    }

    public String determineEventBadge(OrderDTO orderDTO, int day) {
        int totalDiscount = EventBenefitCalculator.calculateTotalBenefit(orderDTO, day);
        return EventBadge.getBadgeByAmount(totalDiscount);
    }

    public int calculateFinalAmount(OrderDTO orderDTO, int day) {
        int totalBeforeDiscount = EventBenefitCalculator.calculateTotalBeforeDiscount(orderDTO);
        int totalDiscount = EventBenefitCalculator.calculateTotalDiscount(orderDTO, day);
        return totalBeforeDiscount - totalDiscount;
    }

    public int totalBeforeDiscount(OrderDTO orderDTO) {
        return EventBenefitCalculator.calculateTotalBeforeDiscount(orderDTO);
    }

    public int calculateTotalBenefit(OrderDTO orderDTO, int day) {
        return EventBenefitCalculator.calculateTotalBenefit(orderDTO, day);
    }

    public DateDTO convertToDateDto(DateVO date) {
        return new DateDTO(date.getDate());
    }

    public OrderDTO convertToOrderDTO(OrderVO order) {
        return new OrderDTO(order.getOrderItems());
    }
}
