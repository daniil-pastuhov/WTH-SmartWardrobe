package constants;

import java.util.HashMap;
import java.util.Map;

public class Tags {

	private static long idCounter = 0;

	private static long getId() {
		return idCounter++;
	}

	private static Map<String, Long> tags = new HashMap<String, Long>();

	public static Long getTagId(String name) {
		return tags.get(name);
	}

    public static Long getOrCreateTagId(String name) {
        if (!containsTag(name)) {
            addTag(name);
        }
        return tags.get(name);
    }

	public static boolean containsTag(String name) {
		return tags.containsKey(name);
	}

	public static void addTag(String name) {
		tags.put(name, getId());
	}

	public static void deleteTag(String name) {
		tags.remove(name);
	}
}
