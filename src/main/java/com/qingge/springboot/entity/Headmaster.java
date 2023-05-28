package com.qingge.springboot.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author 青哥哥
 * @since 2022-01-26
 */
@Getter
@Setter
@TableName("\"DB_DESIGN\".\"HEADMASTER\"")
@ApiModel(value = "HEADMASTER对象", description = "")
@ToString
public class Headmaster implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("\"headmasterid\"")
    @ApiModelProperty("HEADMASTERID")
    private Integer headmasterid;

    @TableField("\"username\"")
    @ApiModelProperty("用户名")
    private String username;

    @TableField("\"password\"")
    @ApiModelProperty("密码")
    private String password;

    @TableField("\"classid\"")
    @ApiModelProperty("班级号")
    private Integer classid;

    @TableField("\"role\"")
    @ApiModelProperty("Role")
    private String role;

}
