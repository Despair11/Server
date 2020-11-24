package org.jeecg.modules.xjdatascreen.datapackageticketorder.mapper;

import org.jeecg.modules.xjdatascreen.datapackageticketorder.entity.OrderVO;
import org.jeecg.modules.xjdatascreen.datapackageticketorder.entity.PackageTicketOrder;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.jeecg.modules.xjdatascreen.datapackageticketorder.entity.PackageTicketOrderVO;

import java.util.List;

/**
 * @Description: package_ticket_order
 * @Author: jeecg-boot
 * @Date:   2020-11-24
 * @Version: V1.0
 */
public interface PackageTicketOrderMapper extends BaseMapper<PackageTicketOrder> {

    /**
     * 获取景区总的使用门票
     * @return
     */
    List<OrderVO> selectAll();


    /**
     * 根据景区code获取使用门票数量
     * @param scenicNum
     * @return
     */
    List<OrderVO> selectByScenicNum(String scenicNum);


}
