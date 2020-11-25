package org.jeecg.modules.xjdatascreen.datatickpassengers.service;

import org.apache.ibatis.annotations.Param;
import org.jeecg.modules.xjdatascreen.dataordercheckrecord.entity.DataOrderCheckRecordVO;
import org.jeecg.modules.xjdatascreen.datatickpassengers.entity.TickPassengers;
import com.baomidou.mybatisplus.extension.service.IService;
import org.jeecg.modules.xjdatascreen.datatickpassengers.entity.TickPassengersVO;
import org.jeecg.modules.xjdatascreen.datatouristmember.entity.DataTouristMemberVO;

import java.util.List;


/**
 * @Description: tick_passengers
 * @Author: jeecg-boot
 * @Date:   2020-11-24
 * @Version: V1.0
 */
public interface ITickPassengersService extends IService<TickPassengers> {


    /**
     * 查询分景区游客年龄和性别数据(折线图)
     * @param scenicName
     * @param startTime
     * @param endTime
     * @return
     */
    List<TickPassengersVO> selectAll(String scenicName, String startTime, String endTime);

    /**
     * 获取各个景区的游客接待数据(折线图)
     * @param scenicName
     * @param startTime
     * @param endTime
     * @return
     */
    List<DataTouristMemberVO> selectTouristAll(String scenicName, String startTime, String endTime);

    /**
     * 获取每个景区的游玩人数据
     * @param scenicName
     * @return
     */
    List<DataOrderCheckRecordVO> selectSumTourist(String scenicName);


    /**
     * 获取每个景区每年的数据
     * @param scenicName
     * @param startTime
     * @param endTime
     * @return
     */
    List<DataTouristMemberVO> selectGalaData(String scenicName, String startTime, String endTime);

}
