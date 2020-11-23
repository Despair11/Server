package org.jeecg.modules.xjdatascreen.datawineshop.service.impl;


import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.apache.commons.collections.CollectionUtils;
import org.jeecg.modules.xjdatascreen.config.RedisKeyConfig;
import org.jeecg.modules.xjdatascreen.datawineshop.entity.DataWineShop;
import org.jeecg.modules.xjdatascreen.datawineshop.entity.DataWineShopVO;
import org.jeecg.modules.xjdatascreen.datawineshop.mapper.DataWineShopMapper;
import org.jeecg.modules.xjdatascreen.datawineshop.service.IDataWineShopService;
import org.jeecg.modules.xjdatascreen.utils.ArithmeticUtil;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Description: data_wine_shop
 * @Author: jeecg-boot
 * @Date:   2020-11-18
 * @Version: V1.0
 */
@Service
public class DataWineShopServiceImpl extends ServiceImpl<DataWineShopMapper, DataWineShop> implements IDataWineShopService {


    @Resource
    private DataWineShopMapper dataWineShopMapper;

    @Resource
    private RedisTemplate redisTemplate;

    @Override
    public List<DataWineShopVO> selectAll() {
        List<DataWineShopVO> dataWineShopVOS = new ArrayList<>();
        if(! redisTemplate.hasKey(RedisKeyConfig.wine_shop_key)) {
            //获取总的酒店数量
            Integer integer = dataWineShopMapper.selectCount(Wrappers.<DataWineShop>query().lambda()
                    .last("where DATE_FORMAT(create_time, '%Y-%m-%d') = DATE_FORMAT( NOW(), '%Y-%m-%d')"));
            //获取酒店列表
            List<DataWineShop> dataWineShops = dataWineShopMapper.selectList(Wrappers.<DataWineShop>query().lambda()
                    .groupBy(DataWineShop::getProductName));
            DataWineShopVO dataWineShopVO;
            if(CollectionUtils.isNotEmpty(dataWineShops)) {
                for (DataWineShop dataWineShop : dataWineShops) {
                    dataWineShopVO = new DataWineShopVO();
                    //获取每个酒店的数量
                    Integer integer1 = dataWineShopMapper.selectCount(Wrappers.<DataWineShop>query().lambda()
                            .eq(DataWineShop::getProductName, dataWineShop.getProductName())
                            .last("AND DATE_FORMAT(create_time, '%Y-%m-%d') = DATE_FORMAT( NOW(), '%Y-%m-%d')"));
                    //预定此酒店待使用数据
                    Integer integer2 = dataWineShopMapper.selectCount(Wrappers.<DataWineShop>query().lambda()
                            .eq(DataWineShop::getProductName, dataWineShop.getProductName())
                            .eq(DataWineShop::getOrderStatus, "待使用")
                            .last("AND DATE_FORMAT(create_time, '%Y-%m-%d') = DATE_FORMAT( NOW(), '%Y-%m-%d')"));
                    //占比
                    String percentage = ArithmeticUtil.format(integer1, integer);
                    //入住率
                    String occupancyRate = ArithmeticUtil.format(integer2, integer1);
                    //酒店名称
                    dataWineShopVO.setProductName(dataWineShop.getProductName());
                    dataWineShopVO.setPercentage(percentage);
                    dataWineShopVO.setOccupancyRate(occupancyRate);
                    dataWineShopVOS.add(dataWineShopVO);
                }
            }
            redisTemplate.opsForValue().set(RedisKeyConfig.wine_shop_key, JSONArray.toJSONString(dataWineShopVOS),RedisKeyConfig.effective_time, TimeUnit.SECONDS);
        } else {
            Object o = redisTemplate.opsForValue().get(RedisKeyConfig.wine_shop_key);
            if(o != null) {
                List<DataWineShopVO> dataWineShopVOS1 = JSONArray.parseArray(o.toString(), DataWineShopVO.class);
                dataWineShopVOS.addAll(dataWineShopVOS1);
            }
        }
        //根据占比进行排序
        dataWineShopVOS.sort(Comparator.comparing(DataWineShopVO::getPercentage).reversed());
        dataWineShopVOS.subList(0,4);
        return dataWineShopVOS;
    }
}
