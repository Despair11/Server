package org.jeecg.modules.xjdatascreen.datatouristmember.service;

import org.jeecg.modules.xjdatascreen.datatouristmember.entity.DataTouristMember;
import com.baomidou.mybatisplus.extension.service.IService;
import org.jeecg.modules.xjdatascreen.datatouristmember.entity.DataTouristMemberVO;

import java.util.List;

/**
 * @Description: data_tourist_member
 * @Author: jeecg-boot
 * @Date:   2020-11-19
 * @Version: V1.0
 */
public interface IDataTouristMemberService extends IService<DataTouristMember> {


    /**
     * 获取人数数据
     * @return
     */
    List<DataTouristMemberVO> selectAll();
}
