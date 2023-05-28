package com.qingge.springboot.controller;


import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qingge.springboot.common.Constants;
import com.qingge.springboot.common.Result;
import com.qingge.springboot.controller.dto.HeadmasterDTO;
import com.qingge.springboot.controller.dto.HeadmasterPasswordDTO;
import com.qingge.springboot.entity.Headmaster;
import com.qingge.springboot.service.IHeadmasterService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 青哥哥
 * @since 2022-01-26
 */
@RestController
@RequestMapping("/user")
public class HeadmasterController {

    @Value("${files.upload.path}")
    private String filesUploadPath;

    @Resource
    private IHeadmasterService userService;

    @PostMapping("/login")
    public Result login(@RequestBody HeadmasterDTO headmasterDTO) {
        String username = headmasterDTO.getUsername();
        String password = headmasterDTO.getPassword();
        if (StrUtil.isBlank(username) || StrUtil.isBlank(password)) {
            return Result.error(Constants.CODE_400, "参数错误");
        }
        System.out.println("6_666_666_666_666_666_666_666_666");
        HeadmasterDTO dto = userService.login(headmasterDTO);
        return Result.success(dto);
    }

    @PostMapping("/register")
    public Result register(@RequestBody HeadmasterDTO headmasterDTO) {
        String username = headmasterDTO.getUsername();
        String password = headmasterDTO.getPassword();
        if (StrUtil.isBlank(username) || StrUtil.isBlank(password)) {
            return Result.error(Constants.CODE_400, "参数错误");
        }
        return Result.success(userService.register(headmasterDTO));
    }

    // 新增或者更新
    @PostMapping
    public Result save(@RequestBody Headmaster headmaster) {
        if (headmaster.getHeadmasterid() == null && headmaster.getPassword() == null) {  // 新增用户默认密码
            headmaster.setPassword( SecureUtil.md5("123"));
        }
        return Result.success(userService.saveOrUpdate(headmaster));
    }

    /**
     * 修改密码
     * @param headmasterPasswordDTO
     * @return
     */
    @PostMapping("/password")
    public Result password(@RequestBody HeadmasterPasswordDTO headmasterPasswordDTO) {
        headmasterPasswordDTO.setPassword(SecureUtil.md5(headmasterPasswordDTO.getPassword()));
        headmasterPasswordDTO.setNewPassword(SecureUtil.md5(headmasterPasswordDTO.getNewPassword()));
        userService.updatePassword(headmasterPasswordDTO);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        return Result.success(userService.removeById(id));
    }

    @PostMapping("/del/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        return Result.success(userService.removeByIds(ids));
    }

    @GetMapping
    public Result findAll() {
        return Result.success(userService.list());
    }

    @GetMapping("/role/{role}")
    public Result findUsersByRole(@PathVariable String role) {
        QueryWrapper<Headmaster> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("role", role);
        List<Headmaster> list = userService.list(queryWrapper);
        return Result.success(list);
    }

    @GetMapping("/{id}")
    public Result findOne(@PathVariable Integer id) {
        return Result.success(userService.getById(id));
    }

    @GetMapping("/username/{username}")
    public Result findByUsername(@PathVariable String username) {
        QueryWrapper<Headmaster> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("\"username\"", username);
        return Result.success(userService.getOne(queryWrapper));
    }

    @GetMapping("/page")
    public Result findPage(@RequestParam Integer pageNum,
                               @RequestParam Integer pageSize,
                               @RequestParam(defaultValue = "") Integer HEADMASTERID,
                               @RequestParam(defaultValue = "") String USERNAME,
                               @RequestParam(defaultValue = "") String PASSWORD,
                               @RequestParam(defaultValue = "") Integer CLASSID) {

//        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
//        queryWrapper.orderByDesc("id");
//        if (!"".equals(username)) {
//            queryWrapper.like("username", username);
//        }
//        if (!"".equals(email)) {
//            queryWrapper.like("email", email);
//        }
//        if (!"".equals(address)) {
//            queryWrapper.like("address", address);
//        }

        return Result.success(userService.findPage(new Page<>(pageNum, pageSize), HEADMASTERID, USERNAME, PASSWORD, CLASSID ));
    }

    /**
     * 导出接口
     */
    @GetMapping("/export")
    public void export(HttpServletResponse response) throws Exception {
        // 从数据库查询出所有的数据
        List<Headmaster> list = userService.list();
        // 通过工具类创建writer 写出到磁盘路径
//        ExcelWriter writer = ExcelUtil.getWriter(filesUploadPath + "/用户信息.xlsx");
        // 在内存操作，写出到浏览器
        ExcelWriter writer = ExcelUtil.getWriter(true);
        //自定义标题别名
        writer.addHeaderAlias("username", "用户名");
        writer.addHeaderAlias("password", "密码");
        writer.addHeaderAlias("nickname", "昵称");
        writer.addHeaderAlias("email", "邮箱");
        writer.addHeaderAlias("phone", "电话");
        writer.addHeaderAlias("address", "地址");
        writer.addHeaderAlias("createTime", "创建时间");
        writer.addHeaderAlias("avatarUrl", "头像");

        // 一次性写出list内的对象到excel，使用默认样式，强制输出标题
        writer.write(list, true);

        // 设置浏览器响应的格式
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        String fileName = URLEncoder.encode("用户信息", "UTF-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName + ".xlsx");

        ServletOutputStream out = response.getOutputStream();
        writer.flush(out, true);
        out.close();
        writer.close();

    }

    /**
     * excel 导入
     * @param file
     * @throws Exception
     */
    @PostMapping("/import")
    public Result imp(MultipartFile file) throws Exception {
        InputStream inputStream = file.getInputStream();
        ExcelReader reader = ExcelUtil.getReader(inputStream);
        // 方式1：(推荐) 通过 javabean的方式读取Excel内的对象，但是要求表头必须是英文，跟javabean的属性要对应起来
//        List<User> list = reader.readAll(User.class);

        // 方式2：忽略表头的中文，直接读取表的内容
        List<List<Object>> list = reader.read(1);
        List<Headmaster> headmasters = CollUtil.newArrayList();
        for (List<Object> row : list) {
            Headmaster headmaster = new Headmaster();
            headmaster.setHeadmasterid(Integer.valueOf(row.get(0).toString()));
            headmaster.setPassword(row.get(1).toString());
            headmaster.setUsername(row.get(2).toString());
            headmaster.setClassid(Integer.valueOf(row.get(3).toString()));
            headmasters.add(headmaster);
        }

        userService.saveBatch(headmasters);
        return Result.success(true);
    }

}

