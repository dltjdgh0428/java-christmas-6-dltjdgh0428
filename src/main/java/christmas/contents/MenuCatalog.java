package christmas.contents;

public enum MenuCatalog {
    MUSHROOM_SOUP("양송이수프", 6000, MenuType.MAIN),
    TAPAS("타파스", 5500, MenuType.MAIN),
    CAESAR_SALAD("시저샐러드", 8000, MenuType.MAIN),
    T_BONE_STEAK("티본스테이크", 55_000, MenuType.MAIN),
    BBQ_RIBS("바비큐립", 54_000, MenuType.MAIN),
    SEAFOOD_PASTA("해산물파스타", 35_000, MenuType.MAIN),
    CHRISTMAS_PASTA("크리스마스파스타", 25_000, MenuType.MAIN),
    CHOCOLATE_CAKE("초코케이크", 15_000, MenuType.DESSERT),
    ICE_CREAM("아이스크림", 5000, MenuType.DESSERT),
    ZERO_COLA("제로콜라", 3000, MenuType.DRINK),
    RED_WINE("레드와인", 60_000, MenuType.DRINK),
    CHAMPAGNE("샴페인", 25_000, MenuType.DRINK),
    NONE("None", 0, MenuType.MAIN); // NONE의 유형은 상황에 따라 조정이 필요할 수 있습니다.

    private final String name;
    private final int price;
    private final MenuType type;

    MenuCatalog(String name, int price, MenuType type) {
        this.name = name;
        this.price = price;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public boolean isMain() {
        return this.type == MenuType.MAIN;
    }

    public boolean isDessert() {
        return this.type == MenuType.DESSERT;
    }

    public boolean isDrink() {
        return this.type == MenuType.DRINK;
    }

    public static MenuCatalog fromString(String text) {
        for (MenuCatalog item : MenuCatalog.values()) {
            if (item.name.equalsIgnoreCase(text)) {
                return item;
            }
        }
        return NONE;
    }
}
