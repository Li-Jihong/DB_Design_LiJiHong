package com.qingge.springboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 *
 * </p>
 *
 * @author 青哥哥
 * @since 2022-02-10
 */
@Getter
@Setter
@TableName("\"DB_DESIGN\".\"SYS_MENU\"")
@ApiModel(value = "Menu对象", description = "")
public class Menu implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("id")
    @TableId(value = "\"id\"", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("名称")
    @TableField(value = "\"name\"")
    private String name;

    @ApiModelProperty("路径")
    @TableField(value = "\"path\"")
    private String path;

    @ApiModelProperty("图标")
    @TableField(value = "\"icon\"")
    private String icon;

    @ApiModelProperty("描述")
    @TableField(value = "\"desciption\"")
    private String desciption;

    @TableField(exist = false)
    private List<Menu> children;

    @TableField(value = "\"pid\"")
    private Integer pid;

    @TableField(value = "\"page_path\"")
    private String page_path;

    @TableField(value = "\"sort_num\"")
    private String sort_num;


}
