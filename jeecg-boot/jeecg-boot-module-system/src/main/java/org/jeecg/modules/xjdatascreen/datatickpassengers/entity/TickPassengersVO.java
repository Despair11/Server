package org.jeecg.modules.xjdatascreen.datatickpassengers.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Edge
 * @version 1.0
 * @date 2020/11/24 16:55
 */
@Data
public class TickPassengersVO implements Serializable {

    //年龄段
    private String generation;
    //男士数量
    private int men;
    //女士数量
    private int ladies;
    //男士和女士总数量
    private int totalNum;

}
