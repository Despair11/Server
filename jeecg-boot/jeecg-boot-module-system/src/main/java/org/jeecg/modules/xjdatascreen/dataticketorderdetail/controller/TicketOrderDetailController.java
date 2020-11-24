package org.jeecg.modules.xjdatascreen.dataticketorderdetail.controller;

import java.util.Arrays;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.modules.xjdatascreen.dataticketorderdetail.entity.TicketOrderDetail;
import org.jeecg.modules.xjdatascreen.dataticketorderdetail.service.ITicketOrderDetailService;

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
 * @Description: ticket_order_detail
 * @Author: jeecg-boot
 * @Date:   2020-11-24
 * @Version: V1.0
 */
@Api(tags="ticket_order_detail")
@RestController
@RequestMapping("/dataticketorderdetail/ticketOrderDetail")
@Slf4j
public class TicketOrderDetailController extends JeecgController<TicketOrderDetail, ITicketOrderDetailService> {
	@Autowired
	private ITicketOrderDetailService ticketOrderDetailService;
	
	/**
	 * 分页列表查询
	 *
	 * @param ticketOrderDetail
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "ticket_order_detail-分页列表查询")
	@ApiOperation(value="ticket_order_detail-分页列表查询", notes="ticket_order_detail-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(TicketOrderDetail ticketOrderDetail,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<TicketOrderDetail> queryWrapper = QueryGenerator.initQueryWrapper(ticketOrderDetail, req.getParameterMap());
		Page<TicketOrderDetail> page = new Page<TicketOrderDetail>(pageNo, pageSize);
		IPage<TicketOrderDetail> pageList = ticketOrderDetailService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param ticketOrderDetail
	 * @return
	 */
	@AutoLog(value = "ticket_order_detail-添加")
	@ApiOperation(value="ticket_order_detail-添加", notes="ticket_order_detail-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody TicketOrderDetail ticketOrderDetail) {
		ticketOrderDetailService.save(ticketOrderDetail);
		return Result.ok("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param ticketOrderDetail
	 * @return
	 */
	@AutoLog(value = "ticket_order_detail-编辑")
	@ApiOperation(value="ticket_order_detail-编辑", notes="ticket_order_detail-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody TicketOrderDetail ticketOrderDetail) {
		ticketOrderDetailService.updateById(ticketOrderDetail);
		return Result.ok("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "ticket_order_detail-通过id删除")
	@ApiOperation(value="ticket_order_detail-通过id删除", notes="ticket_order_detail-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		ticketOrderDetailService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "ticket_order_detail-批量删除")
	@ApiOperation(value="ticket_order_detail-批量删除", notes="ticket_order_detail-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.ticketOrderDetailService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "ticket_order_detail-通过id查询")
	@ApiOperation(value="ticket_order_detail-通过id查询", notes="ticket_order_detail-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		TicketOrderDetail ticketOrderDetail = ticketOrderDetailService.getById(id);
		if(ticketOrderDetail==null) {
			return Result.error("未找到对应数据");
		}
		return Result.ok(ticketOrderDetail);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param ticketOrderDetail
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, TicketOrderDetail ticketOrderDetail) {
        return super.exportXls(request, ticketOrderDetail, TicketOrderDetail.class, "ticket_order_detail");
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
        return super.importExcel(request, response, TicketOrderDetail.class);
    }

}
