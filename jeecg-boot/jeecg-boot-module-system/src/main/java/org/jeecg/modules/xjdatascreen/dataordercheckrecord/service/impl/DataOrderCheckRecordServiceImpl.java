package org.jeecg.modules.xjdatascreen.dataordercheckrecord.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.jeecg.modules.xjdatascreen.dataordercheckrecord.entity.DataOrderCheckRecord;
import org.jeecg.modules.xjdatascreen.dataordercheckrecord.entity.DataOrderCheckRecordVO;
import org.jeecg.modules.xjdatascreen.dataordercheckrecord.mapper.DataOrderCheckRecordMapper;
import org.jeecg.modules.xjdatascreen.dataordercheckrecord.service.IDataOrderCheckRecordService;
import org.jeecg.modules.xjdatascreen.utils.ArithmeticUtil;
import org.jeecg.modules.xjdatascreen.utils.DateUtil;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Description: data_order_check_record
 * @Author: jeecg-boot
 * @Date:   2020-11-21
 * @Version: V1.0
 */
@Service
public class DataOrderCheckRecordServiceImpl extends ServiceImpl<DataOrderCheckRecordMapper, DataOrderCheckRecord> implements IDataOrderCheckRecordService {

    @Resource
    private DataOrderCheckRecordMapper dataOrderCheckRecordMapper;


    @Override
    public List<DataOrderCheckRecordVO> selectAll() {
        //记录今日接待游客数
        List<DataOrderCheckRecordVO> dataOrderCheckRecordVOS = new ArrayList<>();
        DataOrderCheckRecordVO dataOrderCheckRecordVO = new DataOrderCheckRecordVO();
        //记录今年接待总人数
        DataOrderCheckRecordVO dataOrderCheckRecordVO1 = new DataOrderCheckRecordVO();
        //获取今日接待游客数
        Integer integer = dataOrderCheckRecordMapper.selectCount(Wrappers.<DataOrderCheckRecord>query().lambda()
                .last("where DATE_FORMAT(check_time, '%Y-%m-%d') = DATE_FORMAT( NOW(), '%Y-%m-%d')"));
        //获取上个月接待总人数
        Integer integer1 = dataOrderCheckRecordMapper.selectCount(Wrappers.<DataOrderCheckRecord>query().lambda()
                .last("where DATE_FORMAT(check_time, '%Y-%m-%d') = " + "'" + DateUtil.comDate(1) + "'"));
        dataOrderCheckRecordVO.setType("今日接待游客人数");
        dataOrderCheckRecordVO.setPeople(integer);
        Double division = ArithmeticUtil.division(integer, integer1);
        dataOrderCheckRecordVO.setFlagNumber(division);
        int i = division.compareTo(1.00);
        //获取今日接待游客数据汇总
        if(i < 0) {
            dataOrderCheckRecordVO.setFlag(1);
        } else if(i> 0) {
            dataOrderCheckRecordVO.setFlag(2);
        } else {
            dataOrderCheckRecordVO.setFlag(3);
        }
        dataOrderCheckRecordVOS.add(dataOrderCheckRecordVO);
        //获取今年接待游客数
        Integer integer2 = dataOrderCheckRecordMapper.selectCount(Wrappers.<DataOrderCheckRecord>query().lambda()
                .last("where DATE_FORMAT(check_time, '%Y') = DATE_FORMAT( NOW(), '%Y')"));
        //获取去年接待游客数
        Integer integer3 = dataOrderCheckRecordMapper.selectCount(Wrappers.<DataOrderCheckRecord>query().lambda()
                .last("where DATE_FORMAT(check_time, '%Y') =" + "'" + DateUtil.comYear(1) + "'"));
        dataOrderCheckRecordVO1.setType(new SimpleDateFormat("yyyy").format(new Date()) + "年接待总人数");
        dataOrderCheckRecordVO1.setPeople(integer2);
        Double division1 = ArithmeticUtil.division(integer2, integer3);
        dataOrderCheckRecordVO1.setFlagNumber(division1);
        int i1 = division1.compareTo(1.00);
        if(i1 < 0) {
            dataOrderCheckRecordVO1.setFlag(1);
        } else if(i1 > 0) {
            dataOrderCheckRecordVO1.setFlag(2);
        } else {
            dataOrderCheckRecordVO1.setFlag(3);
        }
        dataOrderCheckRecordVOS.add(dataOrderCheckRecordVO1);
        return dataOrderCheckRecordVOS;
    }
}
