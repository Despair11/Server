package org.jeecg.modules.bigscreen.dto;

import lombok.Data;

@Data
public class CameraPreviewDto {
    /**
     * code : 200
     * msg : success
     * playrealUrl : rtsp://10.7.132.108:554/ncg://10.33.25.144:7099:0:32000000001310010703:0:0&checkinfo=ewogICAidGltZSIgOiAiMjAxODAzMjdUMTM1NjI2WiIsCiAgICJ1cm wiIDogInJ0c3A6Ly8xMC43LjEzMi4xMDg6NTU0L25jZzovLzEwLjMzLjI1LjE0NDo3MDk5OjA6MzIwMDAwMDAwMDEzMTAwMTA3MDM6MDowIgp9Cg%3D%3D&idin fo=EAAAAAAQAACtEpf37dsrYwXsANwRTCU1%2Fw%2BSwvVkAUrG6XAMhIEeIMYb66A84s%2FiiptYAHsLTS0%3D
     */

    private String code;
    private String msg;
    private String playrealUrl;
}
