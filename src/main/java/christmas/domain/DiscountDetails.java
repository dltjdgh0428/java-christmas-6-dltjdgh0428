package christmas.domain;

import java.util.ArrayList;
import java.util.List;

public class DiscountDetails {
    private final List<String> details;

    public DiscountDetails() {
        this.details = new ArrayList<>();
    }

    public void addDetail(String detail) {
        details.add(detail);
    }
    public List<String> getDetails() {
        return new ArrayList<>(details);
    }
}
