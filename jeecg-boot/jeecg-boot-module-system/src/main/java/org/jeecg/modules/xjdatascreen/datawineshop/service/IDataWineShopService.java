package org.jeecg.modules.xjdatascreen.datawineshop.service;

import org.jeecg.modules.xjdatascreen.datawineshop.entity.DataWineShop;
import com.baomidou.mybatisplus.extension.service.IService;
import org.jeecg.modules.xjdatascreen.datawineshop.entity.DataWineShopVO;

import java.util.List;

/**
 * @Description: data_wine_shop
 * @Author: jeecg-boot
 * @Date:   2020-11-18
 * @Version: V1.0
 */
public interface IDataWineShopService extends IService<DataWineShop> {


    /**
     * 获取酒店入住率
     * @return
     */
    List<DataWineShopVO> selectAll();
}
