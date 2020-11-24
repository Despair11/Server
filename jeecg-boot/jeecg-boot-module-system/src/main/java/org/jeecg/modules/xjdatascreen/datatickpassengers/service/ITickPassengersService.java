package org.jeecg.modules.xjdatascreen.datatickpassengers.service;

import org.jeecg.modules.xjdatascreen.datatickpassengers.entity.TickPassengers;
import com.baomidou.mybatisplus.extension.service.IService;
import org.jeecg.modules.xjdatascreen.datatickpassengers.entity.TickPassengersVO;

import java.util.List;
import java.util.Map;

/**
 * @Description: tick_passengers
 * @Author: jeecg-boot
 * @Date:   2020-11-24
 * @Version: V1.0
 */
public interface ITickPassengersService extends IService<TickPassengers> {


    /**
     * 查询分景区游客年龄和性别数据
     * @param scenicName
     * @param startTime
     * @param endTime
     * @return
     */
    List<TickPassengersVO> selectAll(String scenicName, String startTime, String endTime);

}
