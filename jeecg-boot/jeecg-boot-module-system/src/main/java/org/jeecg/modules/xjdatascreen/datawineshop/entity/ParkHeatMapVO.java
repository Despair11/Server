package org.jeecg.modules.xjdatascreen.datawineshop.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Edge
 * @version 1.0
 * @date 2020/12/1 18:27
 */

@Data
public class ParkHeatMapVO implements Serializable {

    //经度
    private String longitude;
    //维度
    private String dimension;
    //人数
    private int people;
}
