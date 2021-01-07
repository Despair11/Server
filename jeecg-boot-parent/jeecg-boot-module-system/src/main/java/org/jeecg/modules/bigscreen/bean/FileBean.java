package org.jeecg.modules.bigscreen.bean;

import lombok.Data;

/**
 * @author: jiaosen
 * @date: 2019/9/7 10:51
 * @description: 文件信息
 */
@Data
public class FileBean {

    /**
     * 文件数据
     */
    private byte[] fileMetaData;

    /**
     * 文件大小
     */
    private int fileSize;

    /**
     * 文件类型
     */
    private String fileType;

    /**
     * 宽度
     */
    private int width;

    /**
     * 高度
     */
    private int height;

    public FileBean() {
    }

    public FileBean(byte[] fileMetaData, int fileSize) {
        this.fileMetaData = fileMetaData;
        this.fileSize = fileSize;
    }
}
