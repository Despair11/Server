package org.jeecg.modules.xjdatascreen.datatouristmember.entity;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @Description: data_tourist_member
 * @Author: jeecg-boot
 * @Date:   2020-11-19
 * @Version: V1.0
 */
@Data
@TableName("data_tourist_member")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="data_tourist_member对象", description="data_tourist_member")
public class DataTouristMember implements Serializable {
    private static final long serialVersionUID = 1L;

	/**id*/
	@TableId(type = IdType.AUTO)
    @Excel(name = "id", width = 15)
    @ApiModelProperty(value = "id")
    private java.lang.Integer id;
	/**注册时间*/
	@Excel(name = "注册时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "注册时间")
    private java.util.Date registeredTime;
	/**最后上线时间*/
	@Excel(name = "最后上线时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "最后上线时间")
    private java.util.Date lastOnlineTime;
	/**昵称*/
	@Excel(name = "昵称", width = 15)
    @ApiModelProperty(value = "昵称")
    private java.lang.String nickName;
	/**身份证号码*/
	@Excel(name = "身份证号", width = 15)
    @ApiModelProperty(value = "身份证号")
    private java.lang.String cardId;
	/**性别*/
	@Excel(name = "性别", width = 15)
    @ApiModelProperty(value = "性别")
    private java.lang.String sex;
	/**微信唯一标识*/
	@Excel(name = "微信唯一标识", width = 15)
    @ApiModelProperty(value = "微信唯一标识")
    private java.lang.String uniqueId;
	/**注册来源 W-微信 Z-支付宝 H-H5*/
	@Excel(name = "注册来源", width = 15)
    @ApiModelProperty(value = "注册来源 W-微信 Z-支付宝 H-H5")
    private java.lang.String registeredSource;
	/**手机号*/
	@Excel(name = "手机号", width = 15)
    @ApiModelProperty(value = "手机号")
    private java.lang.String phoneNumber;
	/**供应商code*/
	@Excel(name = "供应商code", width = 15)
    @ApiModelProperty(value = "供应商code")
    private java.lang.String supplierCode;
	/**会员级别*/
	@Excel(name = "会员级别", width = 15)
    @ApiModelProperty(value = "会员级别")
    private java.lang.String memberLevel;
	/**状态*/
	@Excel(name = "状态", width = 15)
    @ApiModelProperty(value = "状态")
    private java.lang.Integer status;
	/**微信图像*/
	@Excel(name = "微信图像", width = 15)
    @ApiModelProperty(value = "微信图像")
    private java.lang.String avatar;
	/**创建人*/
	@Excel(name = "创建人", width = 15)
    @ApiModelProperty(value = "创建人")
    private java.lang.String createBy;
	/**创建时间*/
	@Excel(name = "创建时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "创建时间")
    private java.util.Date createTime;
	/**更新时间*/
	@Excel(name = "更新时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "更新时间")
    private java.util.Date updateTime;
}
