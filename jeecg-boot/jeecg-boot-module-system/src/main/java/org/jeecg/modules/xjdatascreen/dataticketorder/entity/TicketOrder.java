package org.jeecg.modules.xjdatascreen.dataticketorder.entity;

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
 * @Description: ticket_order
 * @Author: jeecg-boot
 * @Date:   2020-11-24
 * @Version: V1.0
 */
@Data
@TableName("ticket_order")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="ticket_order对象", description="ticket_order")
public class TicketOrder implements Serializable {
    private static final long serialVersionUID = 1L;

	/**id*/
	@TableId(type = IdType.ID_WORKER_STR)
    @ApiModelProperty(value = "id")
    private java.lang.Integer id;
	/**景区编号*/
	@Excel(name = "景区编号", width = 15)
    @ApiModelProperty(value = "景区编号")
    private java.lang.String scenicNum;
	/**订单金额*/
	@Excel(name = "订单金额", width = 15)
    @ApiModelProperty(value = "订单金额")
    private java.lang.String orderAmount;
	/**订单编号*/
	@Excel(name = "订单编号", width = 15)
    @ApiModelProperty(value = "订单编号")
    private java.lang.String orderNum;
	/**订单状态*/
	@Excel(name = "订单状态", width = 15)
    @ApiModelProperty(value = "订单状态")
    private java.lang.String status;
	/**支付状态*/
	@Excel(name = "支付状态", width = 15)
    @ApiModelProperty(value = "支付状态")
    private java.lang.String payStatus;
	/**下单时间*/
	@Excel(name = "下单时间", width = 15)
    @ApiModelProperty(value = "下单时间")
    private java.lang.String createTime;
	/**销售渠道*/
	@Excel(name = "销售渠道", width = 15)
    @ApiModelProperty(value = "销售渠道")
    private java.lang.String orderChannel;
	/**支付方式*/
	@Excel(name = "支付方式", width = 15)
    @ApiModelProperty(value = "支付方式")
    private java.lang.String paymentMode;
	/**使用时间*/
	@Excel(name = "使用时间", width = 15)
    @ApiModelProperty(value = "使用时间")
    private java.lang.String useTime;
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
	/**联系人名称*/
	@Excel(name = "联系人名称", width = 15)
    @ApiModelProperty(value = "联系人名称")
    private java.lang.String contactName;
	/**联系人电话*/
	@Excel(name = "联系人电话", width = 15)
    @ApiModelProperty(value = "联系人电话")
    private java.lang.String contactMobile;
	/**联系人证件类型*/
	@Excel(name = "联系人证件类型", width = 15)
    @ApiModelProperty(value = "联系人证件类型")
    private java.lang.Integer contactCardType;
	/**联系人证件号*/
	@Excel(name = "联系人证件号", width = 15)
    @ApiModelProperty(value = "联系人证件号")
    private java.lang.String contactCardNo;
	/**取票号*/
	@Excel(name = "取票号", width = 15)
    @ApiModelProperty(value = "取票号")
    private java.lang.String pickupTicketNo;
	/**用户唯一标识*/
	@Excel(name = "用户唯一标识", width = 15)
    @ApiModelProperty(value = "用户唯一标识")
    private java.lang.String owner;
	/**支付时间*/
	@Excel(name = "支付时间", width = 15)
    @ApiModelProperty(value = "支付时间")
    private java.lang.String payTime;
	/**订单类型*/
	@Excel(name = "订单类型", width = 15)
    @ApiModelProperty(value = "订单类型")
    private java.lang.Integer orderType;
	/**startDate*/
	@Excel(name = "startDate", width = 15)
    @ApiModelProperty(value = "startDate")
    private java.lang.String startDate;
	/**endDate*/
	@Excel(name = "endDate", width = 15)
    @ApiModelProperty(value = "endDate")
    private java.lang.String endDate;
	/**酒店编号*/
	@Excel(name = "酒店编号", width = 15)
    @ApiModelProperty(value = "酒店编号")
    private java.lang.String hotelCode;
	/**支付单号*/
	@Excel(name = "支付单号", width = 15)
    @ApiModelProperty(value = "支付单号")
    private java.lang.String payOrderId;
	/**产品名称*/
	@Excel(name = "产品名称", width = 15)
    @ApiModelProperty(value = "产品名称")
    private java.lang.String productName;
	/**子产品名称*/
	@Excel(name = "子产品名称", width = 15)
    @ApiModelProperty(value = "子产品名称")
    private java.lang.String subProductName;
	/**订单数量*/
	@Excel(name = "订单数量", width = 15)
    @ApiModelProperty(value = "订单数量")
    private java.lang.Integer orderCount;
	/**售后状态*/
	@Excel(name = "售后状态", width = 15)
    @ApiModelProperty(value = "售后状态")
    private java.lang.String afterSaleStatus;
	/**套票产品id*/
	@Excel(name = "套票产品id", width = 15)
    @ApiModelProperty(value = "套票产品id")
    private java.lang.Integer packageTicketId;
	/**是否删除，0-未删除 1-已删除*/
	@Excel(name = "是否删除，0-未删除 1-已删除", width = 15)
    @ApiModelProperty(value = "是否删除，0-未删除 1-已删除")
    private java.lang.Integer isDelete;
	/**取票号生成的二维码地址*/
	@Excel(name = "取票号生成的二维码地址", width = 15)
    @ApiModelProperty(value = "取票号生成的二维码地址")
    private java.lang.String codeUrl;
	/**对应套票的父订单号*/
	@Excel(name = "对应套票的父订单号", width = 15)
    @ApiModelProperty(value = "对应套票的父订单号")
    private java.lang.String pid;
	/**检票方式，1-一码多刷 2-一码一刷*/
	@Excel(name = "检票方式，1-一码多刷 2-一码一刷", width = 15)
    @ApiModelProperty(value = "检票方式，1-一码多刷 2-一码一刷")
    private java.lang.Integer checkStyle;
	/**核验方式：1-一次核验 2-二次核验*/
	@Excel(name = "核验方式：1-一次核验 2-二次核验", width = 15)
    @ApiModelProperty(value = "核验方式：1-一次核验 2-二次核验")
    private java.lang.Integer verificationMethod;
	/**票务系统类型：0-自己平台 1-九天达*/
	@Excel(name = "票务系统类型：0-自己平台 1-九天达", width = 15)
    @ApiModelProperty(value = "票务系统类型：0-自己平台 1-九天达")
    private java.lang.Integer ticketSystemType;
	/**对应第三方系统订单编号*/
	@Excel(name = "对应第三方系统订单编号", width = 15)
    @ApiModelProperty(value = "对应第三方系统订单编号")
    private java.lang.String thirdOrderNum;
	/**检票完结时间*/
	@Excel(name = "检票完结时间", width = 15)
    @ApiModelProperty(value = "检票完结时间")
    private java.lang.String checkFinishTime;
	/**商家编号（订单中商品是哪个商家的*/
	@Excel(name = "商家编号（订单中商品是哪个商家的", width = 15)
    @ApiModelProperty(value = "商家编号（订单中商品是哪个商家的")
    private java.lang.String merchantCode;
	/**OTA类型 1-美团*/
	@Excel(name = "OTA类型 1-美团", width = 15)
    @ApiModelProperty(value = "OTA类型 1-美团")
    private java.lang.Integer otaType;
	/**OTA订单编号*/
	@Excel(name = "OTA订单编号", width = 15)
    @ApiModelProperty(value = "OTA订单编号")
    private java.lang.String otaOrderNum;
	/**对应ota的合作方id*/
	@Excel(name = "对应ota的合作方id", width = 15)
    @ApiModelProperty(value = "对应ota的合作方id")
    private java.lang.String otaPartnerId;
	/**ota传过来的订单金额，用户付给ota的钱*/
	@Excel(name = "ota传过来的订单金额，用户付给ota的钱", width = 15)
    @ApiModelProperty(value = "ota传过来的订单金额，用户付给ota的钱")
    private java.lang.String otaOrderAmount;
}
