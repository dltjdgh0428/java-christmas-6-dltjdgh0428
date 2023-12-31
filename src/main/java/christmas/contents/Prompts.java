package christmas.contents;

public enum Prompts {
    WELCOME_MESSAGE("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다."),
    ASK_FOR_DATE("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)"),
    ASK_FOR_ORDER("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)"),
    EVENT_BENEFIT_PREVIEW("12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!"),
    ORDER_MENU("<주문 메뉴>"),
    TOTAL_ORDER_AMOUNT_BEFORE_DISCOUNT("<할인 전 총주문 금액>"),
    GIFT_MENU("<증정 메뉴>"),
    BENEFIT_DETAILS("<혜택 내역>"),
    TOTAL_BENEFIT_AMOUNT("<총혜택 금액>"),
    EXPECTED_PAYMENT_AFTER_DISCOUNT("<할인 후 예상 결제 금액>"),
    EVENT_BADGE("<12월 이벤트 배지>"),
    NOTHING("없음"),
    CHAMPAGNE("샴페인 1개"),
    SPACE(""),
    ITEM_SEPARATOR(","),
    QUANTITY_SEPARATOR("-");

    private final String message;

    Prompts(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public String getDayMessage(int day) {
        return String.format(EVENT_BENEFIT_PREVIEW.message, day);
    }
}