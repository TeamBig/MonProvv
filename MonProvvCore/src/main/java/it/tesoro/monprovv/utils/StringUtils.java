package it.tesoro.monprovv.utils;

import java.io.IOException;
import java.sql.Clob;
import java.sql.SQLException;
import java.util.Collection;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import org.apache.commons.io.IOUtils;

public class StringUtils {

	public static boolean mailSyntaxCheck(String email){
		// Create the Pattern using the regex
		Pattern p = Pattern.compile(".+@.+\\.[a-z]+");

		// Match the given string with the pattern
		Matcher m = p.matcher(email);

		// check whether match is found
		boolean matchFound = m.matches();

		StringTokenizer st = new StringTokenizer(email, ".");
		String lastToken = null;
		while (st.hasMoreTokens()) {
			lastToken = st.nextToken();
		}

		// validate the country code
		if (matchFound && lastToken.length() >= 2
				&& email.length() - 1 != lastToken.length()) {

			return false;
		} else {
			return true;
		}

	}
	
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
