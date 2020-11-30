package org.jeecg.modules.xjdatascreen.datawineshop.service;

import org.jeecg.modules.xjdatascreen.datawineshop.entity.*;
import com.baomidou.mybatisplus.extension.service.IService;

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



    //===========================================mock数据====================================================================


    /**
     * mock美食店铺数据
     * @return
     */
    List<GourmetTestVO> selectAllGourmet();


    /**
     * mock游客信息来源分析
     * @return
     */
    List<SourceOfTouristsVO> selectAllTravel();


    /**
     * mock关注热点数据
     * @return
     */
    List<String> selectFocusOnHotSpotsAll();


    /**
     * mock游客满意程度数据
     * @return
     */
    List<TouristSatisfactionVO> selectSatisfactionAll(String scenicName);


    /**
     * mock各景区票占比数据
     * @return
     */
    VoteRatioVO selectVoteRatioAll(String scenicName);


    /**
     * mock各个景区的团散占比
     * @return
     */
    List<ProportionOfGroupsVO> selectProportionOfGroupsAll(String scenicName);


    /**
     * mock各个景区的游客信息分析数据
     * @param startTime
     * @param endTime
     * @return
     */
    List<ProportionOfGroupsVO> selectTravelInfoAll(String scenicName, String startTime, String endTime);


    /**
     * mock各景区内项目受欢迎程度
     * @param scenicName
     * @param startTime
     * @param endTime
     * @return
     */
    List<ScenicProjectVO> selectScenicProjectAll(String scenicName, String startTime, String endTime);
}
