package christmas.domain.vo;

import christmas.contents.MenuCatalog;
import christmas.utils.ParserUtil;
import christmas.utils.ValidationUtil;
import java.util.Map;

public class OrderVO {
    private Map<MenuCatalog, Integer> orderItems;

    public OrderVO(String orderInput) {
        ValidationUtil.validateOrderBeforeParse(orderInput);
        this.orderItems = ParserUtil.parseOrder(orderInput);
        ValidationUtil.validateOrderAfterParse(this.orderItems);
    }
    public Map<MenuCatalog, Integer> getOrderItems() {
        return orderItems;
    }
}