package org.jeecg.modules.xjdatascreen.utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author Edge
 * @version 1.0
 * @date 2020/11/19 10:14
 */
public class DateUtil {
    //获取连续天数时间段
    public static List<String> getDays(String dStart, String dEnd) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        List<String> dateList = null;
        try {
            Calendar cStart = Calendar.getInstance();
            cStart.setTime(sdf.parse(dStart));
            dateList = new ArrayList<String>();
            //别忘了，把起始日期加上
            dateList.add(dStart);
            // 此日期是否在指定日期之后
            while (sdf.parse(dEnd).after(cStart.getTime())) {
                // 根据日历的规则，为给定的日历字段添加或减去指定的时间量
                cStart.add(Calendar.DAY_OF_MONTH, 1);
                dateList.add(sdf.format(cStart.getTime()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dateList;
    }

    //获取月份时间段
    public static List<String> getMouths(String start,String end){
        List<String> date=new ArrayList<String>();
        try{
            Date d1 = new SimpleDateFormat("yyyy-MM").parse(start);//定义起始日期

            Date d2 = new SimpleDateFormat("yyyy-MM").parse(end);//定义结束日期

            Calendar dd = Calendar.getInstance();//定义日期实例

            dd.setTime(d1);//设置日期起始时间

            while (dd.getTime().before(d2)) {//判断是否到结束日期

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");

                String str = sdf.format(dd.getTime());

                date.add(str);

                dd.add(Calendar.MONTH, 1);//进行当前日期月份加1

            }
            date.add(end);;//输出日期结果

        }catch (Exception e){
            System.out.println("异常"+e.getMessage());
        }
        return date;
    }

    public static void main(String[] args) {
//        List<String> dates = getDays("2020-08-08", "2020-11-19");
//        System.out.println(dates.toString());
        System.out.println(getMouths("2020-01","2020-09"));
    }


}
