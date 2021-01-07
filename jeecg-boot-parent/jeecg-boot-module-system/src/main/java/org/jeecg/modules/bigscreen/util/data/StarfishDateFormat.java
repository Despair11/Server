package org.jeecg.modules.bigscreen.util.data;

import com.fasterxml.jackson.core.io.NumberInput;
import com.fasterxml.jackson.databind.util.StdDateFormat;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

import java.text.FieldPosition;
import java.text.ParseException;
import java.text.ParsePosition;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * 自定义时间转换格式
 * 兼容以下格式：
 * 1.ISO8601时间格式:
 *     yyyy-MM-dd'T'HH:mm:ss.SSSXXX -->2004-05-03T17:30:08+08:00
 * 	   yyyy-MM-dd'T'HH:mm:ss.SSSZ -->2004-05-03T17:30:08.000Z
 * 	   yyyy-MM-dd'T'HH:mm:ssZ -->2004-05-03T17:30:08Z
 * 2.普通时间格式：
 *      yyyy-MM-dd HH:mm:ss
 *      yyyy-MM-dd
 * 3.long类型的时间
 * 4.RFC1123格式
 *     EEE, dd MMM yyyy HH:mm:ss zzz
 * @author gonghao 2019/1/7 22:52
 * @since 1.0.0
 */
public class StarfishDateFormat extends StdDateFormat implements  Cloneable{

    private static final char T = 'T';
    private static final char HYPHEN = '-';
    private static final char COLON = ':';

    /**
     * 默认设置：时区为当前时区，locale为简体中文，宽松时间转换，格式化tz
     * @author gonghao 2019/1/8 10:53
     * @param
     * @return
     * @since 1.0.0
     */
    public StarfishDateFormat(){
        super(TimeZone.getDefault(),Locale.US,true,true);
    }

    public StarfishDateFormat(TimeZone tz, Locale locale){
        super(tz,locale,true,true);
    }

    @Override
    public Date parse(String dateStr) throws ParseException {
        dateStr = dateStr.trim();
        ParsePosition pos = new ParsePosition(0);
        Date dt = localParseDate(dateStr, pos);
        if (dt != null) {
            return dt;
        }
        StringBuilder sb = new StringBuilder();
        for (String f : ALL_FORMATS) {
            if (sb.length() > 0) {
                sb.append("\", \"");
            } else {
                sb.append('"');
            }
            sb.append(f);
        }
        sb.append('"');
        throw new ParseException
                (String.format("Cannot parse date \"%s\": not compatible with any of standard forms (%s)",
                        dateStr, sb.toString()), pos.getErrorIndex());
    }

    @Override
    public Date parse(String dateStr, ParsePosition pos) {
        try {
            return localParseDate(dateStr, pos);
        } catch (ParseException e) {
            // may look weird but this is what `DateFormat` suggest to do...
        }
        return null;
    }

    @Override
    public StringBuffer format(Date date, StringBuffer toAppendTo,
                               FieldPosition fieldPosition) {
        TimeZone tz = _timezone;
        if (tz == null) {
            tz = TimeZone.getDefault();
        }
        _format(tz, _locale, date, toAppendTo);
        return toAppendTo;
    }

    //add by gonghao 20190110 增加clone方法，否则将导致依然会拿到StdDateFormat对象
    @Override
    public StarfishDateFormat clone() {
        // Although there is that much state to share, we do need to
        // orchestrate a bit, mostly since timezones may be changed
        super.clone();
        return new StarfishDateFormat(_timezone,_locale);
    }

    private Date localParseDate(String dateStr, ParsePosition pos) throws ParseException {

        //判断是否为ISO8601格式的时间，这里约定是iso8601时间格式，所以长度大于19，且包含T
        if (dateStr.length() >= 19 && dateStr.charAt(10) == T) {
            return parseISO8601(dateStr, pos);
        }

        //普通的时间格式转换 yyyy-MM-dd
        if (dateStr.length() == 10 && dateStr.charAt(4) == HYPHEN && dateStr.charAt(7) == HYPHEN){
            return DateUtil.getDateByStr(dateStr, DateUtil.DATE_FORMAT_yyyy_MM_dd);
        }

        //yyyy-MM-dd HH:mm:ss格式
        if (dateStr.length() == 19 && dateStr.charAt(4) == HYPHEN
                && dateStr.charAt(7) == HYPHEN && dateStr.charAt(13) == COLON){
            return DateUtil.getDateByStr(dateStr);
        }

        //String 类型的long时间
        int i = dateStr.length();
        while (--i >= 0) {
            char ch = dateStr.charAt(i);
            if (ch < '0' || ch > '9') {
                // 07-Aug-2013, tatu: And [databind#267] points out that negative numbers should also work
                if (i > 0 || ch != '-') {
                    break;
                }
            }
        }
        if ((i < 0)
                // let's just assume negative numbers are fine (can't be RFC-1123 anyway); check length for positive
                && (dateStr.charAt(0) == '-' || NumberInput.inLongRange(dateStr, false))) {
            return parseDateFromLong(dateStr, pos);
        }

        // Otherwise, fall back to using RFC 1123. NOTE: call will NOT throw, just returns `null`
        return parseAsRFC1123(dateStr, pos);
    }

    private Date parseISO8601(String dateStr, ParsePosition pos) {
        DateTimeFormatter formatter = ISODateTimeFormat.dateTimeParser();
        return formatter.parseDateTime(dateStr).toDate();
    }

    private Date parseDateFromLong(String longStr, ParsePosition pos) throws ParseException
    {
        long ts;
        try {
            ts = NumberInput.parseLong(longStr);
        } catch (NumberFormatException e) {
            throw new ParseException(String.format(
                    "Timestamp value %s out of 64-bit value range", longStr),
                    pos.getErrorIndex());
        }
        return new Date(ts);
    }

    public static void main(String[] args) throws ParseException {
        //String str = "2019-01-08T00:00:00+08:00";
        //String str = "2019-01-08T00:00:00.000Z";
        //String str = "2019-01-08T00:00:00Z";
        //String str = "2019-01-08 00:00:00";
        //String str = "2019-01-08";
        String str = "1546876800000";
        StarfishDateFormat dateFormat = new StarfishDateFormat();
        Date date = dateFormat.parse(str);

        System.out.println(dateFormat.format(date));

    }

}