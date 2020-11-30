package org.jeecg.modules.xjdatascreen.datawineshop.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Edge
 * @version 1.0
 * @date 2020/11/19 11:41
 */

@Data
public class DataWineShopVO implements Serializable {
    private static final long serialVersionUID = 1L;

    //酒店名称
    private String productName;
    //占比
    private String percentage;
    //入住率
    private String occupancyRate;
}


