package org.jeecg.modules.system.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.constant.CacheConstant;
import org.jeecg.common.constant.CommonConstant;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.system.util.JwtUtil;
import org.jeecg.common.system.vo.LoginUser;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.system.entity.SysDepart;
import org.jeecg.modules.system.model.DepartIdModel;
import org.jeecg.modules.system.model.SysDepartTreeModel;
import org.jeecg.modules.system.service.ISysDepartService;
import org.jeecg.modules.system.util.FindsDepartsChildrenUtil;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * ????????? ???????????????
 * <p>
 * 
 * @Author: Steve @Since??? 2019-01-22
 */
@RestController
@RequestMapping("/sys/sysDepart")
@Slf4j
public class SysDepartController {

	@Autowired
	private ISysDepartService sysDepartService;

	/**
	 * ???????????? ??????????????????,??????????????????????????????????????????
	 *
	 * @return
	 */
	@RequestMapping(value = "/queryMyDeptTreeList", method = RequestMethod.GET)
	public Result<List<SysDepartTreeModel>> queryMyDeptTreeList() {
		Result<List<SysDepartTreeModel>> result = new Result<>();
		LoginUser user = (LoginUser) SecurityUtils.getSubject().getPrincipal();
		try {
			if(oConvertUtils.isNotEmpty(user.getIdentity()) && user.getIdentity() == CommonConstant.USER_IDENTITY_2 ){
				List<SysDepartTreeModel> list = sysDepartService.queryMyDeptTreeList(user.getDepartIds());
				result.setResult(list);
				result.setMessage(CommonConstant.USER_IDENTITY_2.toString());
				result.setSuccess(true);
			}else{
				result.setMessage(CommonConstant.USER_IDENTITY_1.toString());
				result.setSuccess(true);
			}
		} catch (Exception e) {
			log.error(e.getMessage(),e);
		}
		return result;
	}

	/**
	 * ???????????? ??????????????????,??????????????????????????????????????????
	 * 
	 * @return
	 */
	@RequestMapping(value = "/queryTreeList", method = RequestMethod.GET)
	public Result<List<SysDepartTreeModel>> queryTreeList() {
		Result<List<SysDepartTreeModel>> result = new Result<>();
		try {
			// ??????????????????
//			List<SysDepartTreeModel> list =FindsDepartsChildrenUtil.getSysDepartTreeList();
//			if (CollectionUtils.isEmpty(list)) {
//				list = sysDepartService.queryTreeList();
//			}
			List<SysDepartTreeModel> list = sysDepartService.queryTreeList();
			result.setResult(list);
			result.setSuccess(true);
		} catch (Exception e) {
			log.error(e.getMessage(),e);
		}
		return result;
	}

	/**
	 * ??????????????? ???????????????????????????????????????,?????????????????????
	 * 
	 * @param sysDepart
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@CacheEvict(value= {CacheConstant.SYS_DEPARTS_CACHE,CacheConstant.SYS_DEPART_IDS_CACHE}, allEntries=true)
	public Result<SysDepart> add(@RequestBody SysDepart sysDepart, HttpServletRequest request) {
		Result<SysDepart> result = new Result<SysDepart>();
		String username = JwtUtil.getUserNameByToken(request);
		try {
			sysDepart.setCreateBy(username);
			sysDepartService.saveDepartData(sysDepart, username);
			//?????????????????????
			// FindsDepartsChildrenUtil.clearSysDepartTreeList();
			// FindsDepartsChildrenUtil.clearDepartIdModel();
			result.success("???????????????");
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			result.error500("????????????");
		}
		return result;
	}

	/**
	 * ???????????? ???????????????????????????,?????????????????????
	 * 
	 * @param sysDepart
	 * @return
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.PUT)
	@CacheEvict(value= {CacheConstant.SYS_DEPARTS_CACHE,CacheConstant.SYS_DEPART_IDS_CACHE}, allEntries=true)
	public Result<SysDepart> edit(@RequestBody SysDepart sysDepart, HttpServletRequest request) {
		String username = JwtUtil.getUserNameByToken(request);
		sysDepart.setUpdateBy(username);
		Result<SysDepart> result = new Result<SysDepart>();
		SysDepart sysDepartEntity = sysDepartService.getById(sysDepart.getId());
		if (sysDepartEntity == null) {
			result.error500("?????????????????????");
		} else {
			boolean ok = sysDepartService.updateDepartDataById(sysDepart, username);
			// TODO ??????false???????????????
			if (ok) {
				//?????????????????????
				//FindsDepartsChildrenUtil.clearSysDepartTreeList();
				//FindsDepartsChildrenUtil.clearDepartIdModel();
				result.success("????????????!");
			}
		}
		return result;
	}
	
	 /**
     *   ??????id??????
    * @param id
    * @return
    */
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	@CacheEvict(value= {CacheConstant.SYS_DEPARTS_CACHE,CacheConstant.SYS_DEPART_IDS_CACHE}, allEntries=true)
   public Result<SysDepart> delete(@RequestParam(name="id",required=true) String id) {

       Result<SysDepart> result = new Result<SysDepart>();
       SysDepart sysDepart = sysDepartService.getById(id);
       if(sysDepart==null) {
           result.error500("?????????????????????");
       }else {
           boolean ok = sysDepartService.delete(id);
           if(ok) {
	            //?????????????????????
	   		   //FindsDepartsChildrenUtil.clearSysDepartTreeList();
	   		   // FindsDepartsChildrenUtil.clearDepartIdModel();
               result.success("????????????!");
           }
       }
       return result;
   }


	/**
	 * ???????????? ???????????????????????????ID,???????????????????????????????????????????????????
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "/deleteBatch", method = RequestMethod.DELETE)
	@CacheEvict(value= {CacheConstant.SYS_DEPARTS_CACHE,CacheConstant.SYS_DEPART_IDS_CACHE}, allEntries=true)
	public Result<SysDepart> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {

		Result<SysDepart> result = new Result<SysDepart>();
		if (ids == null || "".equals(ids.trim())) {
			result.error500("??????????????????");
		} else {
			this.sysDepartService.deleteBatchWithChildren(Arrays.asList(ids.split(",")));
			result.success("????????????!");
		}
		return result;
	}

	/**
	 * ???????????? ?????????????????????????????????????????????,?????????????????????????????????????????????,?????????????????????
	 * 
	 * @return
	 */
	@RequestMapping(value = "/queryIdTree", method = RequestMethod.GET)
	public Result<List<DepartIdModel>> queryIdTree() {
//		Result<List<DepartIdModel>> result = new Result<List<DepartIdModel>>();
//		List<DepartIdModel> idList;
//		try {
//			idList = FindsDepartsChildrenUtil.wrapDepartIdModel();
//			if (idList != null && idList.size() > 0) {
//				result.setResult(idList);
//				result.setSuccess(true);
//			} else {
//				sysDepartService.queryTreeList();
//				idList = FindsDepartsChildrenUtil.wrapDepartIdModel();
//				result.setResult(idList);
//				result.setSuccess(true);
//			}
//			return result;
//		} catch (Exception e) {
//			log.error(e.getMessage(),e);
//			result.setSuccess(false);
//			return result;
//		}
		Result<List<DepartIdModel>> result = new Result<>();
		try {
			List<DepartIdModel> list = sysDepartService.queryDepartIdTreeList();
			result.setResult(list);
			result.setSuccess(true);
		} catch (Exception e) {
			log.error(e.getMessage(),e);
		}
		return result;
	}
	 
	/**
	 * <p>
	 * ????????????????????????,???????????????????????????????????????
	 * </p>
	 * 
	 * @param keyWord
	 * @return
	 */
	@RequestMapping(value = "/searchBy", method = RequestMethod.GET)
	public Result<List<SysDepartTreeModel>> searchBy(@RequestParam(name = "keyWord", required = true) String keyWord) {
		Result<List<SysDepartTreeModel>> result = new Result<List<SysDepartTreeModel>>();
		List<SysDepartTreeModel> treeList = this.sysDepartService.searhBy(keyWord);
		if (treeList == null || treeList.size() == 0) {
			result.setSuccess(false);
			result.setMessage("????????????????????????");
			return result;
		}
		result.setResult(treeList);
		return result;
	}


	/**
     * ??????excel
     *
     * @param request
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(SysDepart sysDepart,HttpServletRequest request) {
        // Step.1 ??????????????????
        QueryWrapper<SysDepart> queryWrapper = QueryGenerator.initQueryWrapper(sysDepart, request.getParameterMap());
        //Step.2 AutoPoi ??????Excel
        ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
        List<SysDepart> pageList = sysDepartService.list(queryWrapper);
        //???????????????
        Collections.sort(pageList, new Comparator<SysDepart>() {
            @Override
			public int compare(SysDepart arg0, SysDepart arg1) {
            	return arg0.getOrgCode().compareTo(arg1.getOrgCode());
            }
        });
        //??????????????????
        mv.addObject(NormalExcelConstants.FILE_NAME, "????????????");
        mv.addObject(NormalExcelConstants.CLASS, SysDepart.class);
        LoginUser user = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("??????????????????", "?????????:"+user.getRealname(), "????????????"));
        mv.addObject(NormalExcelConstants.DATA_LIST, pageList);
        return mv;
    }

    /**
     * ??????excel????????????
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
	@CacheEvict(value= {CacheConstant.SYS_DEPARTS_CACHE,CacheConstant.SYS_DEPART_IDS_CACHE}, allEntries=true)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
        for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
            MultipartFile file = entity.getValue();// ????????????????????????
            ImportParams params = new ImportParams();
            params.setTitleRows(2);
            params.setHeadRows(1);
            params.setNeedSave(true);
            try {
            	// orgCode????????????
            	int codeLength = 3;
                List<SysDepart> listSysDeparts = ExcelImportUtil.importExcel(file.getInputStream(), SysDepart.class, params);
                //???????????????
                Collections.sort(listSysDeparts, new Comparator<SysDepart>() {
                    @Override
					public int compare(SysDepart arg0, SysDepart arg1) {
                    	return arg0.getOrgCode().length() - arg1.getOrgCode().length();
                    }
                });
                for (SysDepart sysDepart : listSysDeparts) {
                	String orgCode = sysDepart.getOrgCode();
                	if(orgCode.length() > codeLength) {
                		String parentCode = orgCode.substring(0, orgCode.length()-codeLength);
                		QueryWrapper<SysDepart> queryWrapper = new QueryWrapper<SysDepart>();
                		queryWrapper.eq("org_code", parentCode);
                		try {
                		SysDepart parentDept = sysDepartService.getOne(queryWrapper);
                		if(!parentDept.equals(null)) {
							sysDepart.setParentId(parentDept.getId());
						} else {
							sysDepart.setParentId("");
						}
                		}catch (Exception e) {
                			//???????????????parentDept
                		}
                	}else{
                		sysDepart.setParentId("");
					}
                    sysDepartService.save(sysDepart);
                }
                return Result.ok("????????????????????????????????????" + listSysDeparts.size());
            } catch (Exception e) {
                log.error(e.getMessage(),e);
                return Result.error("??????????????????:"+e.getMessage());
            } finally {
                try {
                    file.getInputStream().close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return Result.error("?????????????????????");
    }
}
