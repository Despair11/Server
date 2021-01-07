package org.jeecg.modules.bigscreen.dto.villageTask;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * description:村务-人口查询
 * author: Zhang Yajun
 * date: 2021/1/6 14:50
 * version: 1.0
 */
@TableName(value = "luci_village_task")
@Data
public class VillagePopulation {
    @TableId(value = "pid")
    private int pid;
    //总人口
    private long totalPopulation;
    //本地居民
    private long localPeople;
    //外来人口
    private long foreignPeople;
    //低保人员
    private long lowQualityPeople;
    //党员人员
    private long partyMember;
    //支援之家
    private long volunteer;
    //下一个模块中总增长数量展示
    private long totalIncrement;
}
