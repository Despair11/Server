package org.jeecg.modules.bigscreen.dto.house;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * description:
 * author: Zhang Yajun
 * date: 2021/1/6 9:46
 * version: 1.0
 */
@TableName(value = "luci_house_agritainment")
@Data
public class Agritainment {

    @TableId(value = "aid")
    private int aid;
    //账户名称
    private String designation;
    //姓名
    private String name;
    //房间数量
    private int counts;
    //价格/间
    private int price;
    //昨日预定
    private int lastReserve;
    //今日预定
    private int todayReserve;
    //今日盈利
    private int todayEarn;
    //本月预定
    private int monthReserve;
    //本月盈利
    private int monthEarn;
    //入住率
    private int checkInPercent;
}
