package org.jeecg.modules.xjdatascreen.datatickpassengers.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.apache.commons.collections.CollectionUtils;
import org.jeecg.modules.xjdatascreen.datascenicinfo.entity.ScenicInfo;
import org.jeecg.modules.xjdatascreen.datascenicinfo.mapper.ScenicInfoMapper;
import org.jeecg.modules.xjdatascreen.datatickpassengers.entity.TickPassengers;
import org.jeecg.modules.xjdatascreen.datatickpassengers.entity.TickPassengersVO;
import org.jeecg.modules.xjdatascreen.datatickpassengers.mapper.TickPassengersMapper;
import org.jeecg.modules.xjdatascreen.datatickpassengers.service.ITickPassengersService;
import org.jeecg.modules.xjdatascreen.datatouristmember.entity.DataTouristMember;
import org.jeecg.modules.xjdatascreen.utils.GetAgeNoUtil;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import javax.annotation.Resource;
import java.util.*;
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
}
