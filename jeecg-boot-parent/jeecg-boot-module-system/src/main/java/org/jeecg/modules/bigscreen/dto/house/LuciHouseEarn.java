package org.jeecg.modules.bigscreen.dto.house;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * description:芦茨民宿模块收益实体
 * author: Zhang Yajun
 * date: 2021/1/5 10:03
 * version: 1.0
 */
@TableName(value = "luci_house_earn")
@Data
public class LuciHouseEarn {
    @TableId(value = "hid")
    private int hid;
    private long todayEarn;
    private long monthEarn;
    private long yearEarn;
    private long lastyearEarn;
}
