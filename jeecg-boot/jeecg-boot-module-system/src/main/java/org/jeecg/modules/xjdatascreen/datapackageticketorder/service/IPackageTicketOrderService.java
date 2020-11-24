package org.jeecg.modules.xjdatascreen.datapackageticketorder.service;

import org.jeecg.modules.xjdatascreen.datapackageticketorder.entity.PackageTicketOrder;
import com.baomidou.mybatisplus.extension.service.IService;
import org.jeecg.modules.xjdatascreen.datapackageticketorder.entity.PackageTicketOrderVO;

import java.util.List;

/**
 * @Description: package_ticket_order
 * @Author: jeecg-boot
 * @Date:   2020-11-24
 * @Version: V1.0
 */
public interface IPackageTicketOrderService extends IService<PackageTicketOrder> {


    /**
     * 获取总屏各个景区的统计
     * @return
     */
    List<PackageTicketOrderVO> selectAll();


}
