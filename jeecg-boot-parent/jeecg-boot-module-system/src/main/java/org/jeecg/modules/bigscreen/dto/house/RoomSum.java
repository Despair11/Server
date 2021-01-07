package org.jeecg.modules.bigscreen.dto.house;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * description:芦茨民宿列表实体
 * author: Zhang Yajun
 * date: 2021/1/5 11:25
 * version: 1.0
 */
@TableName(value = "luci_house_count")
@Data
public class RoomSum {
    @TableId(value = "cid")
    private int cid;
    private long homeCount;
    private long houseCount;
    private long emptyHouse;
    private int checkInPercent;
    private int emptyPercent;
    private int fullPercent;
}
