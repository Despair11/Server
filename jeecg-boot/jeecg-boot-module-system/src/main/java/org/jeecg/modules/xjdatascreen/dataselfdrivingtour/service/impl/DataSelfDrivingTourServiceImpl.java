package org.jeecg.modules.xjdatascreen.dataselfdrivingtour.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.jeecg.modules.xjdatascreen.datascenicinfo.entity.ScenicInfo;
import org.jeecg.modules.xjdatascreen.datascenicinfo.mapper.ScenicInfoMapper;
import org.jeecg.modules.xjdatascreen.dataselfdrivingtour.entity.DataSelfDrivingTour;
import org.jeecg.modules.xjdatascreen.dataselfdrivingtour.entity.DataSelfDrivingTourVO;
import org.jeecg.modules.xjdatascreen.dataselfdrivingtour.mapper.DataSelfDrivingTourMapper;
import org.jeecg.modules.xjdatascreen.dataselfdrivingtour.service.IDataSelfDrivingTourService;
import org.jeecg.modules.xjdatascreen.utils.ArithmeticUtil;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Description: data_self_driving_tour
 * @Author: jeecg-boot
 * @Date:   2020-11-26
 * @Version: V1.0
 */
@Service
public class DataSelfDrivingTourServiceImpl extends ServiceImpl<DataSelfDrivingTourMapper, DataSelfDrivingTour> implements IDataSelfDrivingTourService {


    @Resource
    private DataSelfDrivingTourMapper dataSelfDrivingTourMapper;
    @Resource
    private ScenicInfoMapper scenicInfoMapper;

    @Override
    public List<DataSelfDrivingTourVO> selectAll(String scenicName, String startTime, String endTime) {
        List<DataSelfDrivingTourVO> dataSelfDrivingTourVOS = new ArrayList<>();
        //获取全部车辆
        QueryWrapper<DataSelfDrivingTour> dataSelfDrivingTourQueryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(scenicName) && StringUtils.isNotBlank(startTime) && StringUtils.isNotBlank(endTime)) {
            List<ScenicInfo> scenicInfos = scenicInfoMapper.selectList(Wrappers.<ScenicInfo>query().lambda().eq(ScenicInfo::getScenicName, scenicName));
            if(CollectionUtils.isNotEmpty(scenicInfos)) {
                dataSelfDrivingTourQueryWrapper.eq("scenic_num", scenicInfos.get(0).getScenicCode()).between("come_time", startTime, endTime);
            }
        }
        Integer total = dataSelfDrivingTourMapper.selectCount(dataSelfDrivingTourQueryWrapper);
        //获取每个省的数量
        List<DataSelfDrivingTour> dataSelfDrivingTours = dataSelfDrivingTourMapper.selectList(dataSelfDrivingTourQueryWrapper);
        if (CollectionUtils.isNotEmpty(dataSelfDrivingTours)) {
            Map<String, List<DataSelfDrivingTour>> collect = dataSelfDrivingTours.stream().collect(Collectors.groupingBy(DataSelfDrivingTour::getProvince));
            collect.forEach((provide, vo) -> {
                DataSelfDrivingTourVO dataSelfDrivingTourVO = new DataSelfDrivingTourVO();
                dataSelfDrivingTourVO.setProvince(provide);
                dataSelfDrivingTourVO.setNumber(vo.size());
                String format = ArithmeticUtil.format(vo.size(), total);
                dataSelfDrivingTourVO.setPercentage(format);
                dataSelfDrivingTourVOS.add(dataSelfDrivingTourVO);
            });
        }
        dataSelfDrivingTourVOS.sort(Comparator.comparing(DataSelfDrivingTourVO::getNumber).reversed());
        if (dataSelfDrivingTourVOS.size() > 8) {
            return dataSelfDrivingTourVOS.subList(0, 8);
        }
        return dataSelfDrivingTourVOS;
    }
}
