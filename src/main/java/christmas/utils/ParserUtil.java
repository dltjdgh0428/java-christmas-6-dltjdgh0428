package christmas.utils;

import static christmas.contents.ContentNumbers.ORDER_PARTS_COUNT;
import static christmas.contents.ContentNumbers.MENU_NAME_INDEX;
import static christmas.contents.ContentNumbers.MENU_QUANTITY_INDEX;
import static christmas.contents.Prompts.ITEM_SEPARATOR;
import static christmas.contents.Prompts.QUANTITY_SEPARATOR;

import christmas.contents.MenuCatalog;
import java.util.AbstractMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ParserUtil {
    public static int parseDate(String input) {
        return Integer.parseInt(input);
    }

    public static Map<MenuCatalog, Integer> parseOrder(String orderInput) {
        return Stream.of(orderInput.split(ITEM_SEPARATOR.getMessage()))
                .map(item -> item.split(QUANTITY_SEPARATOR.getMessage()))
                .filter(parts -> parts.length == ORDER_PARTS_COUNT.getValue())
                .map(parts -> new AbstractMap.SimpleEntry<>(
                        MenuCatalog.fromString(parts[MENU_NAME_INDEX.getValue()].trim()),
                        Integer.parseInt(parts[MENU_QUANTITY_INDEX.getValue()].trim())))
                .collect(Collectors.toList())
                .stream()
                .collect(Collectors.toMap(
                        AbstractMap.SimpleEntry::getKey,
                        AbstractMap.SimpleEntry::getValue,
                        (a, b) -> a));
    }
}
