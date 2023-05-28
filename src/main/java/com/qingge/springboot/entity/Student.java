package com.qingge.springboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author 青哥哥
 * @since 2022-03-03
 */
@Data
@TableName("\"DB_DESIGN\".\"STUDENTS\"")
@ApiModel(value = "Student", description = "")
public class Student implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("studentid")
    @TableId(value = "\"studentid\"")
    private Integer studentid;

    @TableField("\"name\"")
    @ApiModelProperty("姓名")
    private String name;

    @TableField("\"age\"")
    @ApiModelProperty("年龄")
    private Integer age;

    @TableField("\"gender\"")
    @ApiModelProperty("性别")
    private String gender;

    @TableField("\"ethnicity\"")
    @ApiModelProperty("民族")
    private String ethnicity;

    @TableField("\"politicalstatus\"")
    @ApiModelProperty("政治状态")
    private String politicalstatus;

    @TableField("\"position\"")
    @ApiModelProperty("职位")
    private String position;

    @TableField("\"classid\"")
    @ApiModelProperty("班级号")
    private String classid;

    @TableField("\"dormitoryid\"")
    @ApiModelProperty("寝室号")
    private String dormitoryid;

//    @TableField(exist = false)
//    private String teacher;


}
