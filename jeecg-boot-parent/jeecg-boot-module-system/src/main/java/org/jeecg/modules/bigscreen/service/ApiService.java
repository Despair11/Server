package org.jeecg.modules.bigscreen.service;


import org.jeecg.modules.bigscreen.dto.GetCameraResponseDto;
import org.jeecg.modules.bigscreen.dto.OrgDto;
import org.jeecg.modules.bigscreen.util.CustomArtemisHttpUtil;

import java.util.List;

public interface ApiService {
    List<GetCameraResponseDto> queryAllCamera(CustomArtemisHttpUtil customArtemisHttpUtil);

    List<OrgDto> getAllOrgDto(CustomArtemisHttpUtil customArtemisHttpUtil);

    String getCameraPreview(String cameraIndexCode,CustomArtemisHttpUtil customArtemisHttpUtil);
}
