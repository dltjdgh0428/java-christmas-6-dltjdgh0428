package christmas.ui;

import christmas.contents.OutputFormat;
import christmas.contents.Prompts;
import christmas.domain.DiscountDetails;
import christmas.dto.OrderDTO;

import java.util.Optional;

public class OutputView {

    public void printWelcomeMessage() {
        System.out.println(Prompts.WELCOME_MESSAGE.getMessage());
    }

    public void printEventBenefitPreview(int day) {
        System.out.println(Prompts.EVENT_BENEFIT_PREVIEW.getDayMessage(day));
        System.out.println(Prompts.SPACE.getMessage());
    }

    public void printOrderMenu(OrderDTO orderDTO) {
        System.out.println(Prompts.ORDER_MENU.getMessage());
        orderDTO.order().entrySet().stream()
                .forEach(entry -> {
                    System.out.println(OutputFormat.getFormattedString(entry.getKey(), entry.getValue()));
                });
        System.out.println(Prompts.SPACE.getMessage());
    }

    public void printTotalOrderAmountBeforeDiscount(int totalBeforeDiscount) {
        System.out.println(Prompts.TOTAL_ORDER_AMOUNT_BEFORE_DISCOUNT.getMessage());
        System.out.println(OutputFormat.getPositiveInt(totalBeforeDiscount));
        System.out.println(Prompts.SPACE.getMessage());
    }

    public void printGiftMenu(boolean giftChampagne) {
        System.out.println(Prompts.GIFT_MENU.getMessage());
        String message = Optional.of(giftChampagne)
                .map(gift -> Prompts.Champagne.getMessage())
                .orElse(Prompts.NOTHING.getMessage());
        System.out.println(message);
        System.out.println(Prompts.SPACE.getMessage());
    }

    public void printBenefitDetails(DiscountDetails discountDetails) {
        System.out.println(Prompts.BENEFIT_DETAILS.getMessage());
        Optional.ofNullable(discountDetails)
                .map(DiscountDetails::getDetails)
                .filter(details -> !details.isEmpty())
                .ifPresentOrElse(
                        details -> details.forEach(System.out::println),
                        () -> System.out.println(Prompts.NOTHING.getMessage())
                );
        System.out.println(Prompts.SPACE.getMessage());
    }

    public void printTotalBenefitAmount(int totalDiscount) {
        System.out.println(Prompts.TOTAL_BENEFIT_AMOUNT.getMessage());
        System.out.println(OutputFormat.getNegativeInt(totalDiscount));
        System.out.println(Prompts.SPACE.getMessage());
    }

    public void printExpectedPaymentAfterDiscount(int payment) {
        System.out.println(Prompts.EXPECTED_PAYMENT_AFTER_DISCOUNT.getMessage());
        System.out.println(OutputFormat.getPositiveInt(payment));
        System.out.println(Prompts.SPACE.getMessage());
    }

    public void printEventBadge(String eventBadge) {
        System.out.println(Prompts.EVENT_BADGE.getMessage());
        System.out.println(eventBadge);
    }
}
