package org.jeecg.modules.xjdatascreen.datawineshop.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author Edge
 * @version 1.0
 * @date 2020/11/30 11:23
 */

@Data
public class VoteRatioVO implements Serializable {
    //总票数
    private int totalVote;
    //list
    private List<VoteData> voteDataList;


    @Data
    public static class VoteData{
        //票类型
        private String voteName;
        //票数量
        private int voteNumber;
        //占比
        private String percentage;
    }
}
