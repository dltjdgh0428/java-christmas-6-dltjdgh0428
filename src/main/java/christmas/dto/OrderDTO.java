package christmas.dto;

import christmas.contents.MenuCatalog;
import java.util.Map;

public record OrderDTO(Map<MenuCatalog, Integer> order) {
}
