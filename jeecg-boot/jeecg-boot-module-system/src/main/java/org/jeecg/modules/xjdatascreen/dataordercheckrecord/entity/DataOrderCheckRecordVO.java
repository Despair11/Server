package org.jeecg.modules.xjdatascreen.dataordercheckrecord.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Edge
 * @version 1.0
 * @date 2020/11/23 9:44
 */

@Data
public class DataOrderCheckRecordVO implements Serializable {

    //类型
    private String type;
    //人数
    private Integer people;
    //同比上升或下降标志 1.下降 2.上升 3.相等
    private int flag;
    //上升和下降百分比
    private double flagNumber;
}
