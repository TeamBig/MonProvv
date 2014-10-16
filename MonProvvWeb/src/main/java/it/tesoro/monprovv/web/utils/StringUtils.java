package it.tesoro.monprovv.web.utils;

import java.util.Collection;

public class StringUtils {

	public static boolean isEmpty(Object obj) {
		boolean result = obj == null;

		if (!result) {
			if (obj instanceof String) {
				result = ((String) obj).length() == 0;
			} else if (obj instanceof Collection<?>) {
				result = ((Collection<?>) obj).isEmpty();
			}
		}

		return result;
	}
	
	public static boolean isNotEmpty(Object obj) {
		return !isEmpty(obj);
	}

}
