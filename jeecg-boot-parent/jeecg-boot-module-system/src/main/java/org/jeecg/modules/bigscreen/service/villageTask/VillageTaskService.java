package org.jeecg.modules.bigscreen.service.villageTask;

import org.jeecg.modules.bigscreen.dto.villageTask.VillageGlory;
import org.jeecg.modules.bigscreen.dto.villageTask.VillagePopulation;
import org.jeecg.modules.bigscreen.dto.villageTask.VillagePopulationChange;

import java.util.List;

/**
 * description:
 * author: Zhang Yajun
 * date: 2021/1/6 14:54
 * version: 1.0
 */
public interface VillageTaskService {

    /**
     * 村务模块-人口统计查询
     * @return org.jeecg.modules.bigscreen.dto.villageTask.VillagePopulation
     */
    VillagePopulation checkPopulation();

    /**
     * 村务-人口数量变化
     * @return org.jeecg.modules.bigscreen.dto.villageTask.VillagePopulationChange
     */
    List<VillagePopulationChange> checkPopulationChange();

    /**
     * 光荣榜
     * @return
     */
    List<VillageGlory> checkGlory();
}
