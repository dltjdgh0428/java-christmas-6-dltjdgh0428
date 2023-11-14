package christmas.contents;

public enum OutputFormat {
    ORDER_MENU_FORMAT("%s %d개"),
    POSITIVE_INT("%,d원"),
    NEGATIVE_INT("-%,d원");

    private final String format;

    OutputFormat(String format) {
        this.format = format;
    }

    public static String getFormattedString(MenuCatalog menuName, int quantity) {
        return String.format(ORDER_MENU_FORMAT.format, menuName.getName(), quantity);
    }

    public static String getPositiveInt(int formatInt) {
        return String.format(POSITIVE_INT.format, formatInt);
    }
    public static String getNegativeInt(int formatInt){
        return String.format(NEGATIVE_INT.format, formatInt);
    }
}
