package org.jeecg.modules.xjdatascreen.config;

/**
 * @author Edge
 * @version 1.0
 * @date 2020/11/19 10:05
 */
public class RedisKeyConfig {
    //有效时间
    public static final int effective_time = 24 * 60 *60;
    //酒店相关key
    public static final String wine_shop_key = "wine:shop:key";
    //游客折线相关key
    public static final String tourist_member_key = "tourist:member:key";
    //游客国庆数据key
    public static final String tourist_national_day_key = "tourist:national:day:key";
    //总游客数据key
    public static final String sum_tourist_member_key = "sum:tourist:member:key";
    //旅游收入数据key
    public static final String tourism_income_key = "tourism:income:key";
}
