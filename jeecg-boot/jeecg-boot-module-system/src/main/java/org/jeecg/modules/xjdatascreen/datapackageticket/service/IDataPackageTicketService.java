package org.jeecg.modules.xjdatascreen.datapackageticket.service;

import org.jeecg.modules.xjdatascreen.datapackageticket.entity.CommonVO;
import org.jeecg.modules.xjdatascreen.datapackageticket.entity.DataPackageTicket;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @Description: data_package_ticket
 * @Author: jeecg-boot
 * @Date:   2020-11-18
 * @Version: V1.0
 */
public interface IDataPackageTicketService extends IService<DataPackageTicket> {


    /**
     * 获取旅游总收入
     * @return
     */
    List<CommonVO> selectAll();
}
