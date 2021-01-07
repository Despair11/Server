package org.jeecg.modules.bigscreen.dto.villageTask;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * description:
 * author: Zhang Yajun
 * date: 2021/1/6 15:07
 * version: 1.0
 */
@Data
@TableName(value = "luci_village_population_change")
public class VillagePopulationChange {
    @TableId(value = "pid")
    private int pid;
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
