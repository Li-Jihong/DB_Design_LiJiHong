package com.qingge.springboot.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * \* Created with IntelliJ IDEA.
 * \* @ProjectName: pure-design-master
 * \* @FileName: Payment
 * \* @author: li-jihong
 * \* Date: 2023-05-25 8:58
 */
@Data
@TableName("\"DB_DESIGN\".\"PAYMENT\"")
@ApiModel(value = "Payment", description = "")
public class Payment implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("paymentid")
    @TableId(value = "\"paymentid\"")
    private Integer paymentid;

    @TableField("\"classid\"")
    @ApiModelProperty("classid")
    private String classid;

    @TableField("\"studentid\"")
    @ApiModelProperty("studentid")
    private String studentid;

    @TableField("\"paymentamount\"")
    @ApiModelProperty("paymentamount")
    private String paymentamount;

    @TableField("\"paymentdate\"")
    @ApiModelProperty("paymentdate")
    private String paymentdate;

}