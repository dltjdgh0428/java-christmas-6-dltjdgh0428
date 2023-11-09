package christmas.ui;

import christmas.contents.Prompts;

public class OutputView {
    public void printWelcomeMessage() {
        System.out.println(Prompts.WELCOME_MESSAGE.getMessage());
    }

    public void printEventBenefitPreview() {
        System.out.println(Prompts.EVENT_BENEFIT_PREVIEW.getMessage());
    }

    public void printOrderMenu() {
        System.out.println(Prompts.ORDER_MENU.getMessage());
    }

    public void printTotalOrderAmountBeforeDiscount() {
        System.out.println(Prompts.TOTAL_ORDER_AMOUNT_BEFORE_DISCOUNT.getMessage());
    }

    public void printGiftMenu() {
        System.out.println(Prompts.GIFT_MENU.getMessage());
    }

    public void printBenefitDetails() {
        System.out.println(Prompts.BENEFIT_DETAILS.getMessage());
    }

    public void printTotalBenefitAmount() {
        System.out.println(Prompts.TOTAL_BENEFIT_AMOUNT.getMessage());
    }

    public void printExpectedPaymentAfterDiscount() {
        System.out.println(Prompts.EXPECTED_PAYMENT_AFTER_DISCOUNT.getMessage());
    }

    public void printEventBadge() {
        System.out.println(Prompts.EVENT_BADGE.getMessage());
    }
}
