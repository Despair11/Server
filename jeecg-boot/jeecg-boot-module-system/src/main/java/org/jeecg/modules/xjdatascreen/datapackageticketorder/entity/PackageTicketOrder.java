package org.jeecg.modules.xjdatascreen.datapackageticketorder.entity;

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
 * @Description: package_ticket_order
 * @Author: jeecg-boot
 * @Date:   2020-11-24
 * @Version: V1.0
 */
@Data
@TableName("package_ticket_order")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="package_ticket_order对象", description="package_ticket_order")
public class PackageTicketOrder implements Serializable {
    private static final long serialVersionUID = 1L;

	/**id*/
	@TableId(type = IdType.ID_WORKER_STR)
    @ApiModelProperty(value = "id")
    private java.lang.Integer id;
	/**套票订单号*/
	@Excel(name = "套票订单号", width = 15)
    @ApiModelProperty(value = "套票订单号")
    private java.lang.String orderNum;
	/**景区编号*/
	@Excel(name = "景区编号", width = 15)
    @ApiModelProperty(value = "景区编号")
    private java.lang.String scenicNum;
	/**订单金额*/
	@Excel(name = "订单金额", width = 15)
    @ApiModelProperty(value = "订单金额")
    private java.lang.String orderAmount;
	/**订单状态*/
	@Excel(name = "订单状态", width = 15)
    @ApiModelProperty(value = "订单状态")
    private java.lang.String status;
	/**订单支付状态*/
	@Excel(name = "订单支付状态", width = 15)
    @ApiModelProperty(value = "订单支付状态")
    private java.lang.String payStatus;
	/**订单创建时间*/
	@Excel(name = "订单创建时间", width = 15)
    @ApiModelProperty(value = "订单创建时间")
    private java.lang.String createTime;
	/**订单来源*/
	@Excel(name = "订单来源", width = 15)
    @ApiModelProperty(value = "订单来源")
    private java.lang.String orderChannel;
	/**支付方式*/
	@Excel(name = "支付方式", width = 15)
    @ApiModelProperty(value = "支付方式")
    private java.lang.String paymentModel;
	/**使用日期*/
	@Excel(name = "使用日期", width = 15)
    @ApiModelProperty(value = "使用日期")
    private java.lang.String useDate;
	/**联系人名称*/
	@Excel(name = "联系人名称", width = 15)
    @ApiModelProperty(value = "联系人名称")
    private java.lang.String contactName;
	/**联系人手机*/
	@Excel(name = "联系人手机", width = 15)
    @ApiModelProperty(value = "联系人手机")
    private java.lang.String contactMobile;
	/**联系人证件类型*/
	@Excel(name = "联系人证件类型", width = 15)
    @ApiModelProperty(value = "联系人证件类型")
    private java.lang.Integer contactCardType;
	/**联系人证件号*/
	@Excel(name = "联系人证件号", width = 15)
    @ApiModelProperty(value = "联系人证件号")
    private java.lang.String contactCardNo;
	/**用户唯一标识*/
	@Excel(name = "用户唯一标识", width = 15)
    @ApiModelProperty(value = "用户唯一标识")
    private java.lang.String owner;
	/**取票号*/
	@Excel(name = "取票号", width = 15)
    @ApiModelProperty(value = "取票号")
    private java.lang.String pickupTicketNo;
	/**支付时间*/
	@Excel(name = "支付时间", width = 15)
    @ApiModelProperty(value = "支付时间")
    private java.lang.String payTime;
	/**支付订单号*/
	@Excel(name = "支付订单号", width = 15)
    @ApiModelProperty(value = "支付订单号")
    private java.lang.String payOrderId;
	/**产品名称*/
	@Excel(name = "产品名称", width = 15)
    @ApiModelProperty(value = "产品名称")
    private java.lang.String productName;
	/**数量*/
	@Excel(name = "数量", width = 15)
    @ApiModelProperty(value = "数量")
    private java.lang.Integer orderCount;
	/**产品编号*/
	@Excel(name = "产品编号", width = 15)
    @ApiModelProperty(value = "产品编号")
    private java.lang.String productCode;
	/**用户是否删除标识 0-未删除 1-已删除*/
	@Excel(name = "用户是否删除标识 0-未删除 1-已删除", width = 15)
    @ApiModelProperty(value = "用户是否删除标识 0-未删除 1-已删除")
    private java.lang.Integer isDelete;
	/**套票单价*/
	@Excel(name = "套票单价", width = 15)
    @ApiModelProperty(value = "套票单价")
    private java.lang.String unitPrice;
	/**订单类型*/
	@Excel(name = "订单类型", width = 15)
    @ApiModelProperty(value = "订单类型")
    private java.lang.Integer orderType;
	/**已核验数量*/
	@Excel(name = "已核验数量", width = 15)
    @ApiModelProperty(value = "已核验数量")
    private java.lang.Integer useCount;
	/**已退数量*/
	@Excel(name = "已退数量", width = 15)
    @ApiModelProperty(value = "已退数量")
    private java.lang.Integer cancelCount;
	/**有效使用结束日期*/
	@Excel(name = "有效使用结束日期", width = 15)
    @ApiModelProperty(value = "有效使用结束日期")
    private java.lang.String endDate;
	/**分时库存开始时间*/
	@Excel(name = "分时库存开始时间", width = 15)
    @ApiModelProperty(value = "分时库存开始时间")
    private java.lang.String startTime;
	/**分时库存结束时间*/
	@Excel(name = "分时库存结束时间", width = 15)
    @ApiModelProperty(value = "分时库存结束时间")
    private java.lang.String endTime;
	/**售后订单状态*/
	@Excel(name = "售后订单状态", width = 15)
    @ApiModelProperty(value = "售后订单状态")
    private java.lang.String afterSaleStatus;
	/**订单完结时间*/
	@Excel(name = "订单完结时间", width = 15)
    @ApiModelProperty(value = "订单完结时间")
    private java.lang.String checkFinishTime;
	/**商家编号（订单中商品是哪个商家的*/
	@Excel(name = "商家编号（订单中商品是哪个商家的", width = 15)
    @ApiModelProperty(value = "商家编号（订单中商品是哪个商家的")
    private java.lang.String merchantCode;
	/**已退款金额*/
	@Excel(name = "已退款金额", width = 15)
    @ApiModelProperty(value = "已退款金额")
    private java.lang.String refundAmount;
	/**客户名称；客户类型为T的时候为分销商名称，C的时候为订单来源*/
	@Excel(name = "客户名称；客户类型为T的时候为分销商名称，C的时候为订单来源", width = 15)
    @ApiModelProperty(value = "客户名称；客户类型为T的时候为分销商名称，C的时候为订单来源")
    private java.lang.String orderCustomerName;
	/**客户类型;T为分销商；C为散客编号为空；*/
	@Excel(name = "客户类型;T为分销商；C为散客编号为空；", width = 15)
    @ApiModelProperty(value = "客户类型;T为分销商；C为散客编号为空；")
    private java.lang.String orderCustomerType;
	/**分销商编号，ota分销 按照ota类型 1-美团 2-飞猪 3-携程。。。作为其编号，其余为分销商编号*/
	@Excel(name = "分销商编号，ota分销 按照ota类型 1-美团 2-飞猪 3-携程。。。作为其编号，其余为分销商编号", width = 15)
    @ApiModelProperty(value = "分销商编号，ota分销 按照ota类型 1-美团 2-飞猪 3-携程。。。作为其编号，其余为分销商编号")
    private java.lang.String orderCustomerNo;
}
