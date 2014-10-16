package it.tesoro.monprovv.web.utils;

public class StringUtils {

	public static boolean isEmpty(Object obj) {
		boolean result = false;
		if (obj != null) {
			result = ((String) obj).trim().equals("");
		} else {
			result = true;
		}
		return result;
	}
	
	public static boolean isNotEmpty(Object obj) {
		return !isEmpty(obj);
	}

}
