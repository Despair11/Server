package org.jeecg.modules.bigscreen.service.iml;

import com.fasterxml.jackson.core.type.TypeReference;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.modules.bigscreen.bean.PageData;
import org.jeecg.modules.bigscreen.constant.CommonConstant;
import org.jeecg.modules.bigscreen.dto.BaseResponse;
import org.jeecg.modules.bigscreen.dto.CameraPreviewDto;
import org.jeecg.modules.bigscreen.dto.GetCameraResponseDto;
import org.jeecg.modules.bigscreen.dto.OrgDto;
import org.jeecg.modules.bigscreen.service.ApiService;
import org.jeecg.modules.bigscreen.util.CustomArtemisHttpUtil;
import org.jeecg.modules.bigscreen.util.OpenApiUtil;
import org.jeecg.modules.bigscreen.util.json.JsonUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class ApiServiceIml implements ApiService {

    /**
     * 查询海康所有的监控点信息
     */
    @Override
    public List<GetCameraResponseDto> queryAllCamera(CustomArtemisHttpUtil customArtemisHttpUtil) {
        final int size = 1000;
        int start = 1;

        List<GetCameraResponseDto> responseDtos = new ArrayList<>();
        for (; ; start++) {
            Map<String, String> querys = new HashMap<>();
            querys.put("start",String.valueOf(start));
            querys.put("size",String.valueOf(size));
            if (log.isDebugEnabled()) {
                log.debug("queryAllCamera request param:{}", JsonUtil.toJson(querys));
            }
            String result = OpenApiUtil.getOpenApiResult(CommonConstant.GET_CAMERA_LIST, querys, customArtemisHttpUtil);
            BaseResponse<PageData<GetCameraResponseDto>> baseResponse = JsonUtil.toPojo(result, new TypeReference<BaseResponse<PageData<GetCameraResponseDto>>>() {
            });
            log.info("queryAllCamera result:{}",result);

            if (!"200".equals(baseResponse.getCode())) {
                log.error("查询监控点列表错误，错误码：{}，错误信息：{}", baseResponse.getCode(), baseResponse.getMsg());
            } else {
                if (baseResponse.getData() != null && !CollectionUtils.isEmpty(baseResponse.getData().getList())) {
                    responseDtos.addAll(baseResponse.getData().getList());
                    if (baseResponse.getData().getList().size() >= size) {
                        continue;
                    }
                }
            }
            break;
        }
        return responseDtos;
    }

    @Override
    public List<OrgDto> getAllOrgDto(CustomArtemisHttpUtil customArtemisHttpUtil) {
        final int size = 1000;
        int start = 1;
        List<OrgDto> responseDtos = new ArrayList<>();
        for (; ; start++) {
            Map<String, String> querys = new HashMap<>();
            querys.put("start",String.valueOf(start));
            querys.put("size",String.valueOf(size));
            if (log.isDebugEnabled()) {
                log.debug("getAllOrgDto request param:{}", JsonUtil.toJson(querys));
            }
            String result = OpenApiUtil.getOpenApiResult(CommonConstant.GET_ALL_ORG_LIST, querys, customArtemisHttpUtil);
            BaseResponse<PageData<OrgDto>> baseResponse = JsonUtil.toPojo(result, new TypeReference<BaseResponse<PageData<OrgDto>>>() {
            });

            log.info("getAllOrgDto result:{}",result);

            if (!"200".equals(baseResponse.getCode())) {
                log.error("查询组织列表错误，错误码：{}，错误信息：{}", baseResponse.getCode(), baseResponse.getMsg());
            } else {
                if (baseResponse.getData() != null && !CollectionUtils.isEmpty(baseResponse.getData().getList())) {
                    responseDtos.addAll(baseResponse.getData().getList());
                    if (baseResponse.getData().getList().size() >= size) {
                        continue;
                    }
                }
            }
            break;
        }
        return responseDtos;
    }

    @Override
    public String getCameraPreview(String cameraIndexCode,CustomArtemisHttpUtil customArtemisHttpUtil) {
        Map<String, String> querys = new HashMap<>();
        querys.put("cameraIndexCode",cameraIndexCode);
        querys.put("subStream",String.valueOf(0));
        querys.put("protocol",String.valueOf(1));
        log.info("getCameraPreviewrequest param:{}", JsonUtil.toJson(querys));
        String result = OpenApiUtil.getOpenApiResult(CommonConstant.GET_CAMERA_PREVIEW_URL, querys, customArtemisHttpUtil);
        CameraPreviewDto cameraPreviewDto = JsonUtil.toPojo(result, new TypeReference<CameraPreviewDto>() {
        });

        log.info("getCameraPreview result:{}",result);

        if (!"200".equals(cameraPreviewDto.getCode())) {
            log.error("查询监控点取流信息错误，错误码：{}，错误信息：{}", cameraPreviewDto.getCode(), cameraPreviewDto.getMsg());
            return null;
        } else {
            return cameraPreviewDto.getPlayrealUrl();
        }
    }
}
