package org.jeecg.modules.xjdatascreen.datasingleticket.entity;

import java.io.Serializable;


import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @Description: data_single_ticket
 * @Author: jeecg-boot
 * @Date:   2020-11-18
 * @Version: V1.0
 */
@Data
@TableName("data_single_ticket")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="data_single_ticket对象", description="data_single_ticket")
public class DataSingleTicket implements Serializable {
    private static final long serialVersionUID = 1L;

	/**单票订单id*/
	@ExcelProperty(value = "单票订单id", index = 0)
    @ApiModelProperty(value = "单票订单id")
    @TableId
    private java.lang.String orderId;
	/**订单渠道*/
	@ExcelProperty(value = "订单渠道", index = 1)
    @ApiModelProperty(value = "订单渠道")
    private java.lang.String orderChannel;
	/**产品名称*/
	@ExcelProperty(value = "产品名称", index = 2)
    @ApiModelProperty(value = "产品名称")
    private java.lang.String productName;
	/**票种名称*/
	@ExcelProperty(value = "票种名称", index = 3)
    @ApiModelProperty(value = "票种名称")
    private java.lang.String ticketName;
	/**联系人*/
	@ExcelProperty(value = "联系人", index = 4)
    @ApiModelProperty(value = "联系人")
    private java.lang.String contactPerson;
	/**联系电话*/
	@ExcelProperty(value = "联系电话", index = 5)
    @ApiModelProperty(value = "联系电话")
    private java.lang.String mobile;
	/**使用日期*/
	@ExcelProperty(value = "使用日期", index = 6)
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "使用日期")
    private java.util.Date useTime;
	/**订单金额*/
	@ExcelProperty(value = "订单金额", index = 7)
    @ApiModelProperty(value = "订单金额")
    private java.math.BigDecimal orderPrice;
	/**购买数量*/
	@ExcelProperty(value = "购买数量", index = 8)
    @ApiModelProperty(value = "购买数量")
    private java.lang.Integer buyNumber;
	/**支付方式*/
	@ExcelProperty(value = "支付方式", index = 9)
    @ApiModelProperty(value = "支付方式")
    private java.lang.String payType;
	/**订单状态*/
	@ExcelProperty(value = "订单状态", index = 10)
    @ApiModelProperty(value = "订单状态")
    private java.lang.String orderStatus;
	/**支付订单*/
	@ExcelProperty(value = "支付订单", index = 11)
    @ApiModelProperty(value = "支付订单")
    private java.lang.String payOrderId;
	/**第三方订单号*/
	@ExcelProperty(value = "第三方订单号", index = 12)
    @ApiModelProperty(value = "第三方订单号")
    private java.lang.String thirdOrderId;
	/**创建人*/
	@ExcelProperty(value = "创建人", index = 13)
    @ApiModelProperty(value = "创建人")
    private java.lang.String createBy;
	/**创建时间*/
	@ExcelProperty(value = "创建时间", index = 14)
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "创建时间")
    private java.util.Date createTime;
	/**更新时间*/
	@ExcelProperty(value = "更新时间", index = 15)
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "更新时间")
    private java.util.Date updateTime;
}
