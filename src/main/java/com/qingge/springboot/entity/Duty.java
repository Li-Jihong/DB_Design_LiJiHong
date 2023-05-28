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
 * \* @FileName: Duty
 * \* @author: li-jihong
 * \* Date: 2023-05-25 10:52
 */
@Data
@TableName("\"DB_DESIGN\".\"DUTY\"")
@ApiModel(value = "Duty", description = "")
public class Duty implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("dutyid")
    @TableId(value = "\"dutyid\"")
    private Integer dutyid;

    @TableField("\"classid\"")
    @ApiModelProperty("classid")
    private String classid;

    @TableField("\"date\"")
    @ApiModelProperty("date")
    private String date;

    @TableField("\"description\"")
    @ApiModelProperty("description")
    private String description;

}
