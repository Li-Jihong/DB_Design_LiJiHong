package com.qingge.springboot.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("\"DB_DESIGN\".\"SYS_ROLE_MENU\"")
@Data
public class RoleMenu {

    private Integer roleId;
    private Integer menuId;

}
