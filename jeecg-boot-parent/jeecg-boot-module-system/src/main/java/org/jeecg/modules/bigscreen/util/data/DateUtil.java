package org.jeecg.modules.bigscreen.util.data;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.format.ISODateTimeFormat;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.*;

public class DateUtil {
	public static final String TIME_FORMAT_ALL = "yyyy-MM-dd'T'HH:mm:ss.SSSZZ";
	public static final String TIME_FORMAT_T = "yyyy-MM-dd'T'HH:mm:ss";
	public static final String TIME_FORMAT_ZONE = "yyyy-MM-dd'T'HH:mm:ssXXX";
	public static final String FORMAT_MEDIUM_TZ = "yyyy-MM-dd'T'HH:mm:ss'Z'";

	public static final String TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

	public static final String DATE_FORMAT_yyyy_MM_dd = "yyyy-MM-dd";

	public static final String DATE_FORMAT_yyyy_MM = "yyyy-MM";

	public static final String DATE_FORMAT_yyyy_MM_dd_HH_mm_ss = "yyyy-MM-dd'T'HH:mm:ss.SSSZZZZZ";

	public static final String DATE_FORMAT_yyyy = "yyyy";

	public static final String IIMEZONE_GMT8 = "GMT+8";
	/** MM月 格式 */
	public static final String FORMAT_DATE_MONTH_CHINESE = "MM月";
	/** yyyy年MM月dd日 格式 */
	public static final String FORMAT_DATE_CHINESE = "yyyy年MM月dd日";
	/** YYYY年MM月 格式 */
	public static final String FORMAT_DATE_YEAR_MONTH_CHINESE = "yyyy年MM月";
	/** 时间日期格式 到毫秒 */
	public static final String FORMAT_DATETIME_MILLISECOND = "yyyyMMddHHmmssSSS";
	public static final String TIME_DATE_HOUR_FORMAT = "yyyy-MM-dd HH:00:00";
	/** yyyy/MM月/日 格式 */
	public static final String FORMAT_DATE_SLASH = "yyyy/MM/dd";
	/** 时间日期 格式 */
	public static final String FORMAT_DATETIME_SLASH = "yyyy/MM/dd HH:mm:ss";

	public static final String TIME_FORMAT_SS_SSS = "yyyy-MM-dd HH:mm:ss.SSS";
	public static final String TIME_FORMAT_ISO  ="EEE, dd MMM yyyy HH:mm:ss 'GMT'";
	/**
	 * 季开始月份数组 格里高利历和罗马儒略历中一年中的第一个月是 JANUARY，它为 0
	 * */
	public static final Integer[] SEASON_BEGIN = { 0, 3, 6, 9 };

	private static final ZoneId defZoneId = ZoneId.systemDefault();

	private static volatile long latestTime = 0;

	private static final Object lock = new Object();

	/**
	 * 获取当前时间，且全局唯一
	 * @return
	 */
	public static Date getUniqueNowDate(){
		synchronized (lock){
			long now = System.currentTimeMillis();
			latestTime = latestTime < now ? now : latestTime + 1;
			return new Date(latestTime);
		}
	}

	/**
	 * ISO-dateTime的时间转换
	 * 如：2004-05-03T17:30:08+08:00
	 *     2004-05-03T17:30:08.000Z
	 *     2004-05-03T17:30:08Z
	 * @param dateStr
	 * @return
	 */
	public static Date parseISODateTime2Date(String dateStr) {
		if(dateStr.equalsIgnoreCase("0000-00-00T00:00:00Z")){
			return new Date();
		}else{
			org.joda.time.format.DateTimeFormatter dateTimeFormatter = ISODateTimeFormat.dateTimeParser();
			return dateTimeFormatter.parseDateTime(dateStr).toDate();
		}
	}

	/**
	 * 返回带时区的时间 如：2018-12-29T11:57:09+08:00
	 * @author gonghao 2018/12/29 11:55
	 * @param date
	 * @return java.lang.String
	 * @since 1.0.0
	 */
	public static String getTimeFormatIso(Date date){
		TimeZone tz = TimeZone.getDefault();
		DateFormat df = new SimpleDateFormat(TIME_FORMAT_ZONE);
		df.setTimeZone(tz);
		return df.format(date);
	}

	/**
	 * 返回 yyyy-MM-dd'T'HH:mm:ss'Z 格式的时间
	 * 注意这里返回的是世界协调时的时间，比东八区的时间少了8小时
	 * @author gonghao 2018/12/29 11:55
	 * @param date 时间
	 * @return java.lang.String
	 * @since 1.0.0
	 */
	public static String getUTCFormatTz(Date date){
		TimeZone tz = TimeZone.getTimeZone("UTC");
		DateFormat df = new SimpleDateFormat(FORMAT_MEDIUM_TZ);
		df.setTimeZone(tz);
		return df.format(date);
	}

	/**
	 * 将long类型时间转化为默认格式字符串， 默认格式字符串为“yyyy-MM-dd HH:mm:ss”
	 * @param time
	 * @return
	 */
	public  static String getFormatTime(long time){
		return getFormatTime(time, TIME_FORMAT);
	}

	/**
	 *
	 * @param time  long类型的时间
	 * @param format  指定格式化字符串，如"yyyy-MM-dd HH:mm:ss.SSS"
	 * @return
	 */
	public  static String getFormatTime(long time, String format){
		return getFormatTime(Instant.ofEpochMilli(time), format);
	}

	/**
	 * 将Date类型时间转化为默认格式字符串， 默认格式字符串为“yyyy-MM-dd HH:mm:ss”
	 * @param time
	 * @return
	 */
	public  static String getFormatTime(Date time){
		return  getFormatTime(time,TIME_FORMAT);
	}

	/**
	 *
	 * @param time  Date类型时间
	 * @param format  指定格式化字符串，如"yyyy-MM-dd HH:mm:ss.SSS"
	 * @return
	 */
	public  static String getFormatTime(Date time, String format){
		return getFormatTime(time.toInstant(), format);
	}

	/**
	 * 时间格式化
	 *
	 * @param instant  instant 类型时间
	 * @param format  指定格式化字符串，如"yyyy-MM-dd HH:mm:ss.SSS"
	 * @return
	 */
	public static String getFormatTime(Instant instant, String format){
		return  DateTimeFormatter.ofPattern(format).withZone(defZoneId).format(instant);
	}

	/**
	 * 时间格式化
	 *
	 * @param instant  instant 类型时间
	 * @param format  指定格式化字符串，如"yyyy-MM-dd HH:mm:ss.SSS"
	 * @return
	 */
	public static String getFormatTime(Instant instant, String format, ZoneId zoneId, Locale locale){
		return  DateTimeFormatter.ofPattern(format).withZone(zoneId).withLocale(locale).format(instant);
	}



	/**
	 * 获取后一天，如当前时间为2017-07-24 17:28:15， 则返回时间为2017-07-25 17:28:15
	 * @param date
	 * @return
	 */
	public static Date getPreDate(Date date) {
		return Date.from(date.toInstant().plus(1L, ChronoUnit.DAYS));
	}

	/**
	 * 获取前一天,如当前时间为2017-07-24 17:28:15， 则返回时间为2017-07-23 17:28:15
	 * @param date
	 * @return
	 */
	public static Date getLastDate(Date date) {
		return Date.from(date.toInstant().minus(1L, ChronoUnit.DAYS));
	}


	/**
	 * 比较开始日期是否小于等于结束日期,true则是小于等于
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static boolean compareStartEndDate(Date startDate, Date endDate) {
		if (startDate!=null && endDate!= null && startDate.getTime() <= endDate.getTime()) {
			return true;
		}
		return false;
	}


	/**
	 * 将默认格式字符串转换为时间
	 * @param str
	 * @return
	 */
	public static Date  getDateByStr(String str){
		return getDateByStr(str, TIME_FORMAT);
	}

	/**
	 *将字符串转换为指定格式的时间
	 * @param str
	 * @param format
	 * @return
	 */
	public static Date  getDateByStr(String str, String  format ){
		if(str == null || "".equals(str)){
			return null;
		}

		DateFormat dateFormat = new SimpleDateFormat(format);
		Date date = null;
		try {
			date = dateFormat.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * 指定时间的最小时间00:00:00，如返回2017-07-24 00:00:00
	 * @param time long类型参数
	 * @return
	 */
	public static Date  getDayOfMinTime(long time){
		LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(time), defZoneId);
		return Date.from(ZonedDateTime.of(localDateTime.toLocalDate(), LocalTime.MIN, defZoneId).toInstant());
	}


	/**
	 * 指定时间的最小时间00:00:00，如返回2017-07-24 00:00:00
	 * @param date Date 类型参数
	 * @return
	 */
	public static Date  getDayOfMinTime(Date date){
		LocalDateTime localDateTime = LocalDateTime.ofInstant(date.toInstant(), defZoneId);
		return Date.from(ZonedDateTime.of(localDateTime.toLocalDate(), LocalTime.MIN, defZoneId).toInstant());
	}

	/**
	 * 指定时间的最大时间23:59:59，如返回2017-07-24 23:59:59
	 * @param time
	 * @return
	 */
	public static Date getDayOfMaxTime(long time){
		LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(time), defZoneId);
		return Date.from(ZonedDateTime.of(localDateTime.toLocalDate(), LocalTime.MAX, defZoneId).toInstant());
	}

	/**
	 * 指定时间的最大时间23:59:59，如返回2017-07-24 23:59:59
	 * @param date
	 * @return
	 */
	public static Date  getDayOfMaxTime(Date date){
		LocalDateTime localDateTime = LocalDateTime.ofInstant(date.toInstant(), defZoneId);
		return Date.from(ZonedDateTime.of(localDateTime.toLocalDate(), LocalTime.MAX, defZoneId).toInstant());
	}

	//将时间精确到秒
	public  static Timestamp getSecondScaleTime(long time){
		return new Timestamp(time / 1000 * 1000);

	}

	public  static int getCurrentHour(long time){
		return Instant.ofEpochMilli(time).get(ChronoField.HOUR_OF_DAY);
	}

	/**
	 * 获得日期对应的的月份的最小时间
	 *
	 * @author tangsuwen 2016年5月9日 上午11:24:30
	 * @param time
	 * @return
	 */
	public static Date getMonthFistDayDate(long time) {
		return Date.from(Instant.ofEpochSecond(time).with(TemporalAdjusters.firstDayOfNextMonth()));
	}

	/**
	 * 获取两个日期之间的所有日期（包括开始日期和结束日期）
	 *
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static List<Date> getDayDateList(Date startDate, Date endDate) {
		List<Date> dateList = new ArrayList<Date>();
		Calendar eachCalendar = Calendar.getInstance();
		eachCalendar.setTime(startDate);
		while (eachCalendar.getTime().compareTo(endDate) <= 0) {
			dateList.add(eachCalendar.getTime());
			eachCalendar.add(Calendar.DAY_OF_MONTH, 1);
		}
		return dateList;
	}

	/**
	 * 获取两个日期之间的所有月的日期（包括开始日期和结束日期）
	 *
	 * @author tangsuwen 2016年5月9日 上午10:57:05
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public static List<Date> getMonthDateList(long startTime, long endTime) {
		List<Date> dateList = new ArrayList<Date>();
		Date startDateTime = getMonthFistDayDate(startTime);
		Date endDateTime = getMonthFistDayDate(endTime);
		Calendar eachCalendar = Calendar.getInstance();
		eachCalendar.setTime(startDateTime);
		while (eachCalendar.getTime().compareTo(endDateTime) <= 0) {
			dateList.add(eachCalendar.getTime());
			eachCalendar.add(Calendar.MONTH, 1);
		}
		return dateList;
	}

	/**
	 * 添加分钟
	 *
	 * @param date
	 * @param amount
	 * @return
	 */
	public static Date addMinutes(Date date, int amount) {
		return Date.from(date.toInstant().plus(amount, ChronoUnit.MINUTES));
	}

	/**
	 * 增加天
	 *
	 * @param date
	 *            传入时间
	 * @param amount
	 *            增量
	 * @return 时间
	 */
	public static Date addDays(Date date, int amount) {
		return Date.from(date.toInstant().plus(amount, ChronoUnit.DAYS));
	}

	public static Date addMonths(Date date, int amount) {
		return Date.from(date.toInstant().plus(amount, ChronoUnit.MONTHS));
	}


	public static Date addYears(Date date, int amount) {
		return Date.from(date.toInstant().plus(amount, ChronoUnit.YEARS));
	}


	/**
	 * 去掉分秒后，只看小时差 比如：2015/06/07 11:15:16 与 2015/06/07 13:10:44 返回结果为13-11 = 2
	 *
	 * @author tangsuwen 2016年5月5日 下午3:19:36
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public static long getBetweenHour(long startTime, long endTime) {
		Instant startInstant = Instant.ofEpochMilli(startTime);
		Instant endInstant = Instant.ofEpochMilli(endTime);
		return Duration.between(startInstant, endInstant).toHours();
	}

	/**
	 * 获得日期对应的的年份的最小时间
	 *
	 * @author tangsuwen 2016年5月9日 上午11:24:30
	 * @param time
	 * @return
	 */
	public static Date getYearFistDayDate(long time) {
		return Date.from(Instant.ofEpochMilli(time).with(TemporalAdjusters.firstDayOfYear()));
	}

	public static int getWeekOfYear(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setFirstDayOfWeek(Calendar.MONDAY);
		calendar.setTime(date);
		return calendar.get(Calendar.WEEK_OF_YEAR);
	}

	public static int getYear(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_yyyy);
		String year = sdf.format(date);
		return Integer.parseInt(year);
	}

	/**
	 * 获取时间段内的所有日期
	 *
	 * @author 徐立军 2016年5月5日 下午3:19:49
	 * @param start
	 * @param end
	 * @return
	 */
	public static Date[] getDateArrays(Date start, Date end) {
		List<Date> ret = new ArrayList<Date>();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(start);
		Date tmpDate = calendar.getTime();
		long endTime = end.getTime();
		while (tmpDate.before(end) || tmpDate.getTime() == endTime) {
			ret.add(calendar.getTime());
			calendar.add(Calendar.DAY_OF_MONTH, 1);
			tmpDate = calendar.getTime();
		}
		Date[] dates = new Date[ret.size()];
		return (Date[]) ret.toArray(dates);
	}

	/**
	 * 获取时间段内的所有月
	 *
	 * @author 徐立军 2016年5月5日 下午6:12:30
	 * @param start
	 * @param end
	 * @return
	 */
	public static String[] getMonthsStr(Date start, Date end) {
		List<String> ret = new ArrayList<String>();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(start);
		int year = calendar.get(Calendar.YEAR);
		int tmpMonth = calendar.get(Calendar.MONTH) + 1;
		calendar.setTime(end);
		long endMonth = calendar.get(Calendar.MONTH) + 1;
		while (tmpMonth <= endMonth) {
			if (tmpMonth < 10) {
				ret.add(year + "-0" + tmpMonth);
			} else {
				ret.add(year + "-" + tmpMonth);
			}
			tmpMonth++;
		}
		String[] months = new String[ret.size()];
		return (String[]) ret.toArray(months);
	}

	/**
	 * 当年第一天开始时间
	 *
	 * @author 徐立军 2016年5月4日 下午2:38:02
	 * @param year
	 * @return
	 */
	public static Date getNowYearFirstDay(int year) {
		LocalDate localDate = LocalDate.of(year,Month.JANUARY, 1);
		return Date.from((localDate.atStartOfDay().atZone(defZoneId).toInstant()));
	}

	/**
	 * 当前星期第一天开始时间 精确到秒 "00:00:00"
	 *
	 * @author 徐立军 2016年5月4日 下午2:31:19
	 * @param date
	 *            --传入时间
	 * @return
	 */
	public static Date getNowWeekBegin(Date date) {
		return Date.from((date.toInstant().with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY))));
	}

	/**
	 * 当前月第一天
	 *
	 * @param date
	 *            当前时间
	 * @return 时间
	 */
	public static Date getNowMonthFirstDay(Date date) {
		return Date.from((date.toInstant().with(TemporalAdjusters.firstDayOfMonth())));
	}

	/**
	 * 当前月最后一天
	 *
	 * @param date
	 *            传入时间
	 * @return 时间
	 */
	public static Date getNowMonthLastDay(Date date) {
		return Date.from((date.toInstant().with(TemporalAdjusters.lastDayOfMonth())));
	}

	/**
	 * 计算季的第一天时间
	 *
	 * @author xulijun 2016年4月27日 下午7:54:44
	 * @param year
	 *            --年
	 * @param season
	 *            --季（要求1-4之间的数字）
	 * @return
	 */
	public static Date getSeasonFirstDay(int year, int season) {
		try {
			season--;
			Calendar cal = Calendar.getInstance();
			cal.set(Calendar.YEAR, year);
			cal.set(Calendar.MONTH, SEASON_BEGIN[season]);// 格里高利历和罗马儒略历中一年中的第一个月是
			// JANUARY，它为 0
			cal.set(Calendar.DAY_OF_MONTH, 1);
			cal.set(Calendar.HOUR_OF_DAY, 0);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);
			return cal.getTime();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}


	/**
	 * 计算季的最后一天时间
	 *
	 * @author xulijun 2016年4月27日 下午7:56:21
	 * @param year
	 *            --年
	 * @param season
	 *            --季（要求1-4之间的数字）
	 * @return
	 */
	public static Date getSeasonLastDay(int year, int season) {
		try {
			// 计算季的最后一个月，格里高利历和罗马儒略历中一年中的第一个月是 JANUARY，它为 0，此处-1
			int mon = season * 3 - 1;
			Calendar cal = Calendar.getInstance();
			cal.set(Calendar.YEAR, year);
			cal.set(Calendar.MONTH, mon);
			cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
			cal.set(Calendar.HOUR_OF_DAY, 23);
			cal.set(Calendar.MINUTE, 59);
			cal.set(Calendar.SECOND, 59);
			return cal.getTime();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 当前年最后一天
	 *
	 * @param date
	 *            传入时间
	 * @return 时间
	 */
	public static Date getNowYearLastDay(Date date) {
		return Date.from((date.toInstant().with(TemporalAdjusters.lastDayOfYear())));
	}

	/**
	 * 获取两个日期之间的所有年的日期（包括开始日期和结束日期）
	 *
	 * @author tangsuwen 2016年5月9日 上午10:57:05
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public static List<Date> getYearDateList(long startTime, long endTime) {
		List<Date> dateList = new ArrayList<Date>();
		Date startDateTime = getYearFistDayDate(startTime);
		Date endDateTime = getYearFistDayDate(endTime);
		Calendar eachCalendar = Calendar.getInstance();
		eachCalendar.setTime(startDateTime);
		while (eachCalendar.getTime().compareTo(endDateTime) <= 0) {
			dateList.add(eachCalendar.getTime());
			eachCalendar.add(Calendar.YEAR, 1);
		}
		return dateList;
	}

	public static String getFormatTimeHour(long time) {
		return getFormatTime(time, TIME_DATE_HOUR_FORMAT);
	}

	public static String getMonthStrChinese(String dateStr) {
		Date date = getDateByStr(dateStr, DATE_FORMAT_yyyy_MM);
		if(null == date){
			return "";
		}
		return getFormatTime(date, FORMAT_DATE_MONTH_CHINESE);
	}

	public static String getFormatDateYMDdefaultEmpty(Date date) {
		return getFormatDateYMD(date, StringUtils.EMPTY);
	}

	/**
	 *
	 * 将时间格式化成 yyyy-MM-dd 格式，如果时间为null， 则返回 defaultStr.
	 *
	 * @author denggonghai 2016年8月4日 上午11:07:37.
	 * @param date
	 *            需要格式化的时间.
	 * @param defaultStr
	 *            默认返回值.
	 * @return 如果需要格式化的时间为null，则返回默认值.
	 */
	public static String getFormatDateYMD(Date date, String defaultStr) {
		return date == null ? defaultStr : getFormatTime(date, DATE_FORMAT_yyyy_MM_dd);
	}

	public static String getFormatTimeNoMsDefaultEmpty(Date time) {
		return getFormatTimeNoMs(time, StringUtils.EMPTY);
	}

	/**
	 * 将时间格式化成 yyyy-MM-dd HH:mm:ss 格式，如果为null 则返回 defaultStr.
	 *
	 * @author denggonghai 2016年8月4日 上午10:46:15.
	 * @param time
	 *            需要格式化的时间.
	 * @param defaultStr
	 *            默认返回的字符串.
	 * @return 如果time为null， 则默认返回 defaultStr.
	 */
	public static String getFormatTimeNoMs(Date time, final String defaultStr) {
		return time == null ? defaultStr : getFormatTime(time, TIME_FORMAT);
	}

	/**
	 * 取得某月的第一天
	 * @param year
	 * @param month
	 * @return
	 */
	public static Date getMonthFirstDay(int year, int month){
		YearMonth yearMonth = YearMonth.of(year, month);
		return Date.from(yearMonth.atDay(1).atStartOfDay(defZoneId).toInstant());
	}

	/**
	 *
	 * @param timeFormat 时间格式
	 * @param zoneStr 时区
	 * @param locale 显示格式
	 * @return
	 */
	public static String getNowTimeWithTimeZone(String timeFormat, String zoneStr, Locale locale){
		return getFormatTime(Instant.now(), timeFormat, ZoneId.of(zoneStr), locale);
	}


}
