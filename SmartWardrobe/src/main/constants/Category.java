package main.constants;

import java.util.ArrayList;
import java.util.List;

public enum Category {
    DRESS("Платья"),
    TROUSERS("Брюки"),
    SHIRT("Рубашки"),
    TSHIRTS("Футболки"),
    SWEATER("Свитера"),
    ACCESSORIES("Аксессуары"),
    OTHER("Другое");

    private String type;

    Category(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public static Category getByType(String type) {
        for (Category c : values()) {
            if (c.getType().equals(type))
                return c;
        }
        return null;
    }

    public static List<String> getCategories() {
        List<String> res = new ArrayList<String>();
        for (Category c : values()) {
            res.add(c.getType());
        }
        return res;
    }

}
