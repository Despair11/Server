package org.jeecg.modules.bigscreen.service.iml;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.util.TextUtils;
import org.jeecg.modules.bigscreen.bean.PropertyBean;
import org.jeecg.modules.bigscreen.constant.CommonConstant;
import org.jeecg.modules.bigscreen.dto.GetCameraResponseDto;
import org.jeecg.modules.bigscreen.dto.OrgDto;
import org.jeecg.modules.bigscreen.exception.CustomException;
import org.jeecg.modules.bigscreen.service.ApiService;
import org.jeecg.modules.bigscreen.service.DeviceService;
import org.jeecg.modules.bigscreen.util.CommonUtil;
import org.jeecg.modules.bigscreen.util.CustomArtemisHttpUtil;
import org.jeecg.modules.bigscreen.vo.MainCameraVo;
import org.jeecg.modules.bigscreen.vo.VmsTreesVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
public class DeviceServiceIml implements DeviceService {

    @Autowired
    private PropertyBean propertyBean;

    @Autowired
    private ApiService apiService;

    /**
     * 查询监控点展示的层级树
     */
    @Override
    public VmsTreesVo queryVmsTrees() throws CustomException {

        VmsTreesVo vmsTreesVo = new VmsTreesVo();
        VmsTreesVo.ConfigBean configBean = new VmsTreesVo.ConfigBean();
        vmsTreesVo.setConfig(configBean);

        configBean.setAppkey(propertyBean.getAppkey());
        configBean.setSecret(propertyBean.getAppSecret());

        String hikIpPort = propertyBean.getHikIp();
        if (!TextUtils.isEmpty(hikIpPort)) {
            if (hikIpPort.contains(":")) {
                String[] ipPortArray = hikIpPort.split(":");
                String hikIp = ipPortArray[0];
                String port = ipPortArray[1];
                configBean.setServerIp(hikIp);
                configBean.setPort(Integer.parseInt(port));
            } else {
                configBean.setServerIp(hikIpPort);
                configBean.setPort(443);
            }
        }

        CustomArtemisHttpUtil customArtemisHttpUtil = getCustomArtemisHttpUtil();

        List<GetCameraResponseDto> getCameraResponseDtoList = apiService.queryAllCamera(customArtemisHttpUtil);


        Map<String, List<GetCameraResponseDto>> parentRegionIndexCameraMap = new HashMap<>();
        getCameraResponseDtoList.stream().forEach(cameraResponseDto -> {
            List<GetCameraResponseDto> tmpCameraList = parentRegionIndexCameraMap.get(cameraResponseDto.getParentIndexCode());
            if (tmpCameraList == null) {
                tmpCameraList = new ArrayList<>();
                parentRegionIndexCameraMap.put(cameraResponseDto.getParentIndexCode(), tmpCameraList);
            }
            tmpCameraList.add(cameraResponseDto);
        });

        Map<String, OrgDto> orgDtoMap = new HashMap<>();
        Map<String, List<OrgDto>> parentChildOrgDtoIndexCodeMap = new HashMap<>();
        List<OrgDto> orgDtoList = apiService.getAllOrgDto(customArtemisHttpUtil);
        getRegionMessage(orgDtoList, orgDtoMap, parentChildOrgDtoIndexCodeMap);
        OrgDto rootOrgDto = orgDtoMap.get(CommonConstant.ROOT_ORG_ID);

        List<VmsTreesVo.TreeDataBean> treeDataListBeans = new ArrayList<>();
        vmsTreesVo.setTreeDataList(treeDataListBeans);

        List<VmsTreesVo.TreeDataBean> parentTreeDataBeanList = new ArrayList<>();
        VmsTreesVo.TreeDataBean rootTreeDataBean = initRegionTreeBean(rootOrgDto, parentRegionIndexCameraMap);
        parentTreeDataBeanList.add(rootTreeDataBean);

        treeDataListBeans.add(rootTreeDataBean);

        List<VmsTreesVo.TreeDataBean> tmpTreeDataBeanList = new ArrayList<>();
        while (parentTreeDataBeanList.size() > 0) {
            parentTreeDataBeanList.stream().forEach(parentTreeDataBean -> {
                List<OrgDto> childOrgList = parentChildOrgDtoIndexCodeMap.get(parentTreeDataBean.getRegionIndexCode());

                if (childOrgList != null) {
                    childOrgList.stream().forEach(childTbRegion -> {
                        VmsTreesVo.TreeDataBean childTreeDataBean = initRegionTreeBean(childTbRegion, parentRegionIndexCameraMap);
                        parentTreeDataBean.getChildren().add(childTreeDataBean);
                        tmpTreeDataBeanList.add(childTreeDataBean);
                    });
                }
            });
            parentTreeDataBeanList.clear();
            parentTreeDataBeanList.addAll(tmpTreeDataBeanList);
            tmpTreeDataBeanList.clear();
        }

        return vmsTreesVo;
    }

    @Override
    public MainCameraVo queryMainCamera() throws CustomException {
        CustomArtemisHttpUtil customArtemisHttpUtil = CommonUtil.getCustomArtemisHttpUtil();

        MainCameraVo mainCameraVo = new MainCameraVo();
        VmsTreesVo.ConfigBean configBean = new VmsTreesVo.ConfigBean();
        mainCameraVo.setConfig(configBean);

        List<VmsTreesVo.TreeDataBean> treeDataBeanList = new ArrayList<>();
        mainCameraVo.setTreeDataList(treeDataBeanList);

        configBean.setAppkey(propertyBean.getAppkey());
        configBean.setSecret(propertyBean.getAppSecret());

        String hikIpPort = propertyBean.getHikIp();
        if (!TextUtils.isEmpty(hikIpPort)) {
            if (hikIpPort.contains(":")) {
                String[] ipPortArray = hikIpPort.split(":");
                String hikIp = ipPortArray[0];
                String port = ipPortArray[1];
                configBean.setServerIp(hikIp);
                configBean.setPort(Integer.parseInt(port));
            } else {
                configBean.setServerIp(hikIpPort);
                configBean.setPort(443);
            }
        }

        treeDataBeanList.addAll(propertyBean.getMainCameraIndexCodes().stream().map(mainCameraIndexCode->{
            VmsTreesVo.TreeDataBean treeDataBean = new VmsTreesVo.TreeDataBean();
            try {
                String hlsUrl = apiService.getCameraPreview(mainCameraIndexCode, customArtemisHttpUtil);
                log.info("mainCameraIndexCode:{},hlsUrl:{}",mainCameraIndexCode,hlsUrl);
                treeDataBean.setCameraIndexCode(mainCameraIndexCode);
                treeDataBean.setHlsUrl(hlsUrl);
            }catch(Exception e){
                log.error("error",e);
                return null;
            }
            return treeDataBean;
        }).collect(Collectors.toList()));

        return mainCameraVo;
    }

    @Override
    public MainCameraVo queryThirtyCamera() {
        CustomArtemisHttpUtil customArtemisHttpUtil = CommonUtil.getCustomArtemisHttpUtil();

        MainCameraVo mainCameraVo1 = new MainCameraVo();
        VmsTreesVo.ConfigBean configBean = new VmsTreesVo.ConfigBean();
        mainCameraVo1.setConfig(configBean);

        List<VmsTreesVo.TreeDataBean> treeDataBeanList1 = new ArrayList<>();
        mainCameraVo1.setTreeDataList(treeDataBeanList1);

        configBean.setAppkey(propertyBean.getAppkey());
        configBean.setSecret(propertyBean.getAppSecret());

        String hikIpPort = propertyBean.getHikIp();
        if (!TextUtils.isEmpty(hikIpPort)) {
            if (hikIpPort.contains(":")) {
                String[] ipPortArray = hikIpPort.split(":");
                String hikIp = ipPortArray[0];
                String port = ipPortArray[1];
                configBean.setServerIp(hikIp);
                configBean.setPort(Integer.parseInt(port));
            } else {
                configBean.setServerIp(hikIpPort);
                configBean.setPort(443);
            }
        }

        treeDataBeanList1.addAll(propertyBean.getThirtyCamera().stream().map(thirtyCamera->{
            VmsTreesVo.TreeDataBean treeDataBean1 = new VmsTreesVo.TreeDataBean();
            try {
                String hlsUrl = apiService.getCameraPreview(thirtyCamera, customArtemisHttpUtil);
                log.info("thirtyCamera:{},hlsUrl:{}",thirtyCamera,hlsUrl);
                treeDataBean1.setCameraIndexCode(thirtyCamera);
                treeDataBean1.setHlsUrl(hlsUrl);
            }catch(Exception e){
                log.error("error",e);
                return null;
            }
            return treeDataBean1;
        }).collect(Collectors.toList()));

        return mainCameraVo1;
    }

    /**
     * 创建区域树对象
     */
    private VmsTreesVo.TreeDataBean initRegionTreeBean(OrgDto tmpOrg,
                                                       Map<String, List<GetCameraResponseDto>> orgCameraMap) {
        VmsTreesVo.TreeDataBean treeDataBean = new VmsTreesVo.TreeDataBean();
        treeDataBean.setRegionIndexCode(tmpOrg.getIndexCode());
        treeDataBean.setName(tmpOrg.getName());
        treeDataBean.setIcon("h-icon-menu_f");
        if (treeDataBean.getChildren() == null) {
            treeDataBean.setChildren(new ArrayList<>());
        }
        List<GetCameraResponseDto> childCameraList = orgCameraMap.get(tmpOrg.getIndexCode());
        if (childCameraList != null) {
            childCameraList.stream().forEach(tbCamera -> {
                VmsTreesVo.TreeDataBean childTreeBean = getTreeDataBeanFromCamera(tbCamera);
                treeDataBean.getChildren().add(childTreeBean);
            });
        }
        return treeDataBean;
    }

    /**
     * 将设备信息转换成TreeDataBean
     */
//    private VmsTreesVo.TreeDataBean getTreeDataBeanFromDevice(TbEncodeDevice tbEncodeDevice) {
//        VmsTreesVo.TreeDataBean treeDataBean = new VmsTreesVo.TreeDataBean();
//        treeDataBean.setRegionIndexCode(tbEncodeDevice.getIndexCode());
//        treeDataBean.setName(tbEncodeDevice.getName());
//        treeDataBean.setIcon("h-icon-menu_f");
//        return treeDataBean;
//    }
    private VmsTreesVo.TreeDataBean getTreeDataBeanFromCamera(GetCameraResponseDto tbCamera) {
        VmsTreesVo.TreeDataBean childTreeBean = new VmsTreesVo.TreeDataBean();
        childTreeBean.setIcon("el-icon-video-camera");
        childTreeBean.setResourceType(CommonConstant.ResourceType.ReourceType_Camera);
        childTreeBean.setName(tbCamera.getName());
        childTreeBean.setCameraIndexCode(tbCamera.getIndexcode());
        childTreeBean.setRegionIndexCode(tbCamera.getParentIndexCode());
        return childTreeBean;
    }

    /**
     * 获取区域信息
     */
    private void getRegionMessage(List<OrgDto> orgDtoList, Map<String, OrgDto> regionDtoMap, Map<String, List<OrgDto>> parentChildOrgDtoDtoIndexCodeMap) {
        orgDtoList.stream().forEach(orgDto -> {
                    regionDtoMap.put(orgDto.getIndexCode(), orgDto);
                    List<OrgDto> childOrgDtoList = parentChildOrgDtoDtoIndexCodeMap.get(orgDto.getParentIndexCode());
                    if (childOrgDtoList == null) {
                        childOrgDtoList = new ArrayList<>();
                        parentChildOrgDtoDtoIndexCodeMap.put(orgDto.getParentIndexCode(), childOrgDtoList);
                    }
                    childOrgDtoList.add(orgDto);
                }
        );
    }

    private CustomArtemisHttpUtil getCustomArtemisHttpUtil() {
        CustomArtemisHttpUtil customArtemisHttpUtil = new CustomArtemisHttpUtil(propertyBean.getHikIp(),
                propertyBean.getAppkey(), propertyBean.getAppSecret());
        return customArtemisHttpUtil;
    }
}
