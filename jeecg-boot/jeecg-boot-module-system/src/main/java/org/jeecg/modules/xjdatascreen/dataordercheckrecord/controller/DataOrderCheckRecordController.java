package org.jeecg.modules.xjdatascreen.dataordercheckrecord.controller;

import java.util.Arrays;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.modules.xjdatascreen.dataordercheckrecord.entity.DataOrderCheckRecord;
import org.jeecg.modules.xjdatascreen.dataordercheckrecord.service.IDataOrderCheckRecordService;

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
 * @Description: data_order_check_record
 * @Author: jeecg-boot
 * @Date:   2020-11-21
 * @Version: V1.0
 */
@Api(tags="data_order_check_record")
@RestController
@RequestMapping("/dataordercheckrecord/dataOrderCheckRecord")
@Slf4j
public class DataOrderCheckRecordController extends JeecgController<DataOrderCheckRecord, IDataOrderCheckRecordService> {
	@Autowired
	private IDataOrderCheckRecordService dataOrderCheckRecordService;
	
	/**
	 * 分页列表查询
	 *
	 * @param dataOrderCheckRecord
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "data_order_check_record-分页列表查询")
	@ApiOperation(value="data_order_check_record-分页列表查询", notes="data_order_check_record-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(DataOrderCheckRecord dataOrderCheckRecord,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<DataOrderCheckRecord> queryWrapper = QueryGenerator.initQueryWrapper(dataOrderCheckRecord, req.getParameterMap());
		Page<DataOrderCheckRecord> page = new Page<DataOrderCheckRecord>(pageNo, pageSize);
		IPage<DataOrderCheckRecord> pageList = dataOrderCheckRecordService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param dataOrderCheckRecord
	 * @return
	 */
	@AutoLog(value = "data_order_check_record-添加")
	@ApiOperation(value="data_order_check_record-添加", notes="data_order_check_record-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody DataOrderCheckRecord dataOrderCheckRecord) {
		dataOrderCheckRecordService.save(dataOrderCheckRecord);
		return Result.ok("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param dataOrderCheckRecord
	 * @return
	 */
	@AutoLog(value = "data_order_check_record-编辑")
	@ApiOperation(value="data_order_check_record-编辑", notes="data_order_check_record-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody DataOrderCheckRecord dataOrderCheckRecord) {
		dataOrderCheckRecordService.updateById(dataOrderCheckRecord);
		return Result.ok("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "data_order_check_record-通过id删除")
	@ApiOperation(value="data_order_check_record-通过id删除", notes="data_order_check_record-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		dataOrderCheckRecordService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "data_order_check_record-批量删除")
	@ApiOperation(value="data_order_check_record-批量删除", notes="data_order_check_record-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.dataOrderCheckRecordService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "data_order_check_record-通过id查询")
	@ApiOperation(value="data_order_check_record-通过id查询", notes="data_order_check_record-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		DataOrderCheckRecord dataOrderCheckRecord = dataOrderCheckRecordService.getById(id);
		if(dataOrderCheckRecord==null) {
			return Result.error("未找到对应数据");
		}
		return Result.ok(dataOrderCheckRecord);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param dataOrderCheckRecord
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, DataOrderCheckRecord dataOrderCheckRecord) {
        return super.exportXls(request, dataOrderCheckRecord, DataOrderCheckRecord.class, "data_order_check_record");
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
        return super.importExcel(request, response, DataOrderCheckRecord.class);
    }

}
