package com.qingge.springboot.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qingge.springboot.common.Result;
import com.qingge.springboot.entity.ClassLeader;
import com.qingge.springboot.entity.Student;
import com.qingge.springboot.service.IClassLeaderService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * \* Created with IntelliJ IDEA.
 * \* @ProjectName: pure-design-master
 * \* @FileName: ClassLeaderController
 * \* @author: li-jihong
 * \* Date: 2023-05-24 23:02
 */
@RestController
@RequestMapping("/classleader")
public class ClassLeaderController {
    @Resource
    private IClassLeaderService classleaderService;

    // 新增或者更新
    @PostMapping
    public Result save(@RequestBody ClassLeader classleader) {
        classleaderService.saveOrUpdate(classleader);
        return Result.success();
    }


    @DeleteMapping("/{studentid}")
    public Result delete(@PathVariable Integer studentid) {
        classleaderService.removeById(studentid);
        return Result.success();
    }

    @PostMapping("/del/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        classleaderService.removeByIds(ids);
        return Result.success();
    }

    @GetMapping
    public Result findAll() {
        return Result.success(classleaderService.list());
    }

    @GetMapping("/{id}")
    public Result findOne(@PathVariable Integer id) {
        return Result.success(classleaderService.getById(id));
//        return Result.success(studentService.getstudentid(studentid));
    }

    @GetMapping("/page")
    public Result findPage(@RequestParam String name,
                           @RequestParam(defaultValue = "1") Integer pageNum,
                           @RequestParam(defaultValue = "10") Integer pageSize) {
        QueryWrapper<ClassLeader> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("\"studentid\"", name);
        queryWrapper.orderByDesc("\"studentid\"");
        Page<ClassLeader> page = classleaderService.page(new Page<>(pageNum, pageSize), queryWrapper);
        List<ClassLeader> records = page.getRecords();

//        for (Course record : records) {
//            User user = userService.getById(record.getTeacherId());
//            if(user != null) {
//                record.setTeacher(user.getNickname());
//            }
//
//        }
        return Result.success(page);
    }
}
