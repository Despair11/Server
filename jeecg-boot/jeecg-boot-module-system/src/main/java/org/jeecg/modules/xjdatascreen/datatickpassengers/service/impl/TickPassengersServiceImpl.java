package org.jeecg.modules.xjdatascreen.datatickpassengers.service.impl;

import cn.hutool.core.util.IdcardUtil;
import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.jeecg.modules.xjdatascreen.config.RedisKeyConfig;
import org.jeecg.modules.xjdatascreen.dataordercheckrecord.entity.DataOrderCheckRecordVO;
import org.jeecg.modules.xjdatascreen.datascenicinfo.entity.ScenicInfo;
import org.jeecg.modules.xjdatascreen.datascenicinfo.mapper.ScenicInfoMapper;
import org.jeecg.modules.xjdatascreen.dataselfdrivingtour.entity.DataSelfDrivingTourVO;
import org.jeecg.modules.xjdatascreen.dataticketorderdetail.entity.TicketOrderDetail;
import org.jeecg.modules.xjdatascreen.datatickpassengers.entity.TickPassengers;
import org.jeecg.modules.xjdatascreen.datatickpassengers.entity.TickPassengersVO;
import org.jeecg.modules.xjdatascreen.datatickpassengers.mapper.TickPassengersMapper;
import org.jeecg.modules.xjdatascreen.datatickpassengers.service.ITickPassengersService;
import org.jeecg.modules.xjdatascreen.datatouristmember.entity.DataTouristMember;
import org.jeecg.modules.xjdatascreen.datatouristmember.entity.DataTouristMemberVO;
import org.jeecg.modules.xjdatascreen.utils.ArithmeticUtil;
import org.jeecg.modules.xjdatascreen.utils.DateUtil;
import org.jeecg.modules.xjdatascreen.utils.GetAgeNoUtil;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @Description: tick_passengers
 * @Author: jeecg-boot
 * @Date:   2020-11-24
 * @Version: V1.0
 */
@Service
public class TickPassengersServiceImpl extends ServiceImpl<TickPassengersMapper, TickPassengers> implements ITickPassengersService {

    @Resource
    private TickPassengersMapper tickPassengersMapper;
    @Resource
    private ScenicInfoMapper scenicInfoMapper;
    @Resource
    private RedisTemplate redisTemplate;

    @Override
    public List<TickPassengersVO> selectAll(String scenicName, String startTime, String endTime) {
        List<TickPassengersVO> tickPassengersVOS = new ArrayList<>();
        List<TickPassengersVO> tickPassengersVOS1 = new ArrayList<>();
        List<ScenicInfo> scenicInfos = scenicInfoMapper.selectList(Wrappers.<ScenicInfo>query().lambda().eq(ScenicInfo::getScenicName, scenicName));
        if(CollectionUtils.isNotEmpty(scenicInfos)) {
            List<DataTouristMember> dataTouristMembers = tickPassengersMapper.selectAll(scenicInfos.get(0).getScenicCode(), startTime, endTime);
            if(CollectionUtils.isNotEmpty(dataTouristMembers)) {
                for (DataTouristMember dataTouristMember : dataTouristMembers) {
                    int ageByPsptNo = GetAgeNoUtil.getAgeByPsptNo(dataTouristMember.getCardNo());
                    int i = 0;
                    int j = 0;
                    //获取<=18岁
                    TickPassengersVO tickPassengersVO = new TickPassengersVO();
                    tickPassengersVO.setGeneration("0~18岁");
                    if(ageByPsptNo <= 18) {
                        if("男".equals(dataTouristMember.getSex())) {
                            i ++;
                            tickPassengersVO.setMen(i);
                        }
                        if("女".equals(dataTouristMember.getSex())) {
                            j ++;
                            tickPassengersVO.setLadies(j);
                        }
                        tickPassengersVO.setTotalNum(i + j);
                    }
                    tickPassengersVOS.add(tickPassengersVO);
                    //获取19~25岁
                    TickPassengersVO tickPassengersVO1 = new TickPassengersVO();
                    tickPassengersVO1.setGeneration("19~25岁");
                    if(ageByPsptNo >= 19 && ageByPsptNo <= 25) {
                        if("男".equals(dataTouristMember.getSex())) {
                            i ++;
                            tickPassengersVO1.setMen(i);
                        }
                        if("女".equals(dataTouristMember.getSex())) {
                            j ++;
                            tickPassengersVO1.setLadies(j);
                        }
                        tickPassengersVO1.setTotalNum(i + j);
                    }
                    tickPassengersVOS.add(tickPassengersVO1);
                    //26~30岁
                    TickPassengersVO tickPassengersVO2 = new TickPassengersVO();
                    tickPassengersVO2.setGeneration("26~30岁");
                    if(ageByPsptNo >= 26 && ageByPsptNo <= 30) {
                        if("男".equals(dataTouristMember.getSex())) {
                            i ++;
                            tickPassengersVO2.setMen(i);
                        }
                        if("女".equals(dataTouristMember.getSex())) {
                            j ++;
                            tickPassengersVO2.setLadies(j);
                        }
                        tickPassengersVO2.setTotalNum(i + j);
                    }
                    tickPassengersVOS.add(tickPassengersVO2);
                    //31~40岁
                    TickPassengersVO tickPassengersVO3 = new TickPassengersVO();
                    tickPassengersVO3.setGeneration("31~40岁");
                    if(ageByPsptNo >= 31 && ageByPsptNo <= 40) {
                        if("男".equals(dataTouristMember.getSex())) {
                            i ++;
                            tickPassengersVO3.setMen(i);
                        }
                        if("女".equals(dataTouristMember.getSex())) {
                            j ++;
                            tickPassengersVO3.setLadies(j);
                        }
                        tickPassengersVO3.setTotalNum(i + j);
                    }
                    tickPassengersVOS.add(tickPassengersVO3);
                    //41~50岁
                    TickPassengersVO tickPassengersVO4 = new TickPassengersVO();
                    tickPassengersVO4.setGeneration("41~50岁");
                    if(ageByPsptNo >= 41 && ageByPsptNo <= 50) {
                        if("男".equals(dataTouristMember.getSex())) {
                            i ++;
                            tickPassengersVO4.setMen(i);
                        }
                        if("女".equals(dataTouristMember.getSex())) {
                            j ++;
                            tickPassengersVO4.setLadies(j);
                        }
                        tickPassengersVO4.setTotalNum(i + j);
                    }
                    tickPassengersVOS.add(tickPassengersVO4);
                    //51~60岁
                    TickPassengersVO tickPassengersVO5 = new TickPassengersVO();
                    tickPassengersVO5.setGeneration("51~60岁");
                    if(ageByPsptNo >= 51 && ageByPsptNo <= 60) {
                        if("男".equals(dataTouristMember.getSex())) {
                            i ++;
                            tickPassengersVO5.setMen(i);
                        }
                        if("女".equals(dataTouristMember.getSex())) {
                            j ++;
                            tickPassengersVO5.setLadies(j);
                        }
                        tickPassengersVO5.setTotalNum(i + j);
                    }
                    tickPassengersVOS.add(tickPassengersVO5);
                    //>60岁
                    TickPassengersVO tickPassengersVO6 = new TickPassengersVO();
                    tickPassengersVO6.setGeneration(">60岁");
                    if(ageByPsptNo > 60) {
                        if("男".equals(dataTouristMember.getSex())) {
                            i ++;
                            tickPassengersVO6.setMen(i);
                        }
                        if("女".equals(dataTouristMember.getSex())) {
                            j ++;
                            tickPassengersVO6.setLadies(j);
                        }
                        tickPassengersVO6.setTotalNum(i + j);
                    }
                    tickPassengersVOS.add(tickPassengersVO6);
                }
                Map<String, List<TickPassengersVO>> collect = tickPassengersVOS.stream().collect(Collectors.groupingBy(TickPassengersVO::getGeneration));
                collect.forEach((generation, vo)-> {
                    TickPassengersVO tickPassengersVO = new TickPassengersVO();
                    tickPassengersVO.setGeneration(generation);
                    int a = 0;
                    int b = 0;
                    for (TickPassengersVO passengersVO : vo) {
                        a += passengersVO.getLadies();
                        tickPassengersVO.setLadies(a);
                        b += passengersVO.getMen();
                        tickPassengersVO.setMen(b);
                        tickPassengersVO.setTotalNum(a + b);
                    }
                    tickPassengersVOS1.add(tickPassengersVO);
                });
            }
        }
        return tickPassengersVOS1.stream().sorted(Comparator.comparing(TickPassengersVO::getGeneration)).collect(Collectors.toList());
    }

    @Override
    public List<DataTouristMemberVO> selectTouristAll(String scenicName, String startTime, String endTime) {
        List<DataTouristMemberVO> dataTouristMemberVOS = new ArrayList<>();
        List<DataTouristMemberVO.peopleData> peopleDataList = new ArrayList<>();
        List<DataTouristMemberVO.peopleData> peopleDataList1 = new ArrayList<>();
        List<DataTouristMemberVO.peopleData> peopleDataList2 = new ArrayList<>();
        DataTouristMemberVO dataTouristMemberVO = new DataTouristMemberVO();
        DataTouristMemberVO dataTouristMemberVO1 = new DataTouristMemberVO();
        DataTouristMemberVO dataTouristMemberVO2 = new DataTouristMemberVO();

        //根据景区名字获取景区编号
        List<ScenicInfo> scenicInfos = scenicInfoMapper.selectList(Wrappers.<ScenicInfo>query().lambda().eq(ScenicInfo::getScenicName, scenicName));
        if (CollectionUtils.isNotEmpty(scenicInfos)) {
            List<String> days = DateUtil.getDays(startTime, endTime);
            for (String day : days) {
                //获取各个景区省内数据
                List<TicketOrderDetail> ticketOrderDetails = tickPassengersMapper.selectTouristAll(scenicInfos.get(0).getScenicCode(), startTime, endTime, day);
                int i = 0;
                DataTouristMemberVO.peopleData peopleData = new DataTouristMemberVO.peopleData();
                for (TicketOrderDetail ticketOrderDetail : ticketOrderDetails) {
                    i += ticketOrderDetail.getUseCount();
                }
                dataTouristMemberVO.setType("省内数据");
                peopleData.setTotalPeople(i);
                peopleData.setMouth(day);
                peopleDataList.add(peopleData);
                dataTouristMemberVO.setPeopleDataList(peopleDataList);
                //获取各个景区省外数据
                List<TicketOrderDetail> ticketOrderDetails1 = tickPassengersMapper.selectTouristAllOutsideTheProvince(scenicInfos.get(0).getScenicCode(), startTime, endTime, day);
                DataTouristMemberVO.peopleData peopleData1 = new DataTouristMemberVO.peopleData();
                int j = 0;
                for (TicketOrderDetail ticketOrderDetail : ticketOrderDetails1) {
                    j += ticketOrderDetail.getUseCount();
                }
                dataTouristMemberVO1.setType("省外数据");
                peopleData1.setTotalPeople(j);
                peopleData1.setMouth(day);
                peopleDataList1.add(peopleData1);
                dataTouristMemberVO1.setPeopleDataList(peopleDataList1);
            }
        }
        dataTouristMemberVOS.add(dataTouristMemberVO);
        dataTouristMemberVOS.add(dataTouristMemberVO1);
        return dataTouristMemberVOS;
    }

    @Override
    public List<DataOrderCheckRecordVO> selectSumTourist(String scenicName) {
        List<DataOrderCheckRecordVO> dataOrderCheckRecordVOS = new ArrayList<>();
        //记录今日接待游客数
        DataOrderCheckRecordVO dataOrderCheckRecordVO = new DataOrderCheckRecordVO();
        //记录今年接待总人数
        DataOrderCheckRecordVO dataOrderCheckRecordVO1 = new DataOrderCheckRecordVO();
        List<ScenicInfo> scenicInfos = scenicInfoMapper.selectList(Wrappers.<ScenicInfo>query().lambda().eq(ScenicInfo::getScenicName, scenicName));
        if(! redisTemplate.hasKey(RedisKeyConfig.various_scenic_spots_key + scenicInfos.get(0).getId())) {
            //获取今日游客数据
            List<TicketOrderDetail> ticketOrderDetails = tickPassengersMapper.selectSumTourist(scenicInfos.get(0).getScenicCode());
           int i = 0;
            for (TicketOrderDetail ticketOrderDetail : ticketOrderDetails) {
                i += ticketOrderDetail.getUseCount();
            }
            //获取上个月游客数据
            List<TicketOrderDetail> ticketOrderDetails1 = tickPassengersMapper.lastMonthVisitorData(scenicInfos.get(0).getScenicCode(), DateUtil.comDate(1));
            int j = 0;
            for (TicketOrderDetail ticketOrderDetail : ticketOrderDetails1) {
                j += ticketOrderDetail.getUseCount();
            }
            dataOrderCheckRecordVO.setType("今日接待游客人数");
            dataOrderCheckRecordVO.setPeople(i);
            Double division = ArithmeticUtil.division(i, j);
            dataOrderCheckRecordVO.setFlagNumber(division);
            int k = division.compareTo(1.00);
            if(k < 0) {
                dataOrderCheckRecordVO.setFlag(1);
            } else if(k > 0) {
                dataOrderCheckRecordVO.setFlag(2);
            } else {
                dataOrderCheckRecordVO.setFlag(3);
            }
            dataOrderCheckRecordVOS.add(dataOrderCheckRecordVO);
            //获取今年游客数据
            List<TicketOrderDetail> ticketOrderDetails2 = tickPassengersMapper.selectSumTouristYear(scenicInfos.get(0).getScenicCode());
            int l = 0;
            for (TicketOrderDetail ticketOrderDetail : ticketOrderDetails2) {
                l += ticketOrderDetail.getUseCount();
            }
            //获取去年游客数据
            List<TicketOrderDetail> ticketOrderDetails3 = tickPassengersMapper.lastMonthVisitorYearData(scenicInfos.get(0).getScenicCode(), DateUtil.comYear(1));
            int m = 0;
            for (TicketOrderDetail ticketOrderDetail : ticketOrderDetails3) {
                m += ticketOrderDetail.getUseCount();
            }
            dataOrderCheckRecordVO1.setType("今年累计接待游客");
            dataOrderCheckRecordVO1.setPeople(l);
            Double division1 = ArithmeticUtil.division(l, m);
            dataOrderCheckRecordVO1.setFlagNumber(division1);
            int i1 = division1.compareTo(1.00);
            if(i1 < 0) {
                dataOrderCheckRecordVO1.setFlag(1);
            } else if(i1 > 0) {
                dataOrderCheckRecordVO1.setFlag(2);
            } else {
                dataOrderCheckRecordVO1.setFlag(3);
            }
            dataOrderCheckRecordVOS.add(dataOrderCheckRecordVO1);
            redisTemplate.opsForValue().set(RedisKeyConfig.various_scenic_spots_key + scenicInfos.get(0).getId(), JSONArray.toJSONString(dataOrderCheckRecordVOS), RedisKeyConfig.effective_time, TimeUnit.SECONDS);
        } else {
            Object o = redisTemplate.opsForValue().get(RedisKeyConfig.various_scenic_spots_key + scenicInfos.get(0).getId());
            if(null != o) {
                List<DataOrderCheckRecordVO> dataOrderCheckRecordVOS1 = JSONArray.parseArray(o.toString(), DataOrderCheckRecordVO.class);
                dataOrderCheckRecordVOS.addAll(dataOrderCheckRecordVOS1);
            }
        }
        return dataOrderCheckRecordVOS;
    }

    @Override
    public List<DataTouristMemberVO> selectGalaData(String scenicName,String startTime, String endTime) {
        List<DataTouristMemberVO> dataTouristMemberVOS = new ArrayList<>();
        List<DataTouristMemberVO.peopleData> peopleDataList = new ArrayList<>();
        List<DataTouristMemberVO.peopleData> peopleDataList1 = new ArrayList<>();
        List<DataTouristMemberVO.peopleData> peopleDataList2 = new ArrayList<>();
        List<DataTouristMemberVO.peopleData> peopleDataList3 = new ArrayList<>();
        DataTouristMemberVO dataTouristMemberVO = new DataTouristMemberVO();
        DataTouristMemberVO dataTouristMemberVO1 = new DataTouristMemberVO();
        DataTouristMemberVO dataTouristMemberVO2 = new DataTouristMemberVO();
        DataTouristMemberVO dataTouristMemberVO3 = new DataTouristMemberVO();

        List<ScenicInfo> scenicInfos = scenicInfoMapper.selectList(Wrappers.<ScenicInfo>query().lambda().eq(ScenicInfo::getScenicName, scenicName));
//        if(! redisTemplate.hasKey(RedisKeyConfig.annual_tourist_data_of_scenic_spots_key + scenicInfos.get(0).getId())) {
            List<String> days = DateUtil.getDays(startTime, endTime);
            for (String day : days) {
                //就现在时间往前推3年(2017)
                List<TicketOrderDetail> ticketOrderDetails = tickPassengersMapper.selectGalaData(scenicInfos.get(0).getScenicCode(), DateUtil.comYear(3), StringUtils.substringAfter(startTime, "-"), StringUtils.substringAfter(endTime, "-"), StringUtils.substringAfter(day, "-"));
                int i = 0;
                DataTouristMemberVO.peopleData peopleData = new DataTouristMemberVO.peopleData();
                for (TicketOrderDetail ticketOrderDetail : ticketOrderDetails) {
                    i += ticketOrderDetail.getUseCount();
                }
                dataTouristMemberVO.setType(DateUtil.comYear(3) + "游客数");
                peopleData.setTotalPeople(i);
                peopleData.setMouth(day);
                peopleDataList.add(peopleData);
                dataTouristMemberVO.setPeopleDataList(peopleDataList);

                //就现在时间往前推2年(2018)
                List<TicketOrderDetail> ticketOrderDetails1 = tickPassengersMapper.selectGalaData(scenicInfos.get(0).getScenicCode(), DateUtil.comYear(2), StringUtils.substringAfter(startTime, "-"), StringUtils.substringAfter(endTime, "-"), StringUtils.substringAfter(day, "-"));
                int j = 0;
                DataTouristMemberVO.peopleData peopleData1 = new DataTouristMemberVO.peopleData();
                for (TicketOrderDetail ticketOrderDetail : ticketOrderDetails1) {
                    j += ticketOrderDetail.getUseCount();
                }
                dataTouristMemberVO1.setType(DateUtil.comYear(2) + "游客数");
                peopleData1.setTotalPeople(j);
                peopleData1.setMouth(day);
                peopleDataList1.add(peopleData1);
                dataTouristMemberVO1.setPeopleDataList(peopleDataList1);

                //就现在时间往前推1年(2019)
                List<TicketOrderDetail> ticketOrderDetails2 = tickPassengersMapper.selectGalaData(scenicInfos.get(0).getScenicCode(), DateUtil.comYear(1), StringUtils.substringAfter(startTime, "-"), StringUtils.substringAfter(endTime, "-"), StringUtils.substringAfter(day, "-"));
                int k = 0;
                DataTouristMemberVO.peopleData peopleData2 = new DataTouristMemberVO.peopleData();
                for (TicketOrderDetail ticketOrderDetail : ticketOrderDetails2) {
                    k += ticketOrderDetail.getUseCount();
                }
                dataTouristMemberVO2.setType(DateUtil.comYear(1) + "游客数");
                peopleData2.setTotalPeople(k);
                peopleData2.setMouth(day);
                peopleDataList2.add(peopleData2);
                dataTouristMemberVO2.setPeopleDataList(peopleDataList2);

                //现在时间
                List<TicketOrderDetail> ticketOrderDetails3 = tickPassengersMapper.selectGalaData(scenicInfos.get(0).getScenicCode(), DateUtil.comYear(0), StringUtils.substringAfter(startTime, "-"), StringUtils.substringAfter(endTime, "-"), StringUtils.substringAfter(day, "-"));
                int l = 0;
                DataTouristMemberVO.peopleData peopleData3 = new DataTouristMemberVO.peopleData();
                for (TicketOrderDetail ticketOrderDetail : ticketOrderDetails3) {
                    l += ticketOrderDetail.getUseCount();
                }
                dataTouristMemberVO3.setType(DateUtil.comYear(0) + "游客数");
                peopleData3.setTotalPeople(l);
                peopleData3.setMouth(day);
                peopleDataList3.add(peopleData3);
                dataTouristMemberVO3.setPeopleDataList(peopleDataList3);
            }
            dataTouristMemberVOS.add(dataTouristMemberVO);
            dataTouristMemberVOS.add(dataTouristMemberVO1);
            dataTouristMemberVOS.add(dataTouristMemberVO2);
            dataTouristMemberVOS.add(dataTouristMemberVO3);
//            redisTemplate.opsForValue().set(RedisKeyConfig.annual_tourist_data_of_scenic_spots_key + scenicInfos.get(0).getId(), JSONArray.toJSONString(dataTouristMemberVOS), RedisKeyConfig.effective_time, TimeUnit.SECONDS);
//        } else {
//            Object o = redisTemplate.opsForValue().get(RedisKeyConfig.annual_tourist_data_of_scenic_spots_key + scenicInfos.get(0).getId());
//            if(null != o) {
//                List<DataTouristMemberVO> dataTouristMemberVOS1 = JSONArray.parseArray(o.toString(), DataTouristMemberVO.class);
//                dataTouristMemberVOS.addAll(dataTouristMemberVOS1);
//            }
//        }

        return dataTouristMemberVOS;
    }

    @Override
    public List<DataSelfDrivingTourVO> selectTouristOriginAll() {
        List<DataSelfDrivingTourVO> dataSelfDrivingTourVOS = new ArrayList<>();
        List<DataSelfDrivingTourVO> dataSelfDrivingTourVOS1 = new ArrayList<>();
        DataSelfDrivingTourVO dataSelfDrivingTourVO;
        List<TickPassengers> tickPassengers = tickPassengersMapper.selectSumTouristYearOne();
        if(CollectionUtils.isNotEmpty(tickPassengers)) {
            for (TickPassengers tickPassenger : tickPassengers) {
                dataSelfDrivingTourVO = new DataSelfDrivingTourVO();
                //根据身份证号获取省份
                String province = IdcardUtil.getProvinceByIdCard(tickPassenger.getCardNo());
                dataSelfDrivingTourVO.setProvince(province);
                dataSelfDrivingTourVO.setNumber(tickPassenger.getUseCount());
                dataSelfDrivingTourVOS.add(dataSelfDrivingTourVO);
            }
            Map<String, List<DataSelfDrivingTourVO>> dataSelfDrivingTourVOMap = dataSelfDrivingTourVOS.stream().collect(Collectors.groupingBy(DataSelfDrivingTourVO::getProvince));
            dataSelfDrivingTourVOMap.forEach((province, vo) ->{
                DataSelfDrivingTourVO dataSelfDrivingTourVO1 = new DataSelfDrivingTourVO();
                dataSelfDrivingTourVO1.setProvince(province);
                int i = 0;
                for (DataSelfDrivingTourVO selfDrivingTourVO : vo) {
                    i += selfDrivingTourVO.getNumber();
                }
                dataSelfDrivingTourVO1.setNumber(i);
                dataSelfDrivingTourVOS1.add(dataSelfDrivingTourVO1);
            });

        }
        return dataSelfDrivingTourVOS1;
    }

}


