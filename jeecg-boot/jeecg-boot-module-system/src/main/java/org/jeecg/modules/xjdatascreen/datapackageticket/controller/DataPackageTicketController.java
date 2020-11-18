package org.jeecg.modules.xjdatascreen.datapackageticket.controller;

import java.util.Arrays;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.modules.xjdatascreen.datapackageticket.entity.DataPackageTicket;
import org.jeecg.modules.xjdatascreen.datapackageticket.service.IDataPackageTicketService;

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
 * @Description: data_package_ticket
 * @Author: jeecg-boot
 * @Date:   2020-11-18
 * @Version: V1.0
 */
@Api(tags="data_package_ticket_套票订单管理")
@RestController
@RequestMapping("/datapackageticket/dataPackageTicket")
@Slf4j
public class DataPackageTicketController extends JeecgController<DataPackageTicket, IDataPackageTicketService> {
	@Autowired
	private IDataPackageTicketService dataPackageTicketService;
	
//	/**
//	 * 分页列表查询
//	 *
//	 * @param dataPackageTicket
//	 * @param pageNo
//	 * @param pageSize
//	 * @param req
//	 * @return
//	 */
//	@AutoLog(value = "data_package_ticket-分页列表查询")
//	@ApiOperation(value="data_package_ticket-分页列表查询", notes="data_package_ticket-分页列表查询")
//	@GetMapping(value = "/list")
//	public Result<?> queryPageList(DataPackageTicket dataPackageTicket,
//								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
//								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
//								   HttpServletRequest req) {
//		QueryWrapper<DataPackageTicket> queryWrapper = QueryGenerator.initQueryWrapper(dataPackageTicket, req.getParameterMap());
//		Page<DataPackageTicket> page = new Page<DataPackageTicket>(pageNo, pageSize);
//		IPage<DataPackageTicket> pageList = dataPackageTicketService.page(page, queryWrapper);
//		return Result.ok(pageList);
//	}
//
//	/**
//	 *   添加
//	 *
//	 * @param dataPackageTicket
//	 * @return
//	 */
//	@AutoLog(value = "data_package_ticket-添加")
//	@ApiOperation(value="data_package_ticket-添加", notes="data_package_ticket-添加")
//	@PostMapping(value = "/add")
//	public Result<?> add(@RequestBody DataPackageTicket dataPackageTicket) {
//		dataPackageTicketService.save(dataPackageTicket);
//		return Result.ok("添加成功！");
//	}
//
//	/**
//	 *  编辑
//	 *
//	 * @param dataPackageTicket
//	 * @return
//	 */
//	@AutoLog(value = "data_package_ticket-编辑")
//	@ApiOperation(value="data_package_ticket-编辑", notes="data_package_ticket-编辑")
//	@PutMapping(value = "/edit")
//	public Result<?> edit(@RequestBody DataPackageTicket dataPackageTicket) {
//		dataPackageTicketService.updateById(dataPackageTicket);
//		return Result.ok("编辑成功!");
//	}
//
//	/**
//	 *   通过id删除
//	 *
//	 * @param id
//	 * @return
//	 */
//	@AutoLog(value = "data_package_ticket-通过id删除")
//	@ApiOperation(value="data_package_ticket-通过id删除", notes="data_package_ticket-通过id删除")
//	@DeleteMapping(value = "/delete")
//	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
//		dataPackageTicketService.removeById(id);
//		return Result.ok("删除成功!");
//	}
//
//	/**
//	 *  批量删除
//	 *
//	 * @param ids
//	 * @return
//	 */
//	@AutoLog(value = "data_package_ticket-批量删除")
//	@ApiOperation(value="data_package_ticket-批量删除", notes="data_package_ticket-批量删除")
//	@DeleteMapping(value = "/deleteBatch")
//	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
//		this.dataPackageTicketService.removeByIds(Arrays.asList(ids.split(",")));
//		return Result.ok("批量删除成功!");
//	}
//
//	/**
//	 * 通过id查询
//	 *
//	 * @param id
//	 * @return
//	 */
//	@AutoLog(value = "data_package_ticket-通过id查询")
//	@ApiOperation(value="data_package_ticket-通过id查询", notes="data_package_ticket-通过id查询")
//	@GetMapping(value = "/queryById")
//	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
//		DataPackageTicket dataPackageTicket = dataPackageTicketService.getById(id);
//		if(dataPackageTicket==null) {
//			return Result.error("未找到对应数据");
//		}
//		return Result.ok(dataPackageTicket);
//	}
//
//    /**
//    * 导出excel
//    *
//    * @param request
//    * @param dataPackageTicket
//    */
//    @RequestMapping(value = "/exportXls")
//    public ModelAndView exportXls(HttpServletRequest request, DataPackageTicket dataPackageTicket) {
//        return super.exportXls(request, dataPackageTicket, DataPackageTicket.class, "data_package_ticket");
//    }

    /**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    @ApiOperation(value = "票务(套票)数据上传", notes = "票务(套票)数据上传")
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, DataPackageTicket.class);
    }

}
