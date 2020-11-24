package org.jeecg.modules.xjdatascreen.datapackageticketorder.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Edge
 * @version 1.0
 * @date 2020/11/24 15:01
 */

@Data
public class OrderVO implements Serializable {
    //景区code
    private String scenicNum;
    //门票使用数量
    private Integer useCount;
}
