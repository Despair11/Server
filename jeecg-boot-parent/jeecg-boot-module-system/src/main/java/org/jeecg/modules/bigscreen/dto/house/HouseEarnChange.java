package org.jeecg.modules.bigscreen.dto.house;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * description:民宿收益变化实体
 * author: Zhang Yajun
 * date: 2021/1/5 15:58
 * version: 1.0
 */
@TableName(value = "luci_house_earn_change")
@Data
public class HouseEarnChange {
    @TableId(value = "eid")
    private int eid;
    private int jan;
    private int feb;
    private int mar;
    private int apr;
    private int may;
    private int jun;
    private int jul;
    private int aug;
    private int sep;
    private int oct;
    private int nov;
    private int dece;
}
