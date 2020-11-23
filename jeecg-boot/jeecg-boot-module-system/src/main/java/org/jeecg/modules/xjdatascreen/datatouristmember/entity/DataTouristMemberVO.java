package org.jeecg.modules.xjdatascreen.datatouristmember.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author Edge
 * @version 1.0
 * @date 2020/11/20 11:19
 */

@Data
public class DataTouristMemberVO implements Serializable {
    private static final long serialVersionUID = 1L;

    //类型  1.省内 2.省外 3.总游客
    private String type;
    private List<peopleData> peopleDataList;

    @Data
    public static class peopleData{
        //人数
        private int totalPeople;
        //月份
        private String mouth;
    }

}
