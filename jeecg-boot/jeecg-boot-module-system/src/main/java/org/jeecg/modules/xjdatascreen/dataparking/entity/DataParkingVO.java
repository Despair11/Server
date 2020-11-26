package org.jeecg.modules.xjdatascreen.dataparking.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Edge
 * @version 1.0
 * @date 2020/11/26 9:49
 */

@Data
public class DataParkingVO implements Serializable {

    //cmd
    private String cmd;
    private String opTime;
    private data data;


    @Data
    public static class data{
        //总车位
        private int totalPlaceNum;
        //停车场编号
        private String parkNo;
        //空余车位
        private int freePlaceNum;
    }
}
