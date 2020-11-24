package org.jeecg.modules.xjdatascreen.datapackageticketorder.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.apache.commons.collections.CollectionUtils;
import org.jeecg.modules.xjdatascreen.config.RedisKeyConfig;
import org.jeecg.modules.xjdatascreen.datapackageticketorder.entity.OrderVO;
import org.jeecg.modules.xjdatascreen.datapackageticketorder.entity.PackageTicketOrder;
import org.jeecg.modules.xjdatascreen.datapackageticketorder.entity.PackageTicketOrderVO;
import org.jeecg.modules.xjdatascreen.datapackageticketorder.mapper.PackageTicketOrderMapper;
import org.jeecg.modules.xjdatascreen.datapackageticketorder.service.IPackageTicketOrderService;
import org.jeecg.modules.xjdatascreen.datascenicinfo.entity.ScenicInfo;
import org.jeecg.modules.xjdatascreen.datascenicinfo.mapper.ScenicInfoMapper;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import javax.annotation.Resource;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Description: package_ticket_order
 * @Author: jeecg-boot
 * @Date: 2020-11-24
 * @Version: V1.0
 */
@Service
public class PackageTicketOrderServiceImpl extends ServiceImpl<PackageTicketOrderMapper, PackageTicketOrder> implements IPackageTicketOrderService {

    @Resource
    private PackageTicketOrderMapper packageTicketOrderMapper;
    @Resource
    private ScenicInfoMapper scenicInfoMapper;
    @Resource
    private RedisTemplate redisTemplate;

    @Override
    public List<PackageTicketOrderVO> selectAll() {
        List<PackageTicketOrderVO> packageTicketOrderVOS = new ArrayList<>();
        if (!redisTemplate.hasKey(RedisKeyConfig.scenic_info_key)) {
            PackageTicketOrderVO packageTicketOrderVO;
            int i = 0;
            List<OrderVO> orderVOS = packageTicketOrderMapper.selectAll();
            if (CollectionUtils.isNotEmpty(orderVOS)) {
                //获取总的游客数量
                for (OrderVO orderVO : orderVOS) {
                    i += orderVO.getUseCount();
                }
                //获取各个景点游客数据
                List<ScenicInfo> scenicInfos = scenicInfoMapper.selectList(Wrappers.<ScenicInfo>query().lambda().groupBy(ScenicInfo::getScenicCode));
                if (CollectionUtils.isNotEmpty(scenicInfos)) {
                    for (ScenicInfo scenicInfo : scenicInfos) {
                        packageTicketOrderVO = new PackageTicketOrderVO();
                        List<OrderVO> orderVOS1 = packageTicketOrderMapper.selectByScenicNum(scenicInfo.getScenicCode());
                        int j = 0;
                        if (CollectionUtils.isNotEmpty(orderVOS1)) {
                            for (OrderVO orderVO : orderVOS1) {
                                j += orderVO.getUseCount();
                            }
                        }
                        packageTicketOrderVO.setTotalPeople(i);
                        packageTicketOrderVO.setTotalScenicPeople(j);
                        packageTicketOrderVO.setScenicName(scenicInfo.getScenicName());
                        packageTicketOrderVO.setDescription(new SimpleDateFormat("yyyy").format(new Date()) + "年累计");
                        packageTicketOrderVOS.add(packageTicketOrderVO);
                    }
                }
            }
            redisTemplate.opsForValue().set(RedisKeyConfig.scenic_info_key, JSONArray.toJSONString(packageTicketOrderVOS), RedisKeyConfig.effective_time, TimeUnit.SECONDS);
        } else {
            Object o = redisTemplate.opsForValue().get(RedisKeyConfig.scenic_info_key);
            if (null != o) {
                List<PackageTicketOrderVO> packageTicketOrderVOS1 = JSONArray.parseArray(o.toString(), PackageTicketOrderVO.class);
                packageTicketOrderVOS.addAll(packageTicketOrderVOS1);
            }
        }
        return packageTicketOrderVOS;
    }
}
