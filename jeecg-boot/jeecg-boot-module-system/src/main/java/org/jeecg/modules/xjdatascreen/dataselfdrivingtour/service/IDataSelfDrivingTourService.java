package org.jeecg.modules.xjdatascreen.dataselfdrivingtour.service;

import org.jeecg.modules.xjdatascreen.dataselfdrivingtour.entity.DataSelfDrivingTour;
import com.baomidou.mybatisplus.extension.service.IService;
import org.jeecg.modules.xjdatascreen.dataselfdrivingtour.entity.DataSelfDrivingTourVO;

import java.util.List;

/**
 * @Description: data_self_driving_tour
 * @Author: jeecg-boot
 * @Date:   2020-11-26
 * @Version: V1.0
 */
public interface IDataSelfDrivingTourService extends IService<DataSelfDrivingTour> {


    /**
     * 获取大屏汇总数据
     * @return
     */
    List<DataSelfDrivingTourVO> selectAll(String scenicName, String startTime, String endTime);




}
