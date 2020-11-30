package org.jeecg.modules.xjdatascreen.datawineshop.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Edge
 * @version 1.0
 * @date 2020/11/30 13:50
 */

@Data
public class ScenicProjectVO implements Serializable {

    private String time;
    private int jungleCrossing;
    private int rockClimbing;
    private int realCS;
}
