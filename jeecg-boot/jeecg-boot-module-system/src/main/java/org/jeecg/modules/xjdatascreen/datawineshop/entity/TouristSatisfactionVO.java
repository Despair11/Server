package org.jeecg.modules.xjdatascreen.datawineshop.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author Edge
 * @version 1.0
 * @date 2020/11/30 11:07
 */

@Data
public class TouristSatisfactionVO implements Serializable {
    //本月/上月
    private String mouth;
    private List<DataService> dataServices;

    @Data
    public static class DataService{
        //分类
        private String sort;
        //数据
        private String data;
    }
}
