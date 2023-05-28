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
 * \* @FileName: ClassLeader
 * \* @author: li-jihong
 * \* Date: 2023-05-24 22:55
 */
@Data
@TableName("\"DB_DESIGN\".\"CLASSLEADER\"")
@ApiModel(value = "ClassLeader", description = "")
public class ClassLeader implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("leaderid")
    @TableId(value = "\"leaderid\"")
    private Integer leaderid;

    @TableField("\"name\"")
    @ApiModelProperty("姓名")
    private String name;

    @TableField("\"position\"")
    @ApiModelProperty("职位")
    private String position;

    @TableField("\"studentid\"")
    @ApiModelProperty("寝室号")
    private String studentid;

    @TableField("\"classid\"")
    @ApiModelProperty("班级号")
    private String classid;

}
