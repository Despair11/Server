package org.jeecg.modules.xjdatascreen.datapackageticket.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.apache.commons.lang3.StringUtils;
import org.jeecg.modules.xjdatascreen.config.RedisKeyConfig;
import org.jeecg.modules.xjdatascreen.datapackageticket.entity.CommonVO;
import org.jeecg.modules.xjdatascreen.datapackageticket.entity.DataPackageTicket;
import org.jeecg.modules.xjdatascreen.datapackageticket.mapper.DataPackageTicketMapper;
import org.jeecg.modules.xjdatascreen.datapackageticket.service.IDataPackageTicketService;
import org.jeecg.modules.xjdatascreen.datasingleticket.entity.DataSingleTicket;
import org.jeecg.modules.xjdatascreen.datasingleticket.mapper.DataSingleTicketMapper;
import org.jeecg.modules.xjdatascreen.utils.DateUtil;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Description: data_package_ticket
 * @Author: jeecg-boot
 * @Date:   2020-11-18
 * @Version: V1.0
 */
@Service
public class DataPackageTicketServiceImpl extends ServiceImpl<DataPackageTicketMapper, DataPackageTicket> implements IDataPackageTicketService {

    @Resource
    private DataPackageTicketMapper dataPackageTicketMapper;
    @Resource
    private DataSingleTicketMapper dataSingleTicketMapper;
    @Resource
    private RedisTemplate redisTemplate;

    @Override
    public List<CommonVO> selectAll() {
        List<CommonVO> commonVOS = new ArrayList<>();
        List<CommonVO.balance> balances = new ArrayList<>();
        List<CommonVO.balance> balances1 = new ArrayList<>();
        List<CommonVO.balance> balances2= new ArrayList<>();
        CommonVO commonVO = new CommonVO();
        CommonVO commonVO1 = new CommonVO();
        CommonVO commonVO2 = new CommonVO();
        if(! redisTemplate.hasKey(RedisKeyConfig.tourism_income_key)) {
            String time = new SimpleDateFormat("yyyy-MM").format(new Date());
            List<String> mouths = DateUtil.getMouths(DateUtil.comDate(Integer.parseInt(StringUtils.substringAfter(time,"-"))), time);
            for (String mouth : mouths) {
                //获取门票每月金额
                //获取套票总收入
                List<DataPackageTicket> dataPackageTickets = dataPackageTicketMapper.selectList(Wrappers.<DataPackageTicket>query()
                        .lambda().eq(DataPackageTicket::getOrderStatus, "已完成")
                        .last("and DATE_FORMAT(use_time, '%Y-%m') =" + "'" + mouth + "'"));
                BigDecimal bigDecimal = new BigDecimal("0.00");
                for (DataPackageTicket dataPackageTicket : dataPackageTickets) {
                    bigDecimal = bigDecimal.add(dataPackageTicket.getOrderPrice().multiply(BigDecimal.valueOf(dataPackageTicket.getBuyNumber())));
                }
                //获取单票总收入
                List<DataSingleTicket> dataSingleTickets = dataSingleTicketMapper.selectList(Wrappers.<DataSingleTicket>query()
                        .lambda().eq(DataSingleTicket::getOrderStatus, "已完成")
                        .last("and DATE_FORMAT(use_time, '%Y-%m') = " + "'" + mouth + "'"));
                BigDecimal bigDecimal1 = new BigDecimal("0.00");
                for (DataSingleTicket dataSingleTicket : dataSingleTickets) {
                    bigDecimal1 = bigDecimal1.add(dataSingleTicket.getOrderPrice().multiply(BigDecimal.valueOf(dataSingleTicket.getBuyNumber())));
                }
                BigDecimal sumBigDecimal = bigDecimal.add(bigDecimal1);
                commonVO.setType("门票");
                CommonVO.balance balance = new CommonVO.balance();
                balance.setSumBalance(sumBigDecimal.divide(BigDecimal.valueOf(10000)));
                balance.setTime(mouth);
                balances.add(balance);
                commonVO.setBalances(balances);
            }
            commonVOS.add(commonVO);
            redisTemplate.opsForValue().set(RedisKeyConfig.tourism_income_key, JSONArray.toJSONString(commonVOS), RedisKeyConfig.effective_time, TimeUnit.SECONDS);
        } else {
            Object o = redisTemplate.opsForValue().get(RedisKeyConfig.tourism_income_key);
            if(null != o) {
                List<CommonVO> commonVOS1 = JSONArray.parseArray(o.toString(), CommonVO.class);
                commonVOS.addAll(commonVOS1);
            }
        }
        return commonVOS;
    }
}
