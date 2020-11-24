package org.jeecg.modules.xjdatascreen.dataticketorder.controller;

import java.util.Arrays;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.modules.xjdatascreen.dataticketorder.entity.TicketOrder;
import org.jeecg.modules.xjdatascreen.dataticketorder.service.ITicketOrderService;

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
 * @Description: ticket_order
 * @Author: jeecg-boot
 * @Date:   2020-11-24
 * @Version: V1.0
 */
@Api(tags="ticket_order")
@RestController
@RequestMapping("/dataticketorder/ticketOrder")
@Slf4j
public class TicketOrderController extends JeecgController<TicketOrder, ITicketOrderService> {
	@Autowired
	private ITicketOrderService ticketOrderService;
	
	/**
	 * 分页列表查询
	 *
	 * @param ticketOrder
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "ticket_order-分页列表查询")
	@ApiOperation(value="ticket_order-分页列表查询", notes="ticket_order-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(TicketOrder ticketOrder,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<TicketOrder> queryWrapper = QueryGenerator.initQueryWrapper(ticketOrder, req.getParameterMap());
		Page<TicketOrder> page = new Page<TicketOrder>(pageNo, pageSize);
		IPage<TicketOrder> pageList = ticketOrderService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param ticketOrder
	 * @return
	 */
	@AutoLog(value = "ticket_order-添加")
	@ApiOperation(value="ticket_order-添加", notes="ticket_order-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody TicketOrder ticketOrder) {
		ticketOrderService.save(ticketOrder);
		return Result.ok("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param ticketOrder
	 * @return
	 */
	@AutoLog(value = "ticket_order-编辑")
	@ApiOperation(value="ticket_order-编辑", notes="ticket_order-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody TicketOrder ticketOrder) {
		ticketOrderService.updateById(ticketOrder);
		return Result.ok("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "ticket_order-通过id删除")
	@ApiOperation(value="ticket_order-通过id删除", notes="ticket_order-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		ticketOrderService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "ticket_order-批量删除")
	@ApiOperation(value="ticket_order-批量删除", notes="ticket_order-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.ticketOrderService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "ticket_order-通过id查询")
	@ApiOperation(value="ticket_order-通过id查询", notes="ticket_order-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		TicketOrder ticketOrder = ticketOrderService.getById(id);
		if(ticketOrder==null) {
			return Result.error("未找到对应数据");
		}
		return Result.ok(ticketOrder);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param ticketOrder
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, TicketOrder ticketOrder) {
        return super.exportXls(request, ticketOrder, TicketOrder.class, "ticket_order");
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
        return super.importExcel(request, response, TicketOrder.class);
    }

}
