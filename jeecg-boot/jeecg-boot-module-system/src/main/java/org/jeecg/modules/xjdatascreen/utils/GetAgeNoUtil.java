package org.jeecg.modules.xjdatascreen.utils;

import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Edge
 * @version 1.0
 * @date 2020/11/19 20:13
 */
public class GetAgeNoUtil {

    /**
     * 根据身份证号码计算年龄
     *
     * @param psptNo
     * @return
     */
    public static int getAgeByPsptNo(String psptNo) {
        if (StringUtils.isEmpty(psptNo)) {
            return 0;
        }
        String birthDay = psptNo.substring(6, 14);
        String time = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        String yearStr = time.split("-")[0];
        String monthStr = time.split("-")[1];
        String dayStr = time.split("-")[2];
        String yearBirthStr = birthDay.substring(0, 4);
        String monthBirthStr = birthDay.substring(4, 6);
        String dayBirthStr = birthDay.substring(6);
        int year = Integer.parseInt(yearStr);
        int yearBirth = Integer.parseInt(yearBirthStr);
        if (year - yearBirth <= 0) {
            return 0;
        }
        int age = year - yearBirth;
        int month = Integer.parseInt(monthStr);
        int monthBirth = Integer.parseInt(monthBirthStr);
        if (month - monthBirth > 0) {
            return age;
        }
        if (month - monthBirth < 0) {
            return --age;
        }
        int day = Integer.parseInt(dayStr);
        int dayBirth = Integer.parseInt(dayBirthStr);
        if (day - dayBirth >= 0) {
            return age;
        }
        return --age;
    }


    public static void main(String[] args) {
        System.out.println(getAgeByPsptNo("412326199605180999"));
    }

}
