package org.jeecg.modules.xjdatascreen.datawineshop.service.impl;


import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.apache.commons.collections.CollectionUtils;
import org.jeecg.modules.xjdatascreen.config.RedisKeyConfig;
import org.jeecg.modules.xjdatascreen.datawineshop.entity.*;
import org.jeecg.modules.xjdatascreen.datawineshop.mapper.DataWineShopMapper;
import org.jeecg.modules.xjdatascreen.datawineshop.service.IDataWineShopService;
import org.jeecg.modules.xjdatascreen.utils.ArithmeticUtil;
import org.jeecg.modules.xjdatascreen.utils.DateUtil;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import javax.annotation.Resource;
import java.math.BigDecimal;
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
        if (dataWineShopVOS.size() > 4) {
            return dataWineShopVOS.subList(0, 4);
        }
        return dataWineShopVOS;
    }
//====================================================mock数据====================================================================
    @Override
    public List<GourmetTestVO> selectAllGourmet() {
        List<GourmetTestVO> gourmetTestVOS = new ArrayList<>();
        GourmetTestVO gourmetTestVO = new GourmetTestVO();
        gourmetTestVO.setGourmetName("麻椒鸡");
        gourmetTestVO.setPeople(1000);
        gourmetTestVO.setCostPrice(BigDecimal.valueOf(2000.00));
        gourmetTestVOS.add(gourmetTestVO);

        GourmetTestVO gourmetTestVO1 = new GourmetTestVO();
        gourmetTestVO1.setGourmetName("KFC");
        gourmetTestVO1.setPeople(800);
        gourmetTestVO1.setCostPrice(BigDecimal.valueOf(2000.00));
        gourmetTestVOS.add(gourmetTestVO1);

        GourmetTestVO gourmetTestVO2 = new GourmetTestVO();
        gourmetTestVO2.setGourmetName("新疆烤肉");
        gourmetTestVO2.setPeople(2000);
        gourmetTestVO2.setCostPrice(BigDecimal.valueOf(3000.00));
        gourmetTestVOS.add(gourmetTestVO2);
        return gourmetTestVOS;
    }

    @Override
    public List<SourceOfTouristsVO> selectAllTravel() {
        List<SourceOfTouristsVO> sourceOfTouristsVOS = new ArrayList<>();
        SourceOfTouristsVO sourceOfTouristsVO = new SourceOfTouristsVO();

        sourceOfTouristsVO.setTravelName("携程");
        sourceOfTouristsVO.setMouthData(200);
        sourceOfTouristsVO.setYearData(2000);
        sourceOfTouristsVO.setGrowthRate("10%");
        sourceOfTouristsVOS.add(sourceOfTouristsVO);

        SourceOfTouristsVO sourceOfTouristsVO1 = new SourceOfTouristsVO();
        sourceOfTouristsVO1.setTravelName("去哪了");
        sourceOfTouristsVO1.setMouthData(300);
        sourceOfTouristsVO1.setYearData(1500);
        sourceOfTouristsVO1.setGrowthRate("15%");
        sourceOfTouristsVOS.add(sourceOfTouristsVO1);

        SourceOfTouristsVO sourceOfTouristsVO2 = new SourceOfTouristsVO();
        sourceOfTouristsVO2.setTravelName("艺龙");
        sourceOfTouristsVO2.setMouthData(100);
        sourceOfTouristsVO2.setYearData(3000);
        sourceOfTouristsVO2.setGrowthRate("8%");
        sourceOfTouristsVOS.add(sourceOfTouristsVO2);

        SourceOfTouristsVO sourceOfTouristsVO3 = new SourceOfTouristsVO();
        sourceOfTouristsVO3.setTravelName("途牛");
        sourceOfTouristsVO3.setMouthData(400);
        sourceOfTouristsVO3.setYearData(5000);
        sourceOfTouristsVO3.setGrowthRate("16%");
        sourceOfTouristsVOS.add(sourceOfTouristsVO3);

        SourceOfTouristsVO sourceOfTouristsVO4 = new SourceOfTouristsVO();
        sourceOfTouristsVO4.setTravelName("飞猪");
        sourceOfTouristsVO4.setMouthData(350);
        sourceOfTouristsVO4.setYearData(2000);
        sourceOfTouristsVO4.setGrowthRate("11%");

        sourceOfTouristsVOS.add(sourceOfTouristsVO4);
        return sourceOfTouristsVOS;
    }

    @Override
    public List<String> selectFocusOnHotSpotsAll(String scenicName) {
        List<String> list = new ArrayList<>();
        list.add("火星基地");
        list.add("羊肉串");
        list.add("神仙洞");
        list.add("穿越");
        return list;
    }

    @Override
    public List<TouristSatisfactionVO> selectSatisfactionAll(String scenicName) {
        List<TouristSatisfactionVO> touristSatisfactionVOS = new ArrayList<>();
        List<TouristSatisfactionVO.DataService> dataServices = new ArrayList<>();

        TouristSatisfactionVO touristSatisfactionVO = new TouristSatisfactionVO();
        touristSatisfactionVO.setMouth("本月");
        TouristSatisfactionVO.DataService dataService = new TouristSatisfactionVO.DataService();
        dataService.setSort("服务");
        dataService.setData("50%");
        dataServices.add(dataService);
        touristSatisfactionVO.setDataServices(dataServices);

        TouristSatisfactionVO.DataService dataService1 = new TouristSatisfactionVO.DataService();
        dataService1.setSort("交通");
        dataService1.setData("35%");
        dataServices.add(dataService1);
        touristSatisfactionVO.setDataServices(dataServices);

        TouristSatisfactionVO.DataService dataService2 = new TouristSatisfactionVO.DataService();
        dataService2.setSort("导游");
        dataService2.setData("38%");
        dataServices.add(dataService2);
        touristSatisfactionVO.setDataServices(dataServices);

        TouristSatisfactionVO.DataService dataService3 = new TouristSatisfactionVO.DataService();
        dataService3.setSort("餐饮");
        dataService3.setData("70%");
        dataServices.add(dataService3);
        touristSatisfactionVO.setDataServices(dataServices);

        TouristSatisfactionVO.DataService dataService4 = new TouristSatisfactionVO.DataService();
        dataService4.setSort("卫生");
        dataService4.setData("60%");
        dataServices.add(dataService4);
        touristSatisfactionVO.setDataServices(dataServices);

        TouristSatisfactionVO.DataService dataService5 = new TouristSatisfactionVO.DataService();
        dataService5.setSort("设施");
        dataService5.setData("50%");
        dataServices.add(dataService5);
        touristSatisfactionVO.setDataServices(dataServices);

        touristSatisfactionVOS.add(touristSatisfactionVO);

        List<TouristSatisfactionVO.DataService> dataServices1 = new ArrayList<>();
        TouristSatisfactionVO touristSatisfactionVO1 = new TouristSatisfactionVO();
        touristSatisfactionVO1.setMouth("上月");
        TouristSatisfactionVO.DataService dataService6 = new TouristSatisfactionVO.DataService();
        dataService6.setSort("服务");
        dataService6.setData("50%");
        dataServices1.add(dataService6);
        touristSatisfactionVO1.setDataServices(dataServices1);

        TouristSatisfactionVO.DataService dataService7 = new TouristSatisfactionVO.DataService();
        dataService7.setSort("交通");
        dataService7.setData("35%");
        dataServices.add(dataService7);
        touristSatisfactionVO1.setDataServices(dataServices);

        TouristSatisfactionVO.DataService dataService8 = new TouristSatisfactionVO.DataService();
        dataService8.setSort("导游");
        dataService8.setData("38%");
        dataServices1.add(dataService8);
        touristSatisfactionVO1.setDataServices(dataServices1);

        TouristSatisfactionVO.DataService dataService9 = new TouristSatisfactionVO.DataService();
        dataService9.setSort("餐饮");
        dataService9.setData("70%");
        dataServices1.add(dataService9);
        touristSatisfactionVO1.setDataServices(dataServices1);

        TouristSatisfactionVO.DataService dataService10 = new TouristSatisfactionVO.DataService();
        dataService10.setSort("卫生");
        dataService10.setData("60%");
        dataServices1.add(dataService10);
        touristSatisfactionVO1.setDataServices(dataServices1);

        TouristSatisfactionVO.DataService dataService11 = new TouristSatisfactionVO.DataService();
        dataService11.setSort("设施");
        dataService11.setData("50%");
        dataServices1.add(dataService11);
        touristSatisfactionVO1.setDataServices(dataServices1);

        touristSatisfactionVOS.add(touristSatisfactionVO1);




        return touristSatisfactionVOS;
    }

    @Override
    public VoteRatioVO selectVoteRatioAll(String scenicName) {

        VoteRatioVO voteRatioVO = new VoteRatioVO();
        voteRatioVO.setTotalVote(3000);
        List< VoteRatioVO.VoteData> voteDataList = new ArrayList<>();

        VoteRatioVO.VoteData voteData = new VoteRatioVO.VoteData();
        voteData.setVoteName("全票");
        voteData.setVoteNumber(1140);
        voteData.setPercentage("38%");
        voteDataList.add(voteData);


        VoteRatioVO.VoteData voteData1 = new VoteRatioVO.VoteData();
        voteData1.setVoteName("半票");
        voteData1.setVoteNumber(840);
        voteData1.setPercentage("28%");
        voteDataList.add(voteData1);


        VoteRatioVO.VoteData voteData2 = new VoteRatioVO.VoteData();
        voteData2.setVoteName("本地票");
        voteData2.setVoteNumber(600);
        voteData2.setPercentage("20%");
        voteDataList.add(voteData2);

        VoteRatioVO.VoteData voteData3 = new VoteRatioVO.VoteData();
        voteData3.setVoteName("免票");
        voteData3.setVoteNumber(300);
        voteData3.setPercentage("10%");
        voteDataList.add(voteData3);

        VoteRatioVO.VoteData voteData4 = new VoteRatioVO.VoteData();
        voteData4.setVoteName("优惠券");
        voteData4.setVoteNumber(180);
        voteData4.setPercentage("6%");
        voteDataList.add(voteData4);


        voteRatioVO.setVoteDataList(voteDataList);

        return voteRatioVO;
    }

    @Override
    public List<ProportionOfGroupsVO> selectProportionOfGroupsAll(String scenicName) {
        List<ProportionOfGroupsVO> proportionOfGroupsVOS = new ArrayList<>();

        ProportionOfGroupsVO proportionOfGroupsVO = new ProportionOfGroupsVO();
        proportionOfGroupsVO.setName("团客");
        proportionOfGroupsVO.setPercentage("20%");
        proportionOfGroupsVOS.add(proportionOfGroupsVO);

        ProportionOfGroupsVO proportionOfGroupsVO1 = new ProportionOfGroupsVO();
        proportionOfGroupsVO1.setName("散客");
        proportionOfGroupsVO1.setPercentage("80%");
        proportionOfGroupsVOS.add(proportionOfGroupsVO1);

        return proportionOfGroupsVOS;
    }

    @Override
    public List<ProportionOfGroupsVO> selectTravelInfoAll(String scenicName, String startTime, String endTime) {
        List<ProportionOfGroupsVO> proportionOfGroupsVOS = new ArrayList<>();

        ProportionOfGroupsVO proportionOfGroupsVO = new ProportionOfGroupsVO();
        proportionOfGroupsVO.setName("携程");
        proportionOfGroupsVO.setPercentage("234");
        proportionOfGroupsVOS.add(proportionOfGroupsVO);

        ProportionOfGroupsVO proportionOfGroupsVO1 = new ProportionOfGroupsVO();
        proportionOfGroupsVO1.setName("艺龙");
        proportionOfGroupsVO1.setPercentage("213");
        proportionOfGroupsVOS.add(proportionOfGroupsVO1);

        ProportionOfGroupsVO proportionOfGroupsVO2 = new ProportionOfGroupsVO();
        proportionOfGroupsVO2.setName("途牛");
        proportionOfGroupsVO2.setPercentage("166");
        proportionOfGroupsVOS.add(proportionOfGroupsVO2);

        ProportionOfGroupsVO proportionOfGroupsVO3 = new ProportionOfGroupsVO();
        proportionOfGroupsVO3.setName("去哪了");
        proportionOfGroupsVO3.setPercentage("143");
        proportionOfGroupsVOS.add(proportionOfGroupsVO3);

        ProportionOfGroupsVO proportionOfGroupsVO4 = new ProportionOfGroupsVO();
        proportionOfGroupsVO4.setName("飞猪");
        proportionOfGroupsVO4.setPercentage("189");
        proportionOfGroupsVOS.add(proportionOfGroupsVO4);

        ProportionOfGroupsVO proportionOfGroupsVO5 = new ProportionOfGroupsVO();
        proportionOfGroupsVO5.setName("其他渠道");
        proportionOfGroupsVO5.setPercentage("100");
        proportionOfGroupsVOS.add(proportionOfGroupsVO5);

        return proportionOfGroupsVOS;
    }

    @Override
    public List<ScenicProjectVO> selectScenicProjectAll(String scenicName, String startTime, String endTime) {
        List<ScenicProjectVO> scenicProjectVOS = new ArrayList<>();
        ScenicProjectVO scenicProjectVO;
        List<String> days = DateUtil.getDays(startTime, endTime);
        for (String day : days) {
            scenicProjectVO = new ScenicProjectVO();
            scenicProjectVO.setTime(day);
            scenicProjectVO.setJungleCrossing(100);
            scenicProjectVO.setRealCS(30);
            scenicProjectVO.setRockClimbing(50);
            scenicProjectVOS.add(scenicProjectVO);
        }
        return scenicProjectVOS;
    }

    @Override
    public List<String> selectAllScenicSpot() {
        List<String> list = new ArrayList<>();
        list.add("文创园");
        list.add("天山庙");
        list.add("全部");
        list.add("鸣沙山");
        list.add("大海道");

        return list;
    }

}
