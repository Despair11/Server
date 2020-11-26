package org.jeecg.modules.xjdatascreen.dataselfdrivingtour.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Edge
 * @version 1.0
 * @date 2020/11/26 11:40
 */

@Data
public class DataSelfDrivingTourVO implements Serializable {

    //省份
    private String province;
    //数量
    private int number;
    //占比
    private String percentage;
}
