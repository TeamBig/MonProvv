package it.tesoro.monprovv.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class DateUtil {
	
	public static Date fromStringToDate(String data, String pattern) {
		DateFormat formatter = new SimpleDateFormat(pattern);
		Date date = null;
		if (data == null || data.matches("0*\\.0*\\.0*"))
			return null;
		try {
			date = (Date) formatter.parse(data);
		} catch (ParseException e) {
			//System.out.println(e.getMessage());
		}
		return date;
	}
	public static String fromDateToString(Date data) {
		return fromDateToString(data, "dd-MM-yyyy");
	}
	
	public static String changeStringDateFormat(String date, String srcFormat, String destFormat){
		return fromDateToString(fromStringToDate(date,srcFormat),destFormat);
	}
	public static Date fromStringToDate(String data) {
		return fromStringToDate(data, "yyyy-MM-dd");
	}
	
	public static Date fromDateWithPattern(Date data) {
		String dataRitorno = fromDateToString(data,"yyyy-MM-dd");
		return fromStringToDate(dataRitorno, "yyyy-MM-dd");
	}
	
	public static String fromDateToString(Date data, String pattern) {
		DateFormat df = new SimpleDateFormat(pattern);
		return data != null ? df.format(data) : null;
	}
	
	public static String getYear() {	
		Calendar cal = Calendar.getInstance();
		return Integer.toString(cal.get(Calendar.YEAR));
		 
	}
	
	public static Date getEndOfCurrentYear(Date data) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(data);
		int anno = cal.get(Calendar.YEAR);
		cal.set(anno, 11, 31);
		return cal.getTime();	 
	}
	
	
	/** Restituisce la differenza in giorni tra 2 date passate in input
	 * @param sdate1 Data di partenza  
	 * @param sdate2 Data finale
	 * @param fmt indica il formato della data es: "dd/mm/yyyy"
	 * @param tz  indica il TimeZone. Si può mettere a null
	 * @return
	 */
	public static int giorniDifferenza (String sdate1, String sdate2, String fmt, TimeZone tz){
			
	  if (sdate1.equals("")){
	  
		return 0;
	  
	 }else{ 
					
			SimpleDateFormat df = new SimpleDateFormat(fmt);
			Date date1  = null;
			Date date2  = null;
			try {
				date1 = df.parse(sdate1); 
				date2 = df.parse(sdate2); 
			}catch (ParseException pe){
				pe.printStackTrace();
			}
			Calendar cal1 = null; 
			Calendar cal2 = null;
			if (tz == null){
				cal1=Calendar.getInstance(); 
				cal2=Calendar.getInstance(); 
			}else{
				cal1=Calendar.getInstance(tz); 
				cal2=Calendar.getInstance(tz); 
			}
			// different date might have different offset
			cal1.setTime(date1);          
			long ldate1 = date1.getTime() + cal1.get(Calendar.ZONE_OFFSET) + cal1.get(Calendar.DST_OFFSET);
			cal2.setTime(date2);
			long ldate2 = date2.getTime() + cal2.get(Calendar.ZONE_OFFSET) + cal2.get(Calendar.DST_OFFSET);
			// Use integer calculation, truncate the decimals
			int hr1   = (int)(ldate1/3600000); //60*60*1000
			int hr2   = (int)(ldate2/3600000);
			int days1 = (int)hr1/24;
			int days2 = (int)hr2/24;
			int dateDiff  = days2 - days1;
			int weekOffset = (cal2.get(Calendar.DAY_OF_WEEK) - cal1.get(Calendar.DAY_OF_WEEK))==0 ? 1 : 0;
			int weekDiff  = dateDiff/7 + weekOffset; 
			int yearDiff  = cal2.get(Calendar.YEAR) - cal1.get(Calendar.YEAR); 
			int monthDiff = yearDiff * 12 + cal2.get(Calendar.MONTH) - cal1.get(Calendar.MONTH);

			// RITORNA DIFFERENZA DATE
			return dateDiff;
		}
	}
	
	public static String oraFormat(String ora) {
		int length = ora.length();
		String min = ora.substring(length-2);
		String ore = ora.substring(0, length-2);
		return ore+":"+min;
	}
	
	
	
	/*
	 * Restituisce il giorno precedente in funzione della data passata per parametro
	 */
	public static Date giornoPrecedenteToDate(Date data) {
		Calendar cal = Calendar.getInstance(); 
		cal.setTime(data); 
		cal.add(Calendar.DATE, -1);
		return cal.getTime();
	}
	
	public static Date getStartOfCurrentYear(Date data) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(data);
		int anno = cal.get(Calendar.YEAR);
		cal.set(anno, 00, 01);
		return cal.getTime();	 
	}
	
	public static String getStartOfCurrentYear(Date data,String pattern) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(data);
		int anno = cal.get(Calendar.YEAR);
		cal.set(anno, 00, 01);
		return fromDateToString(cal.getTime(), pattern);	 
	}	
	
	public static String getStartOfYearByDate(Date data,String pattern) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(data);
		int anno = cal.get(Calendar.YEAR);
		cal.set(anno, 00, 01);
		return fromDateToString(cal.getTime(), pattern);	 
	}	
	
	public static Date getEndOfCurrentYear() {
		Date data = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(data);
		int anno = cal.get(Calendar.YEAR);
		cal.set(anno, 11, 31);
		return cal.getTime();	 
	}	
	
	public static Integer getYearFromDate(Date data) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(data);
		int anno = cal.get(Calendar.YEAR);
		return anno;
	}
	
	public static Integer getDayFromDate(Date data) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(data);
		int day = cal.get(Calendar.DAY_OF_MONTH);
		return day;
	}
	
	public static Integer getMonthFromDate(Date data) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(data);
		int month = cal.get(Calendar.MONTH);
		return month;
	}
	
	public static String getHour() {	
		Calendar cal = Calendar.getInstance();
		return Integer.toString(cal.get(Calendar.HOUR));
		 
	}
	
	public static int convertToJulian(Date date){
	 Calendar calendar = Calendar.getInstance();
		 calendar.setTime(date);
		 int year = calendar.get(Calendar.YEAR);
		 String syear = String.format("%04d",year).substring(2);
		 int century = Integer.parseInt(String.valueOf(((year / 100)+1)).substring(1));
		 int julian = Integer.parseInt(String.format("%d%s%03d",century,syear,calendar.get(Calendar.DAY_OF_YEAR)));
		 return julian;
		}
	
	public static Date julianToDate(double jd) {

	    double z, f, a, b, c, d, e, m, aux;
	    Date date = new Date();
	    jd += 0.5;
	    z = Math.floor(jd);
	    f = jd - z;

	    if (z >= 2299161.0) {
	      a = Math.floor((z - 1867216.25) / 36524.25);
	      a = z + 1 + a - Math.floor(a / 4);
	    } else {
	      a = z;
	    }

	    b = a + 1524;
	    c = Math.floor((b - 122.1) / 365.25);
	    d = Math.floor(365.25 * c);
	    e = Math.floor((b - d) / 30.6001);
	    aux = b - d - Math.floor(30.6001 * e) + f;

	    Calendar calendar = new GregorianCalendar();
	    calendar.setTime(date);
	    calendar.set(Calendar.DAY_OF_MONTH, (int) aux);
	    aux = ((aux - calendar.get(Calendar.DAY_OF_MONTH)) * 24);
	    calendar.set(Calendar.HOUR_OF_DAY, (int) aux);
	    calendar.set(Calendar.MINUTE, (int) ((aux - calendar.get(Calendar.HOUR_OF_DAY)) * 60));

	    if (e < 13.5) {
	      m = e - 1;
	    } else {
	      m = e - 13;
	    }
	    // Se le resta uno al mes por el manejo de JAVA, donde los meses empiezan en 0.
	    calendar.set(Calendar.MONTH, (int) m - 1);
	    if (m > 2.5) {
	      calendar.set(Calendar.YEAR, (int) (c - 4716));
	    } else {
	      calendar.set(Calendar.YEAR, (int) (c - 4715));
	    }
	    return calendar.getTime();
	  }
	
	public static Date getFirstDayOfCurrentMonth() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		int anno = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH);
		cal.set(anno, month, 1);
		return cal.getTime();	 
	}	
	
	public static String getDate() {
	
		Calendar cal = Calendar.getInstance();
		String mese		= Integer.toString(cal.get(Calendar.MONTH)+1);
		if ( mese.length() == 1 )
			mese = "0" + mese;
		
		String giorno	= Integer.toString(cal.get(Calendar.DAY_OF_MONTH));
		if ( giorno.length() == 1 )
			giorno = "0" + giorno;	
		
		String anno		= Integer.toString(cal.get(Calendar.YEAR));	
		return ( anno + "-" + mese + "-" + giorno );
	}
	
	//Ritorno la data con settata l'ora della fine del giorno corrente per query su db
	public static Date getDateWithEndHour(Date date){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY,23);
		cal.set(Calendar.MINUTE,59);
		cal.set(Calendar.SECOND,0);
		cal.set(Calendar.MILLISECOND,0);
		return cal.getTime();
	}
	
	public static Date getDateWithStartHour(Date date){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY,0);
		cal.set(Calendar.MINUTE,0);
		cal.set(Calendar.SECOND,0);
		cal.set(Calendar.MILLISECOND,0);
		return cal.getTime();
	}
	
	public static boolean isThisDateValid(String dateToValidate){
		 
		if(dateToValidate == null){
			return false;
		}
 
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setLenient(false);
 
		try {
 
			//if not valid, it will throw ParseException
			Date date = sdf.parse(dateToValidate);
			System.out.println(date);
 
		} catch (ParseException e) {
 
			e.printStackTrace();
			return false;
		}
 
		return true;
	}
	
	public static Date getEndOfCurrentYearBeforeMidnight() {
		Date data = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(data);
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		int anno = cal.get(Calendar.YEAR);
		return cal.getTime();	 
	}
	
	public static Date getEndOfDateBeforeMidnight(Date data) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(data);
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		int anno = cal.get(Calendar.YEAR);
		return cal.getTime();	 
	}
	
	public static String addSecondi(int secondi){
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
	    calendar.add(Calendar.SECOND, secondi);
	    Date addSeconds = calendar.getTime();
	    String oraAggiuntaSec = format.format(addSeconds);
	    return oraAggiuntaSec;
	}
	
	public static Date addSecondiReturnDate(Date data, int secondi){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(data);
		//SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
	    calendar.add(Calendar.SECOND, secondi);
	    Date addSeconds = calendar.getTime();
	    return addSeconds;
	}
	
	public static String formatOraToDate(Date data){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(data);
		SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
	    String oraAggiuntaSec = format.format(calendar.getTime());
	    return oraAggiuntaSec;
	}
	
	//inserimento nuovo metodo
	public static Date setData(int giorno, int mese, int anno){
		Calendar cal = Calendar.getInstance();
		cal.set(anno, mese-1, giorno, 0,0,0);
		return cal.getTime();	
	}
	
	public static boolean isBisestile(int anno){
		if(anno%4==0)
			return true;
		else
			return false;
	}
	
	
	public static Date getDataFineInfinita() {
		GregorianCalendar cal = new GregorianCalendar(2999 + 1900, 12, 31, 23,
				59, 59);
		Date dataFineInfinita = new Date(cal.getTimeInMillis());
		return dataFineInfinita;
	}
	
	
	public static Date getEndOfYearDate(int anno) {
		Calendar cal = Calendar.getInstance();
		cal.set(anno, 11, 31);
		return cal.getTime();	 
	}
	
	public static Date getFirstOfYearDate(int anno) {
		Calendar cal = Calendar.getInstance();
		cal.set(anno, 00, 01);
		return cal.getTime();	 
	}
	
	public static String fromDateToStringWithHour(Date data) {
		return fromDateToString(data, "dd-MM-yyyy HH:mm:ss");
	}
	
}
