package org.jeecg.modules.bigscreen.service.house;

import org.jeecg.modules.bigscreen.dto.house.*;

import java.util.List;

/**
 * description:
 * author: Zhang Yajun
 * date: 2021/1/5 10:07
 * version: 1.0
 */
public interface HouseEarnService {

    /**
     * 民宿收益相关查询
     * @return org.jeecg.modules.bigscreen.dto.house.LuciHouseEarn
     */
    LuciHouseEarn earning();

    /**
     * 名宿数量查询
     * @return org.jeecg.modules.bigscreen.dto.house.RoomSum
     */
    RoomSum houseCount();

    /**
     * 入住数据查询
     * @return org.jeecg.modules.bigscreen.dto.house.CheckInfo
     */
    CheckInfo checkIn();

   /**
    * 入住数据变化
    * @return org.jeecg.modules.bigscreen.dto.house.CheckInfoChange
    */
    List<CheckInfoChange> checkInChange();

   /**
    * 民宿收益变化
    * @return org.jeecg.modules.bigscreen.dto.house.HouseEarnChange
    */
    List<HouseEarnChange> houseEarnChange();

    /**
     * 查询游客信息
     * @return org.jeecg.modules.bigscreen.dto.house.Tourist
     */
    Tourist checkTourist();

    /**
     * 查询所有民宿ByPrice
     * @return java.util.List<org.jeecg.modules.bigscreen.dto.house.Agritainment>
     */
    List<Agritainment> findAllByPrice();

    /**
     * 查询所有民宿ByCheckIn
     * @return java.util.List<org.jeecg.modules.bigscreen.dto.house.Agritainment>
     */
    List<Agritainment> findAllByCheckIn();
}
