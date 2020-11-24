package org.jeecg.modules.xjdatascreen.datascenicinfo.controller;

import java.util.Arrays;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.modules.xjdatascreen.datascenicinfo.entity.ScenicInfo;
import org.jeecg.modules.xjdatascreen.datascenicinfo.service.IScenicInfoService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;

import org.jeecg.common.system.base.controller.JeecgController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jeecg.common.aspect.annotation.AutoLog;

 /**
 * @Description: scenic_info
 * @Author: jeecg-boot
 * @Date:   2020-11-24
 * @Version: V1.0
 */
@Api(tags="scenic_info")
@RestController
@RequestMapping("/datascenicinfo/scenicInfo")
@Slf4j
public class ScenicInfoController extends JeecgController<ScenicInfo, IScenicInfoService> {
	@Autowired
	private IScenicInfoService scenicInfoService;
	
	/**
	 * 分页列表查询
	 *
	 * @param scenicInfo
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "scenic_info-分页列表查询")
	@ApiOperation(value="scenic_info-分页列表查询", notes="scenic_info-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(ScenicInfo scenicInfo,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<ScenicInfo> queryWrapper = QueryGenerator.initQueryWrapper(scenicInfo, req.getParameterMap());
		Page<ScenicInfo> page = new Page<ScenicInfo>(pageNo, pageSize);
		IPage<ScenicInfo> pageList = scenicInfoService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param scenicInfo
	 * @return
	 */
	@AutoLog(value = "scenic_info-添加")
	@ApiOperation(value="scenic_info-添加", notes="scenic_info-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody ScenicInfo scenicInfo) {
		scenicInfoService.save(scenicInfo);
		return Result.ok("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param scenicInfo
	 * @return
	 */
	@AutoLog(value = "scenic_info-编辑")
	@ApiOperation(value="scenic_info-编辑", notes="scenic_info-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody ScenicInfo scenicInfo) {
		scenicInfoService.updateById(scenicInfo);
		return Result.ok("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "scenic_info-通过id删除")
	@ApiOperation(value="scenic_info-通过id删除", notes="scenic_info-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		scenicInfoService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "scenic_info-批量删除")
	@ApiOperation(value="scenic_info-批量删除", notes="scenic_info-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.scenicInfoService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "scenic_info-通过id查询")
	@ApiOperation(value="scenic_info-通过id查询", notes="scenic_info-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		ScenicInfo scenicInfo = scenicInfoService.getById(id);
		if(scenicInfo==null) {
			return Result.error("未找到对应数据");
		}
		return Result.ok(scenicInfo);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param scenicInfo
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, ScenicInfo scenicInfo) {
        return super.exportXls(request, scenicInfo, ScenicInfo.class, "scenic_info");
    }

    /**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, ScenicInfo.class);
    }

}
