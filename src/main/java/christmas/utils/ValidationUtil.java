package christmas.utils;

import static christmas.contents.ContentNumbers.*;
import static christmas.contents.ErrorMessages.*;
import static christmas.contents.Prompts.ITEM_SEPARATOR;
import static christmas.contents.Prompts.QUANTITY_SEPARATOR;

import christmas.contents.ContentNumbers;
import christmas.contents.MenuCatalog;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;

public class ValidationUtil {
    //입력 String이 1~31의 값을 갖는지 확인하는 REGEX입니다
    public static final String DATE_REGEX = "^(3[01]|[12][0-9]|0?[1-9])$";

    public static void validateDateFormat(String inputDate) {
        if (!inputDate.matches(DATE_REGEX)) {
            throw new IllegalArgumentException(INVALID_DATE.getMessage());
        }
    }

    public static void validateDate(int date) {
        if (date < ContentNumbers.EVENT_MIN_DATE.getValue() || date > ContentNumbers.EVENT_MAX_DATE.getValue()) {
            throw new IllegalArgumentException(INVALID_DATE.getMessage());
        }
    }

    public static void validateOrderFormat(String orderInput) {
        Arrays.stream(orderInput.split(ITEM_SEPARATOR.getMessage()))
                .map(item -> item.split(QUANTITY_SEPARATOR.getMessage()))
                .peek(parts -> {
                    if (parts.length != ORDER_PARTS_COUNT.getValue()) {
                        throw new IllegalArgumentException(INVALID_ORDER.getMessage());
                    }
                });
    }

    public static void validateOrder(Map<MenuCatalog, Integer> orderMap) {
        validateNonExistentMenu(orderMap);
        validateQuantityRange(orderMap);
        validateDuplicateMenu(orderMap);
        validateDrinksOnlyOrder(orderMap);
    }

    private static void validateNonExistentMenu(Map<MenuCatalog, Integer> orderMap) {
        if (orderMap.containsKey(MenuCatalog.NONE)) {
            throw new IllegalArgumentException(INVALID_ORDER.getMessage());
        }
    }

    private static void validateQuantityRange(Map<MenuCatalog, Integer> orderMap) {
        orderMap.values().forEach(quantity -> {
            if (quantity < MIN_QUANTITY.getValue() || quantity > MAX_QUANTITY.getValue()) {
                throw new IllegalArgumentException(INVALID_ORDER.getMessage());
            }
        });
    }

    private static void validateDuplicateMenu(Map<MenuCatalog, Integer> orderMap) {
        if (orderMap.size() != new HashSet<>(orderMap.keySet()).size()) {
            throw new IllegalArgumentException(INVALID_ORDER.getMessage());
        }
    }

    private static void validateDrinksOnlyOrder(Map<MenuCatalog, Integer> orderMap) {
        boolean onlyDrinks = orderMap.keySet().stream()
                .allMatch(item -> item == MenuCatalog.ZERO_COLA || item == MenuCatalog.RED_WINE || item == MenuCatalog.CHAMPAGNE);
        if (onlyDrinks) {
            throw new IllegalArgumentException(DRINKS_ONLY_ORDER.getMessage());
        }
    }
}
