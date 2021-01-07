package org.jeecg.modules.bigscreen.service.house.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.modules.bigscreen.dto.house.*;
import org.jeecg.modules.bigscreen.mapper.*;
import org.jeecg.modules.bigscreen.service.house.HouseEarnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * description:
 * author: Zhang Yajun
 * date: 2021/1/5 10:08
 * version: 1.0
 */
@Service
@Slf4j
@Transactional
public class HouseEarnServiceImpl implements HouseEarnService {

    @Autowired
    HouseEarnMapper houseEarnMapper;

    @Autowired
    HouseCountMapper houseCountMapper;

    @Autowired
    CheckInfoMapper checkInfoMapper;

    @Autowired
    CheckInfoChangeMapper checkInfoChangeMapper;

    @Autowired
    HouseEarnChangeMapper houseEarnChangeMapper;

    @Autowired
    TouristMapper touristMapper;

    @Autowired
    AgritainmentMapper agritainmentMapper;

    /**
     * 民宿收益相关查询
     * @return org.jeecg.modules.bigscreen.dto.house.LuciHouseEarn
     */
    @Override
    public LuciHouseEarn earning() {
        LuciHouseEarn luciHouseEarn = null;
        try {
            luciHouseEarn = houseEarnMapper.selectOne(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return luciHouseEarn;
    }

    /**
     * 民宿数量查询
     * @return org.jeecg.modules.bigscreen.dto.house.RoomSum
     */
    @Override
    public RoomSum houseCount() {
        RoomSum roomSum = null;
        try {
            roomSum = houseCountMapper.selectOne(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return roomSum;
    }

    /**
     * 入住大屏数据查询
     * @return org.jeecg.modules.bigscreen.dto.house.CheckInfo
     */
    @Override
    public CheckInfo checkIn() {
        CheckInfo checkInfo = null;
        try {
            checkInfo = checkInfoMapper.selectOne(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return checkInfo;
    }

    /**
     * 入住数据变化趋势查询
     * @return org.jeecg.modules.bigscreen.dto.house.CheckInfoChange
     */
    @Override
    public List<CheckInfoChange> checkInChange() {
        List<CheckInfoChange> checkInfoChanges = null;
        try {
            checkInfoChanges = checkInfoChangeMapper.selectList(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return checkInfoChanges;
    }

    /**
     * 民宿收益变化趋势查询
     * @return org.jeecg.modules.bigscreen.dto.house.HouseEarnChange
     */
    @Override
    public List<HouseEarnChange> houseEarnChange() {
        List<HouseEarnChange> houseEarnChange = null;
        try {
            houseEarnChange = houseEarnChangeMapper.selectList(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return houseEarnChange;
    }

    /**
     * "游客画像"信息查询
     * @return org.jeecg.modules.bigscreen.dto.house.Tourist
     */
    @Override
    public Tourist checkTourist() {
        Tourist tourist = null;
        try {
            tourist = touristMapper.selectOne(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tourist;
    }

    /**
     * 行业对比民宿排行-按人均
     * @return
     */
    @Override
    public List<Agritainment> findAllByPrice() {
        List<Agritainment> list = null;
        try {
            list = agritainmentMapper.selectList(new QueryWrapper<Agritainment>()
                    .last("limit 5")
                    .orderByAsc("price"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 排行榜查询-按入住
     * @return
     */
    @Override
    public List<Agritainment> findAllByCheckIn() {
        List<Agritainment> list = null;
        try {
            list = agritainmentMapper.selectList(new QueryWrapper<Agritainment>()
                    .last("limit 10")
                    .orderByDesc("check_In_percent"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
