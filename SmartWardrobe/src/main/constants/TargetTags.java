package main.constants;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nastassia on 19.10.2014.
 */
public enum TargetTags {
    WORK("На работу"),
    UNIVERSITY("В унтверситет"),
    SCHOOL("В школу"),
    TRAINING("На тренировку"),
    THEATRE("В театр"),
    WALK("На прогулку"),
    DATE("На свидание"),
    PICKNIK("На природу");

    private String type;

    TargetTags(String type) {
        this.type = type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public static TargetTags getByType(String type) {
        for (TargetTags c : values()) {
            if (c.getType().equals(type))
                return c;
        }
        return null;
    }

    public static List<String> getCategories() {
        List<String> res = new ArrayList<String>();
        for (TargetTags c : values()) {
            res.add(c.getType());
        }
        return res;
    }
}
