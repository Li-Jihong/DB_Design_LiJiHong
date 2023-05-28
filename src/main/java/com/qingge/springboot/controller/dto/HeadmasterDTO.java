package com.qingge.springboot.controller.dto;

import com.qingge.springboot.entity.Menu;
import lombok.Data;

import java.util.List;

/**
 * 接受前端登录请求的参数
 */
@Data
public class HeadmasterDTO {
    private Integer headmasterid;
    private String username;
    private String password;
    private String token;
    private Integer classid;
    private String role;
    private List<Menu> menus;
}
