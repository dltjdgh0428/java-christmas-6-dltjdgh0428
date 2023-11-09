package christmas.domain.vo;

import christmas.contents.MenuCatalog;
import christmas.utils.ParserUtil;
import christmas.utils.ValidationUtil;

import java.util.Map;

public class OrderVO {
    private Map<MenuCatalog, Integer> orderItems;

    public OrderVO(String orderInput) {
        ValidationUtil.validateOrderFormat(orderInput);
        this.orderItems = ParserUtil.parseOrder(orderInput);
        ValidationUtil.validateOrder(this.orderItems);
    }

    public Map<MenuCatalog, Integer> getOrder() {
        return orderItems;
    }
}