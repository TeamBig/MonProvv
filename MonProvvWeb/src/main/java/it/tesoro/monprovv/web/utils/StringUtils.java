package it.tesoro.monprovv.web.utils;

public class StringUtils {

	public static boolean isEmpty(Object obj) {
		boolean result = true;
		if (obj != null) {
			result = ((String) obj).trim().equals("");
		} else {
			result = false;
		}
		return result;
	}
	
	public static boolean isNotEmpty(Object obj) {
		return !isEmpty(obj);
	}

}
