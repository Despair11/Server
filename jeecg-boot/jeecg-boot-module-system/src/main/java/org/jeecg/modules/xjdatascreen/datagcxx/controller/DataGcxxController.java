package org.jeecg.modules.xjdatascreen.datagcxx.controller;

import java.util.Arrays;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.modules.xjdatascreen.datagcxx.entity.DataGcxx;
import org.jeecg.modules.xjdatascreen.datagcxx.service.IDataGcxxService;

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
 * @Description: data_gcxx
 * @Author: jeecg-boot
 * @Date:   2020-11-18
 * @Version: V1.0
 */
@Api(tags="data_gcxx_过车信息管理")
@RestController
@RequestMapping("/datagcxx/dataGcxx")
@Slf4j
public class DataGcxxController extends JeecgController<DataGcxx, IDataGcxxService> {
	@Autowired
	private IDataGcxxService dataGcxxService;
	
	/**
	 * 分页列表查询
	 *
	 * @param dataGcxx
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "data_gcxx-分页列表查询")
	@ApiOperation(value="data_gcxx-分页列表查询", notes="data_gcxx-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(DataGcxx dataGcxx,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<DataGcxx> queryWrapper = QueryGenerator.initQueryWrapper(dataGcxx, req.getParameterMap());
		Page<DataGcxx> page = new Page<DataGcxx>(pageNo, pageSize);
		IPage<DataGcxx> pageList = dataGcxxService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param dataGcxx
	 * @return
	 */
	@AutoLog(value = "data_gcxx-添加")
	@ApiOperation(value="data_gcxx-添加", notes="data_gcxx-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody DataGcxx dataGcxx) {
		dataGcxxService.save(dataGcxx);
		return Result.ok("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param dataGcxx
	 * @return
	 */
	@AutoLog(value = "data_gcxx-编辑")
	@ApiOperation(value="data_gcxx-编辑", notes="data_gcxx-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody DataGcxx dataGcxx) {
		dataGcxxService.updateById(dataGcxx);
		return Result.ok("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "data_gcxx-通过id删除")
	@ApiOperation(value="data_gcxx-通过id删除", notes="data_gcxx-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		dataGcxxService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "data_gcxx-批量删除")
	@ApiOperation(value="data_gcxx-批量删除", notes="data_gcxx-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.dataGcxxService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "data_gcxx-通过id查询")
	@ApiOperation(value="data_gcxx-通过id查询", notes="data_gcxx-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		DataGcxx dataGcxx = dataGcxxService.getById(id);
		if(dataGcxx==null) {
			return Result.error("未找到对应数据");
		}
		return Result.ok(dataGcxx);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param dataGcxx
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, DataGcxx dataGcxx) {
        return super.exportXls(request, dataGcxx, DataGcxx.class, "data_gcxx");
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
        return super.importExcel(request, response, DataGcxx.class);
    }

}
