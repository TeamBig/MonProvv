package it.tesoro.monprovv.web.utils;

import java.io.IOException;
import java.sql.Clob;
import java.sql.SQLException;
import java.util.Collection;

import org.apache.commons.io.IOUtils;

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

	public static String convertClobToString(Clob cl) throws IOException, SQLException {
		if (cl != null) {
			return IOUtils.toString(cl.getCharacterStream());
		}
		return null;
	}
	
	@SuppressWarnings("null")
	public static Clob convertStringToClob(String str) throws SQLException{
		Clob clob = null;
		clob.setString(1, str);
		return clob;
	}
	
	public static String convertBytesToKb(long bytes, boolean si) {
	    int unit = si ? 1000 : 1024;
	    if (bytes < unit) return bytes + " B";
	    int exp = (int) (Math.log(bytes) / Math.log(unit));
	    String pre = (si ? "kMGTPE" : "KMGTPE").charAt(exp-1) + (si ? "" : "i");
	    return String.format("%.1f %sB", bytes / Math.pow(unit, exp), pre);
	}

}
