package org.jeecg.modules.bigscreen.service.villageTask.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.modules.bigscreen.dto.villageTask.VillageGlory;
import org.jeecg.modules.bigscreen.dto.villageTask.VillagePopulation;
import org.jeecg.modules.bigscreen.dto.villageTask.VillagePopulationChange;
import org.jeecg.modules.bigscreen.mapper.VillageGloryMapper;
import org.jeecg.modules.bigscreen.mapper.VillagePopulationChangeMapper;
import org.jeecg.modules.bigscreen.mapper.VillagePopulationMapper;
import org.jeecg.modules.bigscreen.service.villageTask.VillageTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * description:村务-人口查询
 * author: Zhang Yajun
 * date: 2021/1/6 14:55
 * version: 1.0
 */
@Service
@Slf4j
@Transactional
public class VillageTaskServiceImpl implements VillageTaskService {

    @Autowired
    VillagePopulationMapper villagePopulationMapper;

    @Autowired
    VillagePopulationChangeMapper villagePopulationChangeMapper;

    @Autowired
    VillageGloryMapper villageGloryMapper;

    @Override
    public VillagePopulation checkPopulation() {
        VillagePopulation villagePopulation = null;
        try {
            villagePopulation = villagePopulationMapper.selectOne(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return villagePopulation;
    }

    @Override
    public List<VillagePopulationChange> checkPopulationChange() {
        List<VillagePopulationChange> villagePopulationChanges = null;
        try {
            villagePopulationChanges = villagePopulationChangeMapper.selectList(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return villagePopulationChanges;
    }

    @Override
    public List<VillageGlory> checkGlory() {
        List<VillageGlory> villageGlories = null;
        try {
            villageGlories = villageGloryMapper.selectList(new QueryWrapper<VillageGlory>()
                    .last(" where excellent = 1 limit 5"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return villageGlories;
    }
}
