package org.jeecg.modules.xjdatascreen.datatickpassengers.mapper;

import org.apache.ibatis.annotations.Param;
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
}
