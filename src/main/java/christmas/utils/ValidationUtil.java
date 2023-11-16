package christmas.utils;

import static christmas.contents.ContentNumbers.MAX_QUANTITY;
import static christmas.contents.ContentNumbers.MENU_NAME_INDEX;
import static christmas.contents.ContentNumbers.MIN_QUANTITY;
import static christmas.contents.ContentNumbers.ORDER_PARTS_COUNT;
import static christmas.contents.ErrorMessages.INVALID_DATE;
import static christmas.contents.ErrorMessages.INVALID_ORDER;
import static christmas.contents.Prompts.ITEM_SEPARATOR;
import static christmas.contents.Prompts.QUANTITY_SEPARATOR;

import christmas.contents.ContentNumbers;
import christmas.contents.MenuCatalog;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ValidationUtil {
    //입력 String이 1~31의 값을 갖는지 확인하는 REGEX입니다
    public static final String DATE_REGEX = "^(3[01]|[12][0-9]|0?[1-9])$";

    public static void validateDateBeforeParse(String inputDate) {
        if (!inputDate.matches(DATE_REGEX)) {
            throw new IllegalArgumentException(INVALID_DATE.getMessage());
        }
    }

    public static void validateDateAfterParse(int date) {
        if (date < ContentNumbers.EVENT_MIN_DATE.getValue() || date > ContentNumbers.EVENT_MAX_DATE.getValue()) {
            throw new IllegalArgumentException(INVALID_DATE.getMessage());
        }
    }

    public static void validateOrderBeforeParse(String inputOrder) {
        validateOrderFormat(inputOrder);
        validateDuplicateMenu(inputOrder);
        validateOrderFormat(inputOrder);
    }

    public static void validateOrderFormat(String inputOrder) {
        Arrays.stream(inputOrder.split(ITEM_SEPARATOR.getMessage()))
                .map(item -> item.split(QUANTITY_SEPARATOR.getMessage()))
                .forEach(parts -> {
                    if (parts.length != ORDER_PARTS_COUNT.getValue() || !isNumeric(parts[1].trim())) {
                        throw new IllegalArgumentException(INVALID_ORDER.getMessage());
                    }
                });
    }

    public static void validateDuplicateMenu(String orderInput) {
        Map<MenuCatalog, Long> menuFrequency = Stream.of(orderInput.split(ITEM_SEPARATOR.getMessage()))
                .map(item -> item.split(QUANTITY_SEPARATOR.getMessage()))
                .filter(parts -> parts.length == ORDER_PARTS_COUNT.getValue())
                .map(parts -> MenuCatalog.fromString(parts[MENU_NAME_INDEX.getValue()].trim()))
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        boolean hasDuplicates = menuFrequency.values().stream().anyMatch(count -> count > 1);
        if (hasDuplicates) {
            throw new IllegalArgumentException(INVALID_ORDER.getMessage());
        }
    }

    public static void validateOrderAfterParse(Map<MenuCatalog, Integer> orderMap) {
        validateNonExistentMenu(orderMap);
        validateQuantityRange(orderMap);
        validateDuplicateMenu(orderMap);
        validateDrinksOnlyOrder(orderMap);
        validateTotalOrderQuantity(orderMap);
    }

    public static void validateNonExistentMenu(Map<MenuCatalog, Integer> orderMap) {
        if (orderMap.containsKey(MenuCatalog.NONE)) {
            throw new IllegalArgumentException(INVALID_ORDER.getMessage());
        }
    }

    public static void validateQuantityRange(Map<MenuCatalog, Integer> orderMap) {
        orderMap.values().forEach(quantity -> {
            if (quantity < MIN_QUANTITY.getValue() || quantity > MAX_QUANTITY.getValue()) {
                throw new IllegalArgumentException(INVALID_ORDER.getMessage());
            }
        });
    }


    public static void validateTotalOrderQuantity(Map<MenuCatalog, Integer> orderMap) {
        int totalQuantity = orderMap.values().stream().mapToInt(Integer::intValue).sum();
        if (totalQuantity > MAX_QUANTITY.getValue()) {
            throw new IllegalArgumentException(INVALID_ORDER.getMessage());
        }
    }


    public static void validateDuplicateMenu(Map<MenuCatalog, Integer> orderMap) {
        List<MenuCatalog> originalOrderList = new ArrayList<>(orderMap.keySet());
        Set<MenuCatalog> uniqueMenus = new HashSet<>(originalOrderList);
        if (originalOrderList.size() != uniqueMenus.size()) {
            throw new IllegalArgumentException(INVALID_ORDER.getMessage());
        }
    }

    public static void validateDrinksOnlyOrder(Map<MenuCatalog, Integer> orderMap) {
        boolean onlyDrinks = orderMap.keySet().stream()
                .allMatch(MenuCatalog::isDrink);
        if (onlyDrinks) {
            throw new IllegalArgumentException(INVALID_ORDER.getMessage());
        }
    }

    private static boolean isNumeric(String strNum) {
        try {
            Integer.parseInt(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}
