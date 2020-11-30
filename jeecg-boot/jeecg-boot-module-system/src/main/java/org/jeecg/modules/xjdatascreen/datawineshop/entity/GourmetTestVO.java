package org.jeecg.modules.xjdatascreen.datawineshop.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author Edge
 * @version 1.0
 * @date 2020/11/30 10:23
 */

@Data
public class GourmetTestVO implements Serializable {

    //美食店铺
    private String GourmetName;
    //消费人数
    private int people;
    //消费金额
    private BigDecimal costPrice;
}
