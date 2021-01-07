package org.jeecg.modules.bigscreen.dto.villageTask;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * description:
 * author: Zhang Yajun
 * date: 2021/1/6 16:39
 * version: 1.0
 */
@TableName(value = "luci_village_volunteer")
@Data
public class VillageGlory {
    @TableId(value = "vid")
    private int vid;
    private String name;
    private int excellent;
}
