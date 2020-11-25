package org.jeecg.modules.xjdatascreen.datatickpassengers.mapper;

import org.apache.ibatis.annotations.Param;
import org.jeecg.modules.xjdatascreen.dataticketorderdetail.entity.TicketOrderDetail;
import org.jeecg.modules.xjdatascreen.datatickpassengers.entity.TickPassengers;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.jeecg.modules.xjdatascreen.datatouristmember.entity.DataTouristMember;

import java.util.List;

/**
 * @Description: tick_passengers
 * @Author: jeecg-boot
 * @Date:   2020-11-24
 * @Version: V1.0
 */
public interface TickPassengersMapper extends BaseMapper<TickPassengers> {

    /**
     * 获取分景区(游客与年龄)数据
     * @param scenicNum 景区code
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return
     */
    List<DataTouristMember> selectAll(@Param("scenicNum") String scenicNum,
                                      @Param("startTime") String startTime,
                                      @Param("endTime") String endTime);


    /**
     * 获取各个景区的省内游客接待数据
     * @param scenicNum
     * @param startTime
     * @param endTime
     * @return
     */
    List<TicketOrderDetail> selectTouristAll(@Param("scenicNum") String scenicNum,
                                             @Param("startTime") String startTime,
                                             @Param("endTime") String endTime,
                                             @Param("days") String days);

    /**
     * 获取各个景区的省外游客接待数据
     * @param scenicNum
     * @param startTime
     * @param endTime
     * @return
     */
    List<TicketOrderDetail> selectTouristAllOutsideTheProvince(@Param("scenicNum") String scenicNum,
                                             @Param("startTime") String startTime,
                                             @Param("endTime") String endTime,
                                             @Param("days") String days);

    /**
     * 获取每个景区今日的游玩人数据
     * @param scenicNum
     * @return
     */
    List<TicketOrderDetail> selectSumTourist(@Param("scenicNum") String scenicNum);


    /**
     * 获取每个景观每年的游玩人数据
     * @param scenicNum
     * @return
     */
    List<TicketOrderDetail> selectSumTouristYear(@Param("scenicNum") String scenicNum);
    /**
     * 获取各景区上个月游客数据
     * @param scenicNum
     * @param mouths
     * @return
     */
    List<TicketOrderDetail> lastMonthVisitorData(@Param("scenicNum") String scenicNum,
                                                 @Param("mouths") String mouths);

    /**
     * 获取每个景区去年游客数据
     * @param scenicNum
     * @param years
     * @return
     */
    List<TicketOrderDetail> lastMonthVisitorYearData(@Param("scenicNum") String scenicNum,
                                                     @Param("years") String years);


    /**
     * 获取每个景点每年的数据
     * @param scenicNum
     * @param years
     * @return
     */
    List<TicketOrderDetail> selectGalaData(@Param("scenicNum") String scenicNum,
                                           @Param("years") String years,
                                           @Param("startTime") String startTime,
                                           @Param("endTime") String endTime,
                                           @Param("days") String days);

}
