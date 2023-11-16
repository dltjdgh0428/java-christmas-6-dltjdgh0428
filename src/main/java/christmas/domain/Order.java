package christmas.domain;

import static christmas.contents.ContentNumbers.INITIAL_VALUE;

import christmas.contents.MenuCatalog;
import java.util.LinkedHashMap;
import java.util.Map;

public class Order {
    private Map<MenuCatalog, Integer> orderItems;

    public Order() {
        this.orderItems = new LinkedHashMap<>();
    }

    public void addItem(MenuCatalog item, int quantity) {
        this.orderItems.put(item, this.orderItems.getOrDefault(item, INITIAL_VALUE.getValue()) + quantity);
    }

    public Map<MenuCatalog, Integer> getOrderItems() {
        return orderItems;
    }
}
