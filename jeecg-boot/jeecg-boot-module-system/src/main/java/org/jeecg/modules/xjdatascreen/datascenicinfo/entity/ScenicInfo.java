package org.jeecg.modules.xjdatascreen.datascenicinfo.entity;

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
 * @Description: scenic_info
 * @Author: jeecg-boot
 * @Date:   2020-11-24
 * @Version: V1.0
 */
@Data
@TableName("scenic_info")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="scenic_info对象", description="scenic_info")
public class ScenicInfo implements Serializable {
    private static final long serialVersionUID = 1L;

	/**id*/
	@TableId(type = IdType.ID_WORKER_STR)
    @ApiModelProperty(value = "id")
    private java.lang.Integer id;
	/**scenicCode*/
	@Excel(name = "scenicCode", width = 15)
    @ApiModelProperty(value = "scenicCode")
    private java.lang.String scenicCode;
	/**scenicName*/
	@Excel(name = "scenicName", width = 15)
    @ApiModelProperty(value = "scenicName")
    private java.lang.String scenicName;
	/**scenicCharacteristics*/
	@Excel(name = "scenicCharacteristics", width = 15)
    @ApiModelProperty(value = "scenicCharacteristics")
    private java.lang.String scenicCharacteristics;
	/**addressLongitude*/
	@Excel(name = "addressLongitude", width = 15)
    @ApiModelProperty(value = "addressLongitude")
    private java.lang.String addressLongitude;
	/**addressLatitude*/
	@Excel(name = "addressLatitude", width = 15)
    @ApiModelProperty(value = "addressLatitude")
    private java.lang.String addressLatitude;
	/**telephone*/
	@Excel(name = "telephone", width = 15)
    @ApiModelProperty(value = "telephone")
    private java.lang.String telephone;
	/**coverVideoUrl*/
	@Excel(name = "coverVideoUrl", width = 15)
    @ApiModelProperty(value = "coverVideoUrl")
    private java.lang.String coverVideoUrl;
	/**coverImageUrl*/
	@Excel(name = "coverImageUrl", width = 15)
    @ApiModelProperty(value = "coverImageUrl")
    private java.lang.String coverImageUrl;
	/**summarize*/
	@Excel(name = "summarize", width = 15)
    @ApiModelProperty(value = "summarize")
    private java.lang.String summarize;
	/**trafficGuide*/
	@Excel(name = "trafficGuide", width = 15)
    @ApiModelProperty(value = "trafficGuide")
    private java.lang.String trafficGuide;
	/**ambitusStay*/
	@Excel(name = "ambitusStay", width = 15)
    @ApiModelProperty(value = "ambitusStay")
    private java.lang.String ambitusStay;
	/**featuredItem*/
	@Excel(name = "featuredItem", width = 15)
    @ApiModelProperty(value = "featuredItem")
    private java.lang.String featuredItem;
	/**recommend*/
	@Excel(name = "recommend", width = 15)
    @ApiModelProperty(value = "recommend")
    private java.lang.String recommend;
	/**status*/
	@Excel(name = "status", width = 15)
    @ApiModelProperty(value = "status")
    private java.lang.String status;
	/**sortNo*/
	@Excel(name = "sortNo", width = 15)
    @ApiModelProperty(value = "sortNo")
    private java.lang.Integer sortNo;
	/**createTime*/
	@Excel(name = "createTime", width = 15)
    @ApiModelProperty(value = "createTime")
    private java.lang.String createTime;
	/**supplierCode*/
	@Excel(name = "supplierCode", width = 15)
    @ApiModelProperty(value = "supplierCode")
    private java.lang.String supplierCode;
	/**supplierName*/
	@Excel(name = "supplierName", width = 15)
    @ApiModelProperty(value = "supplierName")
    private java.lang.String supplierName;
	/**state*/
	@Excel(name = "state", width = 15)
    @ApiModelProperty(value = "state")
    private java.lang.String state;
	/**verifyTime*/
	@Excel(name = "verifyTime", width = 15)
    @ApiModelProperty(value = "verifyTime")
    private java.lang.String verifyTime;
	/**verifyRefuseReason*/
	@Excel(name = "verifyRefuseReason", width = 15)
    @ApiModelProperty(value = "verifyRefuseReason")
    private java.lang.String verifyRefuseReason;
	/**address*/
	@Excel(name = "address", width = 15)
    @ApiModelProperty(value = "address")
    private java.lang.String address;
}
