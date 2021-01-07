package org.jeecg.modules.bigscreen.service;


import org.jeecg.modules.bigscreen.exception.CustomException;
import org.jeecg.modules.bigscreen.vo.MainCameraVo;
import org.jeecg.modules.bigscreen.vo.VmsTreesVo;

public interface DeviceService {

    VmsTreesVo queryVmsTrees() throws CustomException;

    MainCameraVo queryMainCamera() throws CustomException;

    MainCameraVo queryThirtyCamera() throws CustomException;
}
