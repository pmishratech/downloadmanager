package com.download.utilities;

import java.util.Collection;
import java.util.Map;

public final class Utility {

	public static boolean isEmpty(final Map<?, ?> map) {
		return map == null || map.isEmpty();
	}

	public static boolean isNotEmpty(final Map<?, ?> map) {
		return !isEmpty(map);
	}

	public static boolean isEmpty(final Collection<?> collection) {
		return collection == null || collection.isEmpty();
	}

	public static boolean isNotEmpty(final Collection<?> collection) {
		return !isEmpty(collection);
	}

	public static boolean isEmpty(final String str) {
		return str == null || str.trim().length() == 0;
	}

	public static boolean isNotEmpty(final String str) {
		return !isEmpty(str);
	}

}
