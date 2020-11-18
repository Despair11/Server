package org.jeecg.modules.xjdatascreen.dataroom.controller;

import java.util.Arrays;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.modules.xjdatascreen.dataroom.entity.DataRoom;
import org.jeecg.modules.xjdatascreen.dataroom.service.IDataRoomService;

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
 * @Description: data_room
 * @Author: jeecg-boot
 * @Date:   2020-11-18
 * @Version: V1.0
 */
@Api(tags="data_room_公安局住宿管理")
@RestController
@RequestMapping("/dataroom/dataRoom")
@Slf4j
public class DataRoomController extends JeecgController<DataRoom, IDataRoomService> {
	@Autowired
	private IDataRoomService dataRoomService;
	
	/**
	 * 分页列表查询
	 *
	 * @param dataRoom
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "data_room-分页列表查询")
	@ApiOperation(value="data_room-分页列表查询", notes="data_room-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(DataRoom dataRoom,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<DataRoom> queryWrapper = QueryGenerator.initQueryWrapper(dataRoom, req.getParameterMap());
		Page<DataRoom> page = new Page<DataRoom>(pageNo, pageSize);
		IPage<DataRoom> pageList = dataRoomService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param dataRoom
	 * @return
	 */
	@AutoLog(value = "data_room-添加")
	@ApiOperation(value="data_room-添加", notes="data_room-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody DataRoom dataRoom) {
		dataRoomService.save(dataRoom);
		return Result.ok("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param dataRoom
	 * @return
	 */
	@AutoLog(value = "data_room-编辑")
	@ApiOperation(value="data_room-编辑", notes="data_room-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody DataRoom dataRoom) {
		dataRoomService.updateById(dataRoom);
		return Result.ok("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "data_room-通过id删除")
	@ApiOperation(value="data_room-通过id删除", notes="data_room-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		dataRoomService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "data_room-批量删除")
	@ApiOperation(value="data_room-批量删除", notes="data_room-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.dataRoomService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "data_room-通过id查询")
	@ApiOperation(value="data_room-通过id查询", notes="data_room-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		DataRoom dataRoom = dataRoomService.getById(id);
		if(dataRoom==null) {
			return Result.error("未找到对应数据");
		}
		return Result.ok(dataRoom);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param dataRoom
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, DataRoom dataRoom) {
        return super.exportXls(request, dataRoom, DataRoom.class, "data_room");
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
        return super.importExcel(request, response, DataRoom.class);
    }

}
