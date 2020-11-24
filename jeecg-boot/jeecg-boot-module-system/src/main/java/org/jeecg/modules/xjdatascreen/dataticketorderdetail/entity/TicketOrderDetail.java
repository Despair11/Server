package org.jeecg.modules.xjdatascreen.dataticketorderdetail.entity;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecg.common.aspect.annotation.Dict;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @Description: ticket_order_detail
 * @Author: jeecg-boot
 * @Date:   2020-11-24
 * @Version: V1.0
 */
@Data
@TableName("ticket_order_detail")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="ticket_order_detail对象", description="ticket_order_detail")
public class TicketOrderDetail implements Serializable {
    private static final long serialVersionUID = 1L;

	/**id*/
	@TableId(type = IdType.ID_WORKER_STR)
    @ApiModelProperty(value = "id")
    private java.lang.Integer id;
	/**订单详情编号*/
	@Excel(name = "订单详情编号", width = 15)
    @ApiModelProperty(value = "订单详情编号")
    private java.lang.String orderDetailId;
	/**订单编号*/
	@Excel(name = "订单编号", width = 15)
    @ApiModelProperty(value = "订单编号")
    private java.lang.String orderNum;
	/**票的id*/
	@Excel(name = "票的id", width = 15)
    @ApiModelProperty(value = "票的id")
    private java.lang.Integer ticketId;
	/**是否一人多票*/
	@Excel(name = "是否一人多票", width = 15)
    @ApiModelProperty(value = "是否一人多票")
    private java.lang.Integer isMore;
	/**是否实名制*/
	@Excel(name = "是否实名制", width = 15)
    @ApiModelProperty(value = "是否实名制")
    private java.lang.Integer isRealName;
	/**票的产品编号*/
	@Excel(name = "票的产品编号", width = 15)
    @ApiModelProperty(value = "票的产品编号")
    private java.lang.String ticketCode;
	/**单价*/
	@Excel(name = "单价", width = 15)
    @ApiModelProperty(value = "单价")
    private java.lang.String unitPrice;
	/**总价*/
	@Excel(name = "总价", width = 15)
    @ApiModelProperty(value = "总价")
    private java.lang.String totalPrice;
	/**数量*/
	@Excel(name = "数量", width = 15)
    @ApiModelProperty(value = "数量")
    private java.lang.Integer quantity;
	/**票种名称*/
	@Excel(name = "票种名称", width = 15)
    @ApiModelProperty(value = "票种名称")
    private java.lang.String typeName;
	/**票的名称*/
	@Excel(name = "票的名称", width = 15)
    @ApiModelProperty(value = "票的名称")
    private java.lang.String ticketName;
	/**房型名称*/
	@Excel(name = "房型名称", width = 15)
    @ApiModelProperty(value = "房型名称")
    private java.lang.String roomName;
	/**房型id*/
	@Excel(name = "房型id", width = 15)
    @ApiModelProperty(value = "房型id")
    private java.lang.Integer hotelRoomId;
	/**房型编号*/
	@Excel(name = "房型编号", width = 15)
    @ApiModelProperty(value = "房型编号")
    private java.lang.String hotelRoomCode;
	/**使用日期*/
	@Excel(name = "使用日期", width = 15)
    @ApiModelProperty(value = "使用日期")
    private java.lang.String useDate;
	/**票的日库存id*/
	@Excel(name = "票的日库存id", width = 15)
    @ApiModelProperty(value = "票的日库存id")
    private java.lang.Integer timeDivisionStockId;
	/**使用数量*/
	@Excel(name = "使用数量", width = 15)
    @ApiModelProperty(value = "使用数量")
    private java.lang.Integer useCount;
	/**退的数量*/
	@Excel(name = "退的数量", width = 15)
    @ApiModelProperty(value = "退的数量")
    private java.lang.Integer cancelCount;
	/**使用有效截止日期*/
	@Excel(name = "使用有效截止日期", width = 15)
    @ApiModelProperty(value = "使用有效截止日期")
    private java.lang.String endDate;
	/**票的上一级编号*/
	@Excel(name = "票的上一级编号", width = 15)
    @ApiModelProperty(value = "票的上一级编号")
    private java.lang.String superTicketCode;
	/**分时库存时段开始时间 HH:mm*/
	@Excel(name = "分时库存时段开始时间 HH:mm", width = 15)
    @ApiModelProperty(value = "分时库存时段开始时间 HH:mm")
    private java.lang.String startTime;
	/**分时库存时段结束时间 HH:mm*/
	@Excel(name = "分时库存时段结束时间 HH:mm", width = 15)
    @ApiModelProperty(value = "分时库存时段结束时间 HH:mm")
    private java.lang.String endTime;
	/**二次核验的数量*/
	@Excel(name = "二次核验的数量", width = 15)
    @ApiModelProperty(value = "二次核验的数量")
    private java.lang.Integer doubleCheckCount;
	/**已退款金额*/
	@Excel(name = "已退款金额", width = 15)
    @ApiModelProperty(value = "已退款金额")
    private java.lang.String refundAmount;
	/**OTA售卖给用户的价格*/
	@Excel(name = "OTA售卖给用户的价格", width = 15)
    @ApiModelProperty(value = "OTA售卖给用户的价格")
    private java.lang.String otaBuyPrice;
	/**OTA产品编号*/
	@Excel(name = "OTA产品编号", width = 15)
    @ApiModelProperty(value = "OTA产品编号")
    private java.lang.String otaProductCode;
}
