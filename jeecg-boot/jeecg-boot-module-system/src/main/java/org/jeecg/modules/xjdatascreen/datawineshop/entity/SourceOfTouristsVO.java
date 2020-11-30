package org.jeecg.modules.xjdatascreen.datawineshop.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Edge
 * @version 1.0
 * @date 2020/11/30 10:37
 */

@Data
public class SourceOfTouristsVO implements Serializable {

    //旅游网站名称
    private String travelName;
    //本月数据
    private int mouthData;
    //本年数据
    private int yearData;
    //增长率
    private String growthRate;
}
