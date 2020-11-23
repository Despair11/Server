package org.jeecg.modules.xjdatascreen.datatouristmember.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.apache.commons.collections.CollectionUtils;
import org.jeecg.modules.xjdatascreen.config.RedisKeyConfig;
import org.jeecg.modules.xjdatascreen.datatouristmember.entity.DataTouristMember;
import org.jeecg.modules.xjdatascreen.datatouristmember.entity.DataTouristMemberVO;
import org.jeecg.modules.xjdatascreen.datatouristmember.mapper.DataTouristMemberMapper;
import org.jeecg.modules.xjdatascreen.datatouristmember.service.IDataTouristMemberService;
import org.jeecg.modules.xjdatascreen.utils.ArithmeticUtil;
import org.jeecg.modules.xjdatascreen.utils.DateUtil;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Description: data_tourist_member
 * @Author: jeecg-boot
 * @Date:   2020-11-19
 * @Version: V1.0
 */
@Service
public class DataTouristMemberServiceImpl extends ServiceImpl<DataTouristMemberMapper, DataTouristMember> implements IDataTouristMemberService {

    @Resource
    private DataTouristMemberMapper dataTouristMemberMapper;

    @Resource
    private RedisTemplate redisTemplate;

    @Override
    public List<DataTouristMemberVO> selectAll() {
        List<DataTouristMemberVO> dataTouristMemberVOS = new ArrayList<>();
        List<DataTouristMemberVO.peopleData> peopleDataList = new ArrayList<>();
        List<DataTouristMemberVO.peopleData> peopleDataList1 = new ArrayList<>();
        List<DataTouristMemberVO.peopleData> peopleDataList2 = new ArrayList<>();
        DataTouristMemberVO dataTouristMemberVO = new DataTouristMemberVO();
        DataTouristMemberVO dataTouristMemberVO1 = new DataTouristMemberVO();
        DataTouristMemberVO dataTouristMemberVO2 = new DataTouristMemberVO();
        if(! redisTemplate.hasKey(RedisKeyConfig.tourist_member_key)) {
            //获取月份
            List<String> mouths = DateUtil.getMouths(DateUtil.comDate(11), new SimpleDateFormat("yyyy-MM").format(new Date()));
            for (String mouth : mouths) {
                //获取每个月总人数
                int integer1 = dataTouristMemberMapper.selectCount(Wrappers.<DataTouristMember>query().lambda()
                        .last("where DATE_FORMAT(create_time, '%Y-%m') = " + "'" + mouth + "'"));
                DataTouristMemberVO.peopleData peopleData1 = new DataTouristMemberVO.peopleData();
                dataTouristMemberVO1.setType("总游客数");
                peopleData1.setTotalPeople(integer1);
                peopleData1.setMouth(mouth);
                peopleDataList1.add(peopleData1);
                dataTouristMemberVO1.setPeopleDataList(peopleDataList1);
                //获取每个月的省内数据
                int integer = dataTouristMemberMapper.selectCount(Wrappers.<DataTouristMember>query().lambda()
                        .last("where card_id like concat (46,'%') and DATE_FORMAT(create_time, '%Y-%m') = " + "'" + mouth + "'"));
                DataTouristMemberVO.peopleData peopleData = new DataTouristMemberVO.peopleData();
                dataTouristMemberVO.setType("省内游客");
                peopleData.setTotalPeople(integer);
                peopleData.setMouth(mouth);
                peopleDataList.add(peopleData);
                dataTouristMemberVO.setPeopleDataList(peopleDataList);
                //获取每个月省外游客数
                DataTouristMemberVO.peopleData peopleData2 = new DataTouristMemberVO.peopleData();
                dataTouristMemberVO2.setType("省外游客");
                peopleData2.setTotalPeople(ArithmeticUtil.sub(integer1,integer));
                peopleData2.setMouth(mouth);
                peopleDataList2.add(peopleData2);
                dataTouristMemberVO2.setPeopleDataList(peopleDataList2);
            }
            dataTouristMemberVOS.add(dataTouristMemberVO);
            dataTouristMemberVOS.add(dataTouristMemberVO1);
            dataTouristMemberVOS.add(dataTouristMemberVO2);
            redisTemplate.opsForValue().set(RedisKeyConfig.tourist_member_key, JSONArray.toJSONString(dataTouristMemberVOS), RedisKeyConfig.effective_time, TimeUnit.SECONDS);
        } else {
            Object o = redisTemplate.opsForValue().get(RedisKeyConfig.wine_shop_key);
            if(null != o) {
                List<DataTouristMemberVO> dataTouristMemberVOS1 = JSONArray.parseArray(o.toString(), DataTouristMemberVO.class);
                dataTouristMemberVOS.addAll(dataTouristMemberVOS1);
            }
        }
        return dataTouristMemberVOS;
    }

    @Override
    public List<DataTouristMemberVO> selectNationalDayAll() {
        List<DataTouristMemberVO> dataTouristMemberVOS = new ArrayList<>();
        List<DataTouristMemberVO.peopleData> peopleDataList = new ArrayList<>();
        List<DataTouristMemberVO.peopleData> peopleDataList1 = new ArrayList<>();
        DataTouristMemberVO dataTouristMemberVO = new DataTouristMemberVO();
        DataTouristMemberVO dataTouristMemberVO1 = new DataTouristMemberVO();
        if(! redisTemplate.hasKey(RedisKeyConfig.tourist_national_day_key)) {
            //获取去年数据
            String beforeYear = DateUtil.comYear(1);
            List<String> days = DateUtil.getDays(beforeYear + "-09-26", beforeYear + "-10-10");
            for (String day : days) {
                Integer integer = dataTouristMemberMapper.selectCount(Wrappers.<DataTouristMember>query().lambda()
                        .last("where DATE_FORMAT(create_time, '%Y-%m-%d') =" + "'" + day + "'"));
                DataTouristMemberVO.peopleData peopleData = new DataTouristMemberVO.peopleData();
                dataTouristMemberVO.setType(beforeYear + "年");
                peopleData.setMouth(day);
                peopleData.setTotalPeople(integer);
                peopleDataList.add(peopleData);
                dataTouristMemberVO.setPeopleDataList(peopleDataList);
            }

            //获取今年数据
            String year = new SimpleDateFormat("yyyy").format(new Date());
            List<String> days1 = DateUtil.getDays(year + "-09-26", year + "-10-10");
            for (String s : days1) {
                Integer integer = dataTouristMemberMapper.selectCount(Wrappers.<DataTouristMember>query().lambda()
                        .last("where DATE_FORMAT(create_time, '%Y-%m-%d') =" + "'" + s + "'"));
                DataTouristMemberVO.peopleData peopleData1 = new DataTouristMemberVO.peopleData();
                dataTouristMemberVO.setType(year + "年");
                peopleData1.setMouth(s);
                peopleData1.setTotalPeople(integer);
                peopleDataList1.add(peopleData1);
                dataTouristMemberVO1.setPeopleDataList(peopleDataList1);
            }
            dataTouristMemberVOS.add(dataTouristMemberVO);
            dataTouristMemberVOS.add(dataTouristMemberVO1);
            redisTemplate.opsForValue().set(RedisKeyConfig.tourist_national_day_key, JSONArray.toJSONString(dataTouristMemberVOS), RedisKeyConfig.effective_time, TimeUnit.SECONDS);
        } else {
            Object o = redisTemplate.opsForValue().get(RedisKeyConfig.tourist_national_day_key);
            if(o != null) {
                List<DataTouristMemberVO> dataTouristMemberVOS1 = JSONArray.parseArray(o.toString(), DataTouristMemberVO.class);
                dataTouristMemberVOS.addAll(dataTouristMemberVOS1);
            }
        }
        return dataTouristMemberVOS;
    }
}
