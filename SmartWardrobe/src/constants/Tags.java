package constants;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Tags implements Serializable {

	private static long idCounter = 0;

	private static long getId() {
		return idCounter++;
	}

	private static Map<String, Long> tags = new HashMap<>();

	public static Long getTagId(String name) {
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
