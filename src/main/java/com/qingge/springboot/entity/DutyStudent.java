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
 * \* @FileName: DutyStudent
 * \* @author: li-jihong
 * \* Date: 2023-05-26 20:36
 */

@Data
@TableName("\"DB_DESIGN\".\"DUTY_STUDENT\"")
@ApiModel(value = "DutyStudent", description = "")
public class DutyStudent implements Serializable {

    private static final long serialVersionUID = 1L;


    @TableId(value = "\"dutyid\"")
    @ApiModelProperty("dutyid")
    private Integer dutyid;

    @TableField("\"studentid\"")
    @ApiModelProperty("studentid")
    private Integer studentid;

}
