package org.jeecg.modules.xjdatascreen.datapackageticket.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author Edge
 * @version 1.0
 * @date 2020/11/23 11:42
 */

@Data
public class CommonVO implements Serializable {
    //类型
    private String type;
    private List<balance> balances;


    @Data
    public static class balance{
        //时间
        private String  time;
        //金额
        private BigDecimal sumBalance;
    }

}
