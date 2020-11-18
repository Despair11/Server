package org.jeecg.modules.xjdatascreen.datasingleticket.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import org.jeecg.common.api.vo.Result;
import org.jeecg.modules.xjdatascreen.config.ExcelListener;
import org.jeecg.modules.xjdatascreen.datasingleticket.entity.DataSingleTicket;
import org.jeecg.modules.xjdatascreen.datasingleticket.service.IDataSingleTicketService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;

import org.jeecg.common.system.base.controller.JeecgController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jeecg.common.aspect.annotation.AutoLog;

 /**
 * @Description: data_single_ticket
 * @Author: jeecg-boot
 * @Date:   2020-11-18
 * @Version: V1.0
 */
@Api(tags="data_single_ticket_单票订单管理")
@RestController
@RequestMapping("/datasingleticket/dataSingleTicket")
@Slf4j
public class DataSingleTicketController extends JeecgController<DataSingleTicket, IDataSingleTicketService> {
	@Autowired
	private IDataSingleTicketService dataSingleTicketService;
	
//	/**
//	 * 分页列表查询
//	 *
//	 * @param dataSingleTicket
//	 * @param pageNo
//	 * @param pageSize
//	 * @param req
//	 * @return
//	 */
//	@AutoLog(value = "data_single_ticket-分页列表查询")
//	@ApiOperation(value="data_single_ticket-分页列表查询", notes="data_single_ticket-分页列表查询")
//	@GetMapping(value = "/list")
//	public Result<?> queryPageList(DataSingleTicket dataSingleTicket,
//								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
//								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
//								   HttpServletRequest req) {
//		QueryWrapper<DataSingleTicket> queryWrapper = QueryGenerator.initQueryWrapper(dataSingleTicket, req.getParameterMap());
//		Page<DataSingleTicket> page = new Page<DataSingleTicket>(pageNo, pageSize);
//		IPage<DataSingleTicket> pageList = dataSingleTicketService.page(page, queryWrapper);
//		return Result.ok(pageList);
//	}
//
//	/**
//	 *   添加
//	 *
//	 * @param dataSingleTicket
//	 * @return
//	 */
//	@AutoLog(value = "data_single_ticket-添加")
//	@ApiOperation(value="data_single_ticket-添加", notes="data_single_ticket-添加")
//	@PostMapping(value = "/add")
//	public Result<?> add(@RequestBody DataSingleTicket dataSingleTicket) {
//		dataSingleTicketService.save(dataSingleTicket);
//		return Result.ok("添加成功！");
//	}
//
//	/**
//	 *  编辑
//	 *
//	 * @param dataSingleTicket
//	 * @return
//	 */
//	@AutoLog(value = "data_single_ticket-编辑")
//	@ApiOperation(value="data_single_ticket-编辑", notes="data_single_ticket-编辑")
//	@PutMapping(value = "/edit")
//	public Result<?> edit(@RequestBody DataSingleTicket dataSingleTicket) {
//		dataSingleTicketService.updateById(dataSingleTicket);
//		return Result.ok("编辑成功!");
//	}
//
//	/**
//	 *   通过id删除
//	 *
//	 * @param id
//	 * @return
//	 */
//	@AutoLog(value = "data_single_ticket-通过id删除")
//	@ApiOperation(value="data_single_ticket-通过id删除", notes="data_single_ticket-通过id删除")
//	@DeleteMapping(value = "/delete")
//	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
//		dataSingleTicketService.removeById(id);
//		return Result.ok("删除成功!");
//	}
//
//	/**
//	 *  批量删除
//	 *
//	 * @param ids
//	 * @return
//	 */
//	@AutoLog(value = "data_single_ticket-批量删除")
//	@ApiOperation(value="data_single_ticket-批量删除", notes="data_single_ticket-批量删除")
//	@DeleteMapping(value = "/deleteBatch")
//	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
//		this.dataSingleTicketService.removeByIds(Arrays.asList(ids.split(",")));
//		return Result.ok("批量删除成功!");
//	}
//
//	/**
//	 * 通过id查询
//	 *
//	 * @param id
//	 * @return
//	 */
//	@AutoLog(value = "data_single_ticket-通过id查询")
//	@ApiOperation(value="data_single_ticket-通过id查询", notes="data_single_ticket-通过id查询")
//	@GetMapping(value = "/queryById")
//	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
//		DataSingleTicket dataSingleTicket = dataSingleTicketService.getById(id);
//		if(dataSingleTicket==null) {
//			return Result.error("未找到对应数据");
//		}
//		return Result.ok(dataSingleTicket);
//	}
//
    /**
    * 导出excel
    *
    * @param request
    * @param dataSingleTicket
    */
    @ApiOperation(value = "票务(单票)数据的导出", notes = "票务(单票)数据的导出")
    @RequestMapping(value = "/exportXls", method = RequestMethod.GET)
    public ModelAndView exportXls(HttpServletRequest request, DataSingleTicket dataSingleTicket) {
        return super.exportXls(request, dataSingleTicket, DataSingleTicket.class, "单票订单excel");
    }

    /**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    @ApiOperation(value = "票务(单票)数据的上传", notes = "票务(单票)数据的上传")
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, DataSingleTicket.class);
    }


    @ApiOperation(value = "数据导入", notes = "数据导入")
     @PostMapping("/excelUp")
     public Result<?> excelUp(@RequestParam("file") MultipartFile file) throws IOException {
        InputStream inputStream = file.getInputStream();
        //实例化实现了AnalysisEventListener接口的类
        ExcelListener listener = new ExcelListener();
        //2、读取指定的excel文件
        EasyExcel.read(inputStream, DataSingleTicket.class, listener).sheet().doRead();
        //3、获取读取的结果
        //获取数据
        List<Object> list = listener.getDatas();
//        List<DataSingleTicket> dataSingleTickets = new ArrayList<>();
//        DataSingleTicket dataSingleTicket = new DataSingleTicket();
        DataSingleTicket dataSingleTicket;
        //转换数据类型,并插入到数据库
        for (int i = 0; i < list.size(); i++) {
            dataSingleTicket = (DataSingleTicket) list.get(i);
            dataSingleTicketService.save(dataSingleTicket);
        }
//        dataSingleTicketService.saveBatch(list);
        return Result.ok();
     }

}
