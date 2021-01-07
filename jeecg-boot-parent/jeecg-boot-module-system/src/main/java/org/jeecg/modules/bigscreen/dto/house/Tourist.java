package org.jeecg.modules.bigscreen.dto.house;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * description:游客画像实体
 * author: Zhang Yajun
 * date: 2021/1/5 18:02
 * version: 1.0
 */
@TableName(value = "luci_house_tourist")
@Data
public class Tourist {
    @TableId(value = "tid")
    private int tid;
    private long zero;
    private long ten;
    private long thirty;
    private long sixty;
    private int zeroPercent;
    private int tenPercent;
    private int thirtyPercent;
    private int sixtyPercent;
    private long male;
    private long female;
    private int malePercent;
    private int femalePercent;
    private int lowConsume;
    private int midConsume;
    private int heighConsume;
}
