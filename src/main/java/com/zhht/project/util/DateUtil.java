package com.zhht.project.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author Administrator
 *
 */
public class DateUtil {

	private static final String HourStr = "小时";
	private static final String MinuteStr = "分钟";
	private static final String SecondsStr = "秒";
	private static final String DayStr="天";

	/**
	 * 获取停车时长
	 * 
	 * @param minutes
	 * @return
	 */
	public static String getParkHours(final Long seconds) {
		String hours = "";
		if (seconds == null) {
			return hours;
		}
		int usedMinutes = seconds.intValue() / 60;
		int surplusSeconds = seconds.intValue() % 60;
		if (usedMinutes >= 60) {
			int hour = usedMinutes / 60;
			if(hour>=24){
				int day=hour/24;
				hour=hour%24;
				hours += day + DayStr;
			}
			usedMinutes = usedMinutes % 60;
			hours += hour + HourStr;
		}
		if (usedMinutes > 0) {
			hours += usedMinutes + MinuteStr;
		}
		hours += surplusSeconds > 0 ? surplusSeconds + SecondsStr : "";
		return hours;
	}
	
	/**
	 * 获取停车时长 xx天xx小时xx分
	 * 
	 * @param minutes
	 * @return
	 */
	public static String getParkDays(final Long minutes) {
		String days="";
		if (minutes == null) {
			return days;
		}
		int usedHours = minutes.intValue() / 60;
		int surplusMinutes = minutes.intValue() % 60;
		if (usedHours >= 24) {
				int day=usedHours/24;
				usedHours=usedHours%24;
				days += day + DayStr;
		}
		
		if (usedHours > 0) {
			days += usedHours + HourStr;
		}
		days += surplusMinutes > 0 ? surplusMinutes + MinuteStr : "";
		return days;
	}

	public static String dateToStr(Date date, String type) {
		SimpleDateFormat format = new SimpleDateFormat(type);
		return format.format(date);
	}

	public static Date strToDate(String str, String type) throws ParseException  {
		SimpleDateFormat format = new SimpleDateFormat(type);
		return format.parse(str);
	}

	public static Date strToDate(String str) throws ParseException   {
		if (str != null && !"".equals(str.trim())) {
			if (str.matches("\\d{14}")) {
				return strToDate(str, "yyyyMMddHHmmss");
			}
			if (str.matches("\\d{4}-\\d{2}-\\d{2}\\s\\d{2}:\\d{2}:\\d{2}")) {
				return strToDate(str, "yyyy-MM-dd HH:mm:ss");
			}
			if(str.matches("\\d{4}-\\d{2}-\\d{2}")){
				return strToDate(str, "yyyy-MM-dd");
			}
		}
		return null;// 发送格式不对，返回时间null
	}
	
	/**
	 * 获取两个日期之间的天数
	 * 
	 * @param sTime	开始时间
	 * @param eTime	结束时间
	 * @return 天数
	 */
	public static int getDayNum(Date sTime,Date eTime){
		Long day = 0L;
		day = (eTime.getTime() - sTime.getTime())/(1000 * 60 * 60 * 24);
		return day.intValue();
	}
	
	/** 
     * 得到几天后的时间 
     *  
     * @param d 
     * @param day 
     * @return 
     */  
    public static Date getDateAfter(Date d, int day) {  
        Calendar now = Calendar.getInstance();  
        now.setTime(d);  
        now.set(Calendar.DATE, now.get(Calendar.DATE) + day);  
        return now.getTime();  
    } 
    
	/** 
     * 得到几小时后的时间 
     *  
     * @param d 
     * @param day 
     * @return 
     */  
    public static Date getHourAfter(Date d, int hour) {  
        Calendar now = Calendar.getInstance();  
        now.setTime(d);  
        now.set(Calendar.HOUR, now.get(Calendar.HOUR) + hour);  
        return now.getTime();  
    }
    
    /**
     * 得到传入日期的时间
     * 
     * @param date
     * @return
     */
    public static Date getTime(Date date) throws Exception{
    	SimpleDateFormat sf_ = new SimpleDateFormat("HH:mm:ss");
    	String t = sf_.format(date);
    	if(sf_.format(date).equals("23:59:59"))
    		t="00:00:00";
        return sf_.parse(t);  
    }
    
	/**
	 * 获取两个时间的间隔分钟数
	 * 
	 * @param start
	 * @param end
	 * @return
	 */
	public static int getMinute(Date start, Date end){
		if(start == null || end == null){
			return 0;
		}
		int a = 60000;
//		if(!end.after(start))
//			end = DateUtil.getDateAfter(end, 1);
		long l = end.getTime() - start.getTime();
		int min = new Long(l/a).intValue();
		if(l%a>0){
			min=min+1;
		}
		return Math.abs(min);
	}
	
	/**
	 * 获取两个时间的间隔分钟数
	 * 
	 * @param start
	 * @param end
	 * @return
	 */
	public static int getMinuteWithAfter(Date start, Date end){
		if(start == null || end == null){
			return 0;
		}
		int a = 60000;
		if(!end.after(start))
			end = DateUtil.getDateAfter(end, 1);
		long l = end.getTime() - start.getTime();
		int min = new Long(l/a).intValue();
		if(l%a>0){
			min=min+1;
		}
		return Math.abs(min);
	}
	
	/**
	 * 将时间段的所有日期转换成String类型（yyyy-MM-dd），并放入集合中
	 * 
	 * @param start
	 * @param end
	 * @return
	 */
	public static List<String> getDateToList(Date start, Date end){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		List<String> list = new ArrayList<String>();
		Date date = start;
		int max = 0;
		try {
			max = getDayNum(df.parse(df.format(start)), df.parse(df.format(end)));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		for(int i=0;i<=max;i++){
			date = DateUtil.getDateAfter(start, i);
			list.add(df.format(date));
		}
		return list;
	}
	
	/**
	 * 得到String类型的星期（yyyy-MM-dd）
	 * 如果失败返回-1
	 * @param date
	 * @return
	 */
	public static Integer getWeek(String date){
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		Integer result = -1;
		try {
			result = getWeek(sf.parse(date));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 得到星期
	 * 1  	#SUNDAY
     * 2	#MONDAY
     * 3	#TUESDAY
     * 4	#WEDNESDAY
     * 5	#THURSDAY
     * 6	#FRIDAY
     * 7	#SATURDAY
	 * 
	 * @param date
	 * @return
	 */
	public static Integer getWeek(Date date){
		Calendar cal =  Calendar.getInstance();
		cal.setTime(date);
		return cal.get( Calendar.DAY_OF_WEEK);
	}
	
	/**
	 * 比较两个日期 返回值为两个日期的差。
	 * 
	 * @return int
	 * @param sDate1
	 *            java.lang.String
	 * @param sDate2
	 *            java.lang.String
	 */
	public static long compareDate(String sDate1, String sDate2) {
		DateFormat dateFormat = DateFormat.getDateInstance();
		Date date1 = null;
		Date date2 = null;
		try {
			date1 = dateFormat.parse(sDate1);
			date2 = dateFormat.parse(sDate2);
		} catch (ParseException e) {
			System.err
					.println("Application log:Catch Exception in compareDate()");
			System.err.println("sDate1:" + sDate1);
			System.err.println("sDate2:" + sDate2);
			System.err.println(e.getMessage());
			e.printStackTrace();
			return 0;
		}

		long dif = 0;
		long lDate2 = date2.getTime();
		long lDate1 = date1.getTime();

		dif = (lDate2 - lDate1) / 1000 / 60 / 60 / 24;
		return dif;
	}

	/**
	 * 获取当前日期 格式为YYYY-MM-DD。
	 * 
	 * @return java.lang.String
	 */
	public static String getCurrentDate() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String s = df.format(new Date());
		return s;
	}

	/**
	 * 获取当前日期及时间 格式为YYYY-MM-DD HH:mm。
	 * 
	 * @return java.lang.String
	 */
	public static String getCurrentDateTime() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String s = df.format(new Date());
		return s;
	}
	
	public static String getCurrentTimeStamp(){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");
		String s = df.format(new Date());
		return s;
	}
	
	public static String getTimeStampString(long l) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");
		String s = df.format(l);
		return s;
	}

	/**
	 * 获取当前日期中的日。
	 * 
	 * @return java.lang.String
	 */
	public static String getCurrentDay() {
		String day;
		SimpleDateFormat df = new SimpleDateFormat("d");
		day = df.format(new Date());
		return day;
	}

	/**
	 * 获取当前日期中的月。
	 * 
	 * @return java.lang.String
	 */
	public static String getCurrentMonth() {
		String month;
		SimpleDateFormat df = new SimpleDateFormat("M");
		month = df.format(new Date());
		return month;
	}

	/**
	 * 获取当前时间 格式为YYYY-MM-DD HH:MM:SS。
	 * 
	 * @return java.lang.String
	 */
	public static String getCurrentTime() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return df.format(new Date());
	}

	/**
	 * 获取当前日期中的年。
	 * 
	 * @return java.lang.String
	 */
	public static String getCurrentYear() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy");
		return df.format(new Date());
	}

	/**
	 * 获取当前周。
	 * 
	 * @return java.lang.String
	 */
	public static String getCurrentWeek() {
		SimpleDateFormat df = new SimpleDateFormat("ww");
		return df.format(new Date());
	}

	/**
	 * 获取当前周。
	 * 
	 * @return java.lang.String
	 */
	public static String getCurrentWeek(String date, String flag) {
		SimpleDateFormat df = new SimpleDateFormat(flag);
		return df.format(getDateD(date));
	}

	/**
	 * 获取指定季度或月的起止日期。
	 * 
	 * @return java.lang.String
	 * @param periodType
	 *            int 0表示季度 1表示月份
	 * @param year
	 *            java.lang.String
	 * @param period
	 *            java.lang.String
	 */
	public static String[] getDate(int periodType, String year, String period) {

		String[] dates = { getCurrentDate(), getCurrentDate() };

		// Validate
		if (periodType != 0 && periodType != 1) {
			// Error period type
			System.err.println("Error period type in DateUtil.getDate().");
			System.err.println("Period type(0-1):" + periodType);
			return dates;
		}

		int intYear = 2000;
		try {
			intYear = Integer.parseInt(year);
			if (intYear < 1900 || intYear > 3000) {
				System.err.println("Invalid year in DateUtility.getDate().");
				System.err.println("Year(1900-3000):" + year);
				return dates;
			}
		} catch (NumberFormatException e) {
			System.err.println("Invalid year in DateUtility.getDate().");
			System.err.println("Year:" + year);
			return dates;
		}

		int intPeriod = 1;
		try {
			intPeriod = Integer.parseInt(period);
			if (periodType == 0) {
				// Season
				if (intPeriod < 1 || intPeriod > 4) {
					System.err
							.println("Invalid season in DateUtility.getDate().");
					System.err.println("Season(1-4):" + period);
					return dates;
				}
			} else {
				// Month
				if (intPeriod < 1 || intPeriod > 12) {
					System.err
							.println("Invalid month in DateUtility.getDate().");
					System.err.println("Month(1-12):" + period);
					return dates;
				}
			}
		} catch (NumberFormatException e) {
			System.err.println("Invalid period in DateUtility.getDate().");
			System.err.println("Period:" + period);
			return dates;
		}

		if (periodType == 0) {
			// Season
			switch (intPeriod) {
			case 1:
				dates[0] = year + "-1-1";
				dates[1] = year + "-3-31";
				break;
			case 2:
				dates[0] = year + "-4-1";
				dates[1] = year + "-6-30";
				break;
			case 3:
				dates[0] = year + "-7-1";
				dates[1] = year + "-9-30";
				break;
			case 4:
				dates[0] = year + "-10-1";
				dates[1] = year + "-12-31";
				break;
			}
		} else {
			// Month
			switch (intPeriod) {
			case 1:
				dates[0] = year + "-1-1";
				dates[1] = year + "-1-31";
				break;
			case 2:
				dates[0] = year + "-2-1";
				if ((intYear % 400 == 0)
						|| ((intYear % 4 == 0) && (intYear % 100 != 0))) {
					dates[1] = year + "-2-29";
				} else {
					dates[1] = year + "-2-28";
				}
				break;
			case 3:
				dates[0] = year + "-3-1";
				dates[1] = year + "-3-31";
				break;
			case 4:
				dates[0] = year + "-4-1";
				dates[1] = year + "-4-30";
				break;
			case 5:
				dates[0] = year + "-5-1";
				dates[1] = year + "-5-31";
				break;
			case 6:
				dates[0] = year + "-6-1";
				dates[1] = year + "-6-30";
				break;
			case 7:
				dates[0] = year + "-7-1";
				dates[1] = year + "-7-31";
				break;
			case 8:
				dates[0] = year + "-8-1";
				dates[1] = year + "-8-31";
				break;
			case 9:
				dates[0] = year + "-9-1";
				dates[1] = year + "-9-30";
				break;
			case 10:
				dates[0] = year + "-10-1";
				dates[1] = year + "-10-31";
				break;
			case 11:
				dates[0] = year + "-11-1";
				dates[1] = year + "-11-30";
				break;
			case 12:
				dates[0] = year + "-12-1";
				dates[1] = year + "-12-31";
				break;
			}
		}

		return dates;
	}

	/**
	 * 获取一个月前的一天。
	 * 
	 * @return java.lang.String
	 */
	public static String getDateBeforeAMonth() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, -1);
		calendar.add(Calendar.DATE, 1);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-M-d");
		String s = df.format(calendar.getTime());
		return s;
	}

	/**
	 * 获取一个月后的一天。
	 * 
	 * @return String
	 */
	public static String getDateAfterAMonth() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, 1);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-M-d");
		String s = df.format(calendar.getTime());
		return s;
	}

	/**
	 * 获取当前月的第一天。
	 * 
	 * @return java.lang.String
	 */
	public static String getFirstDateOfMonth() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-M-");
		String s = df.format(new Date());
		s += "1";
		return s;
	}

	/**
	 * 验证日期的合法性。
	 * 
	 * @param date
	 *            String
	 * @param type
	 *            int 0表示一个日期 1表示年月
	 * @return boolean
	 */
	public static boolean validateDate(String date, int type) {

		boolean valid = true;
		DateFormat dateFormat = DateFormat.getDateInstance();
		switch (type) {
		case 0:
			// 2001-9-1
			break;
		case 1:
			// 2001-9
			date += "-1";
			break;
		}

		try {
			dateFormat.parse(date);
		} catch (ParseException e) {
			valid = false;
			System.err.println("Invalid date format:" + date);
			e.printStackTrace();
		}

		return valid;
	}

	/**
	 * 获取当前季度。
	 * 
	 * @return String
	 */
	public static String getCurrentQuarter() {
		String quarter = null;
		Calendar mydate = Calendar.getInstance();
		Double dd = new Double(Math.floor(mydate.get(Calendar.MONTH) / 3));
		switch (dd.intValue()) {
		case 0:
			quarter = "1";
			break;
		case 1:
			quarter = "2";
			break;
		case 2:
			quarter = "3";
			break;
		case 3:
			quarter = "4";
			break;
		}
		return quarter;
	}

	/**
	 * 根据日期获取所属季度。
	 * 
	 * @param sDate1
	 *            java.lang.String
	 * @return String
	 */
	public static String getQuarterByDay(String sDate1) {
		String quarter = "";
		DateFormat dateFormat = DateFormat.getDateInstance();
		Date date1 = null;
		try {
			date1 = dateFormat.parse(sDate1);
		} catch (ParseException e) {
			System.err
					.println("Application log:Catch Exception in getQuarterByDay()");
			System.err.println("sDate1:" + sDate1);
			System.err.println(e.getMessage());
			e.printStackTrace();
			return "";
		}
		Calendar mydate = Calendar.getInstance();
		mydate.setTime(date1);
		Double dd = new Double(Math.floor(mydate.get(Calendar.MONTH) / 3));
		switch (dd.intValue()) {
		case 0:
			quarter = "1";
			break;
		case 1:
			quarter = "2";
			break;
		case 2:
			quarter = "3";
			break;
		case 3:
			quarter = "4";
			break;
		}
		return quarter;
	}

	/**
	 * 获取与当前日期相差若干天的日期。
	 * 
	 * @return java.lang.String
	 * @param dif
	 *            int
	 */
	public static String getDate(int dif) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, dif);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-M-d");
		String s = df.format(calendar.getTime());
		return s;
	}

	/**
	 * 获取与指定日期相差若干天的日期。
	 * 
	 * @return java.lang.String
	 * @param aDate
	 *            java.lang.String
	 * @param dif
	 *            int
	 */
	public static String getDate(String aDate, int dif) {
		DateFormat dateFormat = DateFormat.getDateInstance();
		Date date = null;
		try {
			date = dateFormat.parse(aDate);
		} catch (ParseException e) {
			System.err.println("Application log:Catch Exception in getDate()");
			System.err.println("aDate:" + aDate);
			System.err.println(e.getMessage());
			e.printStackTrace();
			return null;
		}

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, dif);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-M-d");
		String s = df.format(calendar.getTime());
		return s;
	}

	/**
	 * 获取指定日期一个月前的一天。
	 * 
	 * @param aDate
	 *            java.lang.String
	 * @return java.lang.String
	 */
	public static String getDateBeforeAMonth(String aDate) {
		DateFormat dateFormat = DateFormat.getDateInstance();
		java.util.Date date1 = null;
		try {
			date1 = dateFormat.parse(aDate);
		} catch (ParseException e) {
			System.err
					.println("Application log:Catch Exception in getDateBeforeAMonth(String)");
			System.err.println("aDate:" + aDate);
			System.err.println(e.getMessage());
			e.printStackTrace();
			return null;
		}

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date1);
		calendar.add(Calendar.MONTH, -1);
		calendar.add(Calendar.DATE, 1);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String s = df.format(calendar.getTime());
		return s;
	}

	/**
	 * 获取指定日期下个月的同一天。
	 * 
	 * @param aDate
	 *            String
	 * @return java.lang.String
	 */
	public static String getDateAfterMonth(String aDate) {
		DateFormat dateFormat = DateFormat.getDateInstance();
		java.util.Date date1 = null;
		try {
			date1 = dateFormat.parse(aDate);
		} catch (ParseException e) {
			System.err
					.println("Application log:Catch Exception in getDateBeforeAMonth(String)");
			System.err.println("aDate:" + aDate);
			System.err.println(e.getMessage());
			e.printStackTrace();
			return null;
		}

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date1);
		calendar.add(Calendar.MONTH, 1);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String s = df.format(calendar.getTime());
		return s;
	}

	/**
	 * 获取指定日期下个月的某一天。
	 * 
	 * @param aDate
	 *            String
	 * @param n
	 *            int
	 * @return java.util.Date
	 */
	public static String getDateAfterMonth(String aDate, int n) {
		DateFormat dateFormat = DateFormat.getDateInstance();
		java.util.Date date1 = null;
		try {
			date1 = dateFormat.parse(aDate);
		} catch (ParseException e) {
			System.err
					.println("Application log:Catch Exception in getDateBeforeAMonth(String)");
			System.err.println("aDate:" + aDate);
			System.err.println(e.getMessage());
			e.printStackTrace();
			return null;
		}

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date1);
		calendar.add(Calendar.MONTH, 1);
		calendar.set(Calendar.DATE, n);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String s = df.format(calendar.getTime());
		return s;
	}

	/**
	 * 获取指定日期所在月的最后一天。
	 * 
	 * @param selectDate
	 *            String
	 * @return int
	 */
	public static int getLastDate(String selectDate) {
		// Validate
		int dates = 0;
		int year;
		Calendar calendar = Calendar.getInstance();
		try {
			calendar.setTime(DateFormat.getDateInstance().parse(selectDate));
		} catch (ParseException e) {
		}
		year = calendar.get(Calendar.YEAR);
		//System.out.println(calendar.get(Calendar.MONTH));
		switch (calendar.get(Calendar.MONTH) + 1) {
		case 1:
			dates = 31;
			break;
		case 2:
			if ((year % 400 == 0) || ((year % 4 == 0) && (year % 100 != 0))) {
				dates = 29;
			} else {
				dates = 28;
			}
			break;
		case 3:
			dates = 31;
			break;
		case 4:
			dates = 30;
			break;
		case 5:
			dates = 31;
			break;
		case 6:
			dates = 30;
			break;
		case 7:
			dates = 31;
			break;
		case 8:
			dates = 31;
			break;
		case 9:
			dates = 30;
			break;
		case 10:
			dates = 31;
			break;
		case 11:
			dates = 30;
			break;
		case 12:
			dates = 31;
			break;
		}
		return dates;
	}

	/**
	 * 获取当前日期后的一天。
	 * 
	 * @return java.lang.String
	 */
	public static String getNextDate() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, 1);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-M-d");
		String s = df.format(calendar.getTime());
		return s;
	}

	/**
	 * 获取当前日期前的一天。
	 * 
	 * @return java.lang.String
	 */
	public static String getPreviousDate() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, -1);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-M-d");
		String s = df.format(calendar.getTime());
		return s;
	}
	
	/**
	 * 获取当前日期前的一天。
	 * 
	 * @return java.lang.String
	 */
	public static String getPreDate() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, -1);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String s = df.format(calendar.getTime());
		return s;
	}

	/**
	 * 获取前一天的日期
	 * @author ningquan
	 * @param date
	 * @return
	 */
	public static Date getPreDate(Date date){
		if(date == null){
			date = new Date();
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, -1);
		return calendar.getTime();
	}

	/**
	 * Change date format. From: yyyy-MM-dd To: yyyy.MM.dd。
	 * 
	 * @param oldValue
	 *            String
	 * @return String
	 */
	public static String changeDateFormat(String oldValue) {
		String newValue = new String("2000.01.01");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
		DateFormat df = DateFormat.getDateInstance();
		Date temp = null;
		try {
			temp = df.parse(oldValue);
		} catch (ParseException e) {
			System.err
					.println("Catch invalid date format in method changeDateFormat(String oldValue) in class DateUtility.");
			System.err.println("Your input parameter:oldValue(" + oldValue
					+ ")");
		}
		newValue = sdf.format(temp);
		return newValue;
	}

	/**
	 * 由日期和时分秒来构成一个java.sql.Date。
	 * 
	 * @param aDay
	 *            String
	 * @param aHour
	 *            String
	 * @param aMinute
	 *            String
	 * @param aSecond
	 *            String
	 * @return java.sql.Date
	 */
	public static java.sql.Date constructDate(String aDay, String aHour,
			String aMinute, String aSecond) {
		java.sql.Date sDate = null;
		Date uDate = null;
		int hour, minute, second;
		DateFormat dateFormat = DateFormat.getDateInstance();
		try {
			uDate = dateFormat.parse(aDay);
			hour = Integer.parseInt(aHour);
			minute = Integer.parseInt(aMinute);
			second = Integer.parseInt(aSecond);
		} catch (ParseException e) {
			System.err
					.println("Application log:Catch Exception in DateUtility");
			System.err.println("aDay:" + aDay);
			System.err.println(e.getMessage());
			e.printStackTrace();
			return null;
		} catch (NumberFormatException e) {
			System.err
					.println("Application log:Catch Exception in DateUtility");
			System.err.println(e.getMessage());
			e.printStackTrace();
			return null;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(uDate);
		calendar.set(Calendar.HOUR_OF_DAY, hour);
		calendar.set(Calendar.MINUTE, minute);
		calendar.set(Calendar.SECOND, second);
		uDate = calendar.getTime();
		sDate = new java.sql.Date(uDate.getTime());
		return sDate;
	}

	/**
	 * 构造日期格式。
	 * @param aDay
	 *            String
	 * @return java.sql.Date
	 */
	public static java.sql.Date constructDate(String aDay) {
		return constructDate(aDay, "0", "0", "0");
	}

	/**
	 * 由日期和时分秒来构成一个java.sql.Timestamp。
	 * 
	 * @param aDay
	 *            String
	 * @param aHour
	 *            String
	 * @param aMinute
	 *            String
	 * @param aSecond
	 *            String
	 * @return java.sql.Timestamp
	 */
	public static java.sql.Timestamp constructTimestamp(String aDay,
			String aHour, String aMinute, String aSecond) {
		java.sql.Timestamp timestamp = null;
		Date uDate = null;
		int hour, minute, second;
		DateFormat dateFormat = DateFormat.getDateInstance();
		try {
			uDate = dateFormat.parse(aDay);
			hour = Integer.parseInt(aHour);
			minute = Integer.parseInt(aMinute);
			second = Integer.parseInt(aSecond);
		} catch (ParseException e) {
			System.err
					.println("Application log:Catch Exception in DateUtility");
			System.err.println("aDay:" + aDay);
			System.err.println(e.getMessage());
			e.printStackTrace();
			return null;
		} catch (NumberFormatException e) {
			System.err
					.println("Application log:Catch Exception in DateUtility");
			System.err.println(e.getMessage());
			e.printStackTrace();
			return null;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(uDate);
		calendar.set(Calendar.HOUR_OF_DAY, hour);
		calendar.set(Calendar.MINUTE, minute);
		calendar.set(Calendar.SECOND, second);
		uDate = calendar.getTime();
		timestamp = new java.sql.Timestamp(uDate.getTime());
		return timestamp;
	}

	/**
	 * 由带时分秒的日期来构成一个java.sql.Timestamp。
	 * 
	 * @param aTime
	 *            String 格式：2004-02-06 15:31:08
	 * @return java.sql.Timestamp modified by chenjie 2004-07-07
	 */
	public static java.sql.Timestamp constructTimestamp(String aTime) {
		java.sql.Timestamp timestamp = null;
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		try {
			timestamp = new java.sql.Timestamp(dateFormat.parse(aTime)
					.getTime());
		} catch (ParseException e) {
			System.err
					.println("Application log:Catch Exception in DateUtility");
			System.err.println("aTime:" + aTime);
			e.printStackTrace();
			return null;
		}
		return timestamp;
	}

	/**
	 * 由日期来构成一个java.sql.Timestamp 获取上个月的第一天。
	 * 
	 * @return String
	 */
	public static String getFirstDateOfLastMonth() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, -1);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-");
		String s = df.format(calendar.getTime());
		s += "01";
		return s;
	}

	/**
	 * 获取上个月的最后一天。
	 * 
	 * @return String
	 */
	public static String getLastDateOfLastMonth() {
		DateFormat dateFormat = DateFormat.getDateInstance();
		Date uDate = null;
		try {
			uDate = dateFormat.parse(getFirstDateOfMonth());
		} catch (ParseException e) {
			System.err
					.println("Application log: Catch Exception in DateUtility");
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(uDate);
		calendar.add(Calendar.DATE, -1);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String s = df.format(calendar.getTime());
		return s;
	}

	/**
	 * 获取上年同期的日期。
	 * 
	 * @param curBegin
	 *            String
	 * @param curEnd
	 *            String
	 * @return String[]
	 */
	public static String[] getSameOfLastYear(String curBegin, String curEnd) {
		String[] last = new String[2];
		last[0] = (Integer.parseInt(curBegin.substring(0, 4)) - 1)
				+ curBegin.substring(4);
		last[1] = (Integer.parseInt(curEnd.substring(0, 4)) - 1)
				+ curEnd.substring(4);
		return last;
	}

	/**
	 * 将日期转换为字符串。
	 * 
	 * @param aDate
	 *            java.util.Date
	 * @param formatString
	 *            格式化字符串，如：yyyy-MM-dd
	 * @return String
	 */
	public static String formatDate(java.util.Date aDate, String formatString) {
		if (aDate == null) {
			return "";
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(aDate);
		SimpleDateFormat df = new SimpleDateFormat(formatString);
		String s = df.format(calendar.getTime());
		return s;
	}

	/**
	 * 由日期获得星期(String)。
	 * 
	 * @param s
	 *         java.lang.String（"yyyy-MM-dd"）
	 * @return java.lang.String
	 */
	public static String getDay(String s) {
		SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat dayFormatter = new SimpleDateFormat("EEE");
		ParsePosition pos = new ParsePosition(0);
		Date giveDate = dateFormatter.parse(s, pos);
		return dayFormatter.format(giveDate).toString();
	}

	/**
	 * 将日期由字符串转成日期型。
	 * 
	 * @param s
	 *            java.lang.String（"yyyy-MM-dd"）
	 * @return java.util.Date yyyy-MM-dd
	 */
	public static Date getDateD(String s) {
		SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
		ParsePosition pos = new ParsePosition(0);
		return dateFormatter.parse(s, pos);
	}

	/**
	 * 将星期由字符串转成日期型。
	 * 
	 * @param s
	 *            EEE
	 * @return EEE
	 */
	public static Date getDayD(String s) {
		SimpleDateFormat dayFormatter = new SimpleDateFormat("EEE");
		ParsePosition pos = new ParsePosition(0);
		return dayFormatter.parse(s, pos);
	}

	/**
	 * 将时间由字符串转成日期型。
	 * 
	 * @param s
	 *            HH:mm:ss
	 * @return HH:mm:ss
	 */
	public static Date getTimeD(String s) {
		SimpleDateFormat timeFormatter = new SimpleDateFormat("HH:mm:ss");
		ParsePosition pos = new ParsePosition(0);
		return timeFormatter.parse(s, pos);
	}
	/**
	 * 将时间由字符串转成日期型。
	 * 
	 * @param s
	 *            HH:mm:ss
	 * @return HH:mm:ss
	 */
	public static Date getDateTimeD(String s) {
		SimpleDateFormat timeFormatter = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		ParsePosition pos = new ParsePosition(0);
		return timeFormatter.parse(s, pos);
	}

	/**
	 * 得到本周的第几天(星期一-星期日 1-7)。
	 * 
	 * @param num
	 *            int 1-7
	 * @return java.lang.String
	 */
	public static String getDateOfWeek(int num) {
		Calendar calendar = Calendar.getInstance();
		int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
		if (num < 1) {
			num = 1;
		}
		if (num > 7) {
			num = 7;
		}
		calendar.add(Calendar.DATE, num - dayOfWeek);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		return df.format(calendar.getTime());
	}

	/**
	 * 得到本月的第几天(1-31),如果没有31天则取本月的最后一天。
	 * 
	 * @param num int
	 * @return java.lang.String
	 */
	public static String getDateOfMonth(int num) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		if (num < 1) {
			num = 1;
		}
		int days = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		if (num > days) {
			num = days;
		}
		calendar.add(Calendar.DATE, num - 1);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		return df.format(calendar.getTime());
	}


	/**
	 * 将字符串格式日期转换成long型。
	 * 
	 * @param strDate
	 *            type:YYYY-MM-DD
	 * @return java.lang.Long
	 */
	public static Long getLongDate(String strDate) {
		Date date = java.sql.Date.valueOf(strDate);
		Long lDate = new Long(date.getTime());
		return (lDate);
	}

	/**
	 * 将字符串格式日期转换成long型 。
	 * 
	 * @param iType
	 *            value strDate type 0 :YYYY-MM-DD 1 :YYYY-MM-DD hh:mm:ss
	 * @return java.lang.Long
	 */
	public static Long getLongDate(String strDate, int iType) {
		Long retDate = null;
		switch (iType) {
		case 0:
			retDate = getLongDate(strDate);
			break;
		case 1:
			retDate = new Long(java.sql.Timestamp.valueOf(strDate).getTime());
			break;
		}
		return retDate;
	}

	/**
	 * 将Long格式日期转换成字符串型。
	 * 
	 * @param lDate
	 *            iType value output 0 YYYY-MM-DD 1 YYYY-MM-DD hh 2 YYYY-MM-DD
	 *            hh:ss 3 YYYY-MM-DD hh:ss:mm
	 * @return java.lang.String
	 */

	public static String getStrDate(java.lang.Long lDate, int iType) {
		Date date = new Date(lDate.longValue());
		SimpleDateFormat simpleDateFormat = null;
		if (lDate == null) {
			return "";
		}
		switch (iType) {
		case 0:
			simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			break;
		case 1:
			simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH");
			break;
		case 2:
			simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			break;
		case 3:
			simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			break;
		}

		String strDate = simpleDateFormat.format(date);
		return (strDate);
	}

	/**
	 * 将执行耗时long转为字符显示
	 * @param l java.lang.long
	 * @return java.lang.string
	 */
	public static String getUsedTime(long l) {
		String str = "";
		long h = l / (1000 * 60 * 60);
		if (h > 0)
			str += h + "小时";
		long m = l % (1000 * 60 * 60) / (1000 * 60);
		if (m > 0)
			str += m + "分钟";
		long s = l % (1000 * 60 * 60) % (1000 * 60) / 1000;
		if (s > 0)
			str += s + "秒";
		long ms = l % 1000;
		if (ms > 0)
			str += ms + "毫秒";

		return str;
	}

	/**
	 * 为指定日期添加分钟数，返回添加分钟数后的时间
	 * @author ningquan
	 * @param date
	 * @param min
	 * @return
	 */
	public static Date addMinutes(Date date, int min) {
		if(date == null){
			return null;
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MINUTE, min);
		return cal.getTime();
	}
	
	/**
	 * @author ningquan
	 * 获取前一周的日期
	 */
	public static Date getPreWeekOfDate(Date currentDate){
		if(currentDate == null){
			currentDate = new Date();
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(currentDate);
		calendar.add(Calendar.WEEK_OF_YEAR, -1);
		return calendar.getTime();
	}

	/**
	 * 获取本月的第一天
	 * @author ningquan
	 * @return
	 */
	public static Date getFirstDayOfMonth(){
		 Calendar calendar = Calendar.getInstance();   
		 calendar.add(Calendar.MONTH, 0);
		 calendar.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天 
		 calendar.set(Calendar.HOUR_OF_DAY, 0);
         calendar.set(Calendar.MINUTE, 0);  
         calendar.set(Calendar.SECOND,0);  
         calendar.set(Calendar.MILLISECOND, 0);  
         return calendar.getTime();
	}
	/**
	 * 获取一年12个月
	 * @author 
	 * @return
	 */
	public static List<String> getMonthOfYear(String year){
		List<String> list=new ArrayList<String>();
		
		for(int i=1;i<=12;i++){
			list.add(year+(i<10?"-0"+i:"-"+i));
		}
		return list;
	}
	/**
	 * 获取指定月份的开始和结束日期
	 * @param year		年
	 * @param month		月
	 * @return
	 */
	public static String[] getMonthDate(String year, String month) {
		String[] dates = { getCurrentDate(), getCurrentDate() };
		int intYear = 2000;
		try {
			intYear = Integer.parseInt(year);
			if (intYear < 1900 || intYear > 3000) {
				System.err.println("Invalid year in DateUtility.getDate().");
				System.err.println("Year(1900-3000):" + year);
				return dates;
			}
		} catch (NumberFormatException e) {
			System.err.println("Invalid year in DateUtility.getDate().");
			System.err.println("Year:" + year);
			return dates;
		}
		int intMonth = 1;
		try {
			intMonth = Integer.parseInt(month);
			if (intMonth < 1 || intMonth > 12) {
				System.err.println("Invalid month in DateUtility.getDate().");
				System.err.println("Month(1-12):" + month);
				return dates;
			}
		} catch (NumberFormatException e) {
			System.err.println("Invalid month in DateUtility.getDate().");
			System.err.println("month:" + month);
			return dates;
		}
		switch (intMonth) {
		case 1:
			dates[0] = year + "-01-01 00:00:00";
			dates[1] = year + "-02-01 00:00:00";
			break;
		case 2:
			dates[0] = year + "-02-01 00:00:00";
			dates[1] = year + "-03-01 00:00:00";
			break;
		case 3:
			dates[0] = year + "-03-01 00:00:00";
			dates[1] = year + "-04-01 00:00:00";
			break;
		case 4:
			dates[0] = year + "-04-01 00:00:00";
			dates[1] = year + "-05-01 00:00:00";
			break;
		case 5:
			dates[0] = year + "-05-01 00:00:00";
			dates[1] = year + "-06-01 00:00:00";
			break;
		case 6:
			dates[0] = year + "-06-01 00:00:00";
			dates[1] = year + "-07-01 00:00:00";
			break;
		case 7:
			dates[0] = year + "-07-01 00:00:00";
			dates[1] = year + "-08-01 00:00:00";
			break;
		case 8:
			dates[0] = year + "-08-01 00:00:00";
			dates[1] = year + "-09-01 00:00:00";
			break;
		case 9:
			dates[0] = year + "-09-01 00:00:00";
			dates[1] = year + "-10-01 00:00:00";
			break;
		case 10:
			dates[0] = year + "-10-01 00:00:00";
			dates[1] = year + "-11-01 00:00:00";
			break;
		case 11:
			dates[0] = year + "-11-01 00:00:00";
			dates[1] = year + "-12-01 00:00:00";
			break;
		case 12:
			dates[0] = year + "-12-01 00:00:00";
			dates[1] = Integer.parseInt(year)+1 + "-01-01 00:00:00";
			break;
		}
		return dates;
	}
	/**
	* @Title wipeSeconds
	* @Description 秒数抹零;返回格式为{yyyy-MM-dd HH:mm}
	* @param date
	* @return 
	* @Return Date 返回类型
	* @Throws
	* @author Jack 2016年6月6日15:14:05
	 */
	public static Date wipeSeconds(Object date){
		if( date == null) 
		{
			return null;
		}
		try 
		{
			final String pattern = "yyyyMMddHHmm";
			if(date instanceof String) {
				return strToDate( (String) date , pattern );
			}
			if(date instanceof Date) {
				return strToDate(dateToStr((Date)date,pattern),pattern);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 判断某一时间是否在一时间段范围内
	 * 时间格式：HH:mm:ss
	 * 
	 * @param sTime	时段起
	 * @param eTime	时段止
	 * @param nTime	传入时间
	 * @return
	 * @throws Exception
	 */
	public static boolean checkTimeBetween(Date sTime,Date eTime,Date nTime) throws Exception{
		boolean result = false;
		SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
		if(eTime.compareTo(sTime) == 0){
			throw new Exception("sTime is equal to eTime!");
		}
		if(eTime.before(sTime)){
			Date tmp1 = df.parse("00:00:00");
			Date tmp2 = df.parse("24:00:00");
			if((nTime.compareTo(sTime) >= 0 && nTime.compareTo(tmp2)<0) 
					|| (nTime.compareTo(tmp1) >= 0 && nTime.compareTo(eTime)<0)){
				result = true;
			}
		}else if(nTime.compareTo(sTime) >= 0 && nTime.compareTo(eTime)<0){
			result = true;
		}
		return result;
	}
	
	public static void main(String[] args) {
		SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
		try{
			Date sTime = df.parse("08:00:00");
			Date eTime = df.parse("20:00:00");
			Date sTime2 = df.parse("20:00:00");
			Date eTime2 = df.parse("08:00:00");
			Date nTime = df.parse("19:00:00");
			System.out.println(DateUtil.checkTimeBetween(sTime2,eTime2,nTime));
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
}
