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
import java.util.List;
import java.util.stream.IntStream;


public class EventPlannerService {

    private List<Integer> calculateDiscountAmounts(OrderDTO orderDTO, int day) {
        List<Integer> discountAmounts = new ArrayList<>();
        int totalBeforeDiscount = EventBenefitCalculator.calculateTotalBeforeDiscount(orderDTO);

        if (totalBeforeDiscount <= NO_DISCOUNT.getAmount()) {
            return discountAmounts;
        }

        discountAmounts.add(EventBenefitCalculator.calculateChristmasDayDiscount(orderDTO, day));//크리스마스
        discountAmounts.add(EventBenefitCalculator.calculateWeekdayDiscount(orderDTO, day));//평일
        discountAmounts.add(EventBenefitCalculator.calculateWeekendDiscount(orderDTO, day));//주말
        discountAmounts.add(EventBenefitCalculator.calculateSpecialDiscount(orderDTO, day));//특별
        discountAmounts.add(EventBenefitCalculator.calculateGiftEventDiscount(totalBeforeDiscount));//증정

        return discountAmounts;
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
    public boolean determineGiftEvent(OrderDTO orderDTO){
        if(EventBenefitCalculator.calculateGiftEvent(orderDTO)){
            return true;
        }
        return false;
    }
    public String determineEventBadge(OrderDTO orderDTO, int day) {
        int totalDiscount = EventBenefitCalculator.calculateTotalDiscount(orderDTO, day);
        return EventBadge.getBadgeByAmount(totalDiscount);
    }

    public int calculateFinalAmount(OrderDTO orderDTO, int day) {
        int totalBeforeDiscount = EventBenefitCalculator.calculateTotalBeforeDiscount(orderDTO);
        int totalDiscount = EventBenefitCalculator.calculateTotalDiscount(orderDTO, day);
        return totalBeforeDiscount - totalDiscount;
    }
    public int totalDiscount(OrderDTO orderDTO, int day){
        return EventBenefitCalculator.calculateTotalDiscount(orderDTO, day);
    }
    public int totalBeforeDiscount(OrderDTO orderDTO){
        return EventBenefitCalculator.calculateTotalBeforeDiscount(orderDTO);
    }

    public DateDTO convertToDateDto(DateVO date) {
        return new DateDTO(date.getDate());
    }
    public OrderDTO convertToOrderDTO(OrderVO order) {
        return new OrderDTO(order.getOrderItems());
    }
}
