package org.jeecg.modules.bigscreen.dto.house;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * description:入住数据实体
 * author: Zhang Yajun
 * date: 2021/1/5 15:08
 * version: 1.0
 */
@TableName(value = "luci_house_checkin")
@Data
public class CheckInfo {
    @TableId(value = "cid")
    private int cid;
    private int todayCheckIn;
    private int emptyHouse;
    private int emptyPercent;
}
