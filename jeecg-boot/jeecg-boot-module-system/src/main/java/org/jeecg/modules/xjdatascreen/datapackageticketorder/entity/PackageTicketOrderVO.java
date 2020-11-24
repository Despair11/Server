package org.jeecg.modules.xjdatascreen.datapackageticketorder.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Edge
 * @version 1.0
 * @date 2020/11/24 14:52
 */

@Data
public class PackageTicketOrderVO implements Serializable {
    //景区名称
    private String scenicName;
    //景区人数
    private int totalScenicPeople;
    //所有景区总人数
    private int totalPeople;
    //描述
    private String description;
}
