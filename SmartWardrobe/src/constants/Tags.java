package constants;

import java.util.*;

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

	public static List<String> parseString(String s) {
		StringTokenizer st = new StringTokenizer(s, Constants.tagsSeparator);
		List<String> res = new ArrayList<>();
		while (st.hasMoreTokens()) {
			res.add(st.nextToken());
		}
		return res;
	}

	public static String parseToString(Set<String> tags) {
		StringBuilder sb = new StringBuilder();
		for (String tag : tags) {
			sb.append(tag);
			sb.append("; ");
		}
		return sb.toString();
	}
}
