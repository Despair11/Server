package org.jeecg.modules.xjdatascreen.dataordercheckrecord.service;

import org.jeecg.modules.xjdatascreen.dataordercheckrecord.entity.DataOrderCheckRecord;
import com.baomidou.mybatisplus.extension.service.IService;
import org.jeecg.modules.xjdatascreen.dataordercheckrecord.entity.DataOrderCheckRecordVO;

import java.util.List;

/**
 * @Description: data_order_check_record
 * @Author: jeecg-boot
 * @Date:   2020-11-21
 * @Version: V1.0
 */
public interface IDataOrderCheckRecordService extends IService<DataOrderCheckRecord> {


    /**
     * 获取接待游客数据
     * @return
     */
    List<DataOrderCheckRecordVO> selectAll();
}
