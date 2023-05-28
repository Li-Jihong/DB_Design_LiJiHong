package com.qingge.springboot.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qingge.springboot.common.Result;
import com.qingge.springboot.entity.Student;
import com.qingge.springboot.service.IStudentsService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 青哥哥
 * @since 2022-03-03
 */
@RestController
@RequestMapping("/students")
public class StudentsController {

    @Resource
    private IStudentsService studentService;

    // 新增或者更新
    @PostMapping
    public Result save(@RequestBody Student student) {
        studentService.saveOrUpdate(student);
        return Result.success();
    }


    @DeleteMapping("/{studentid}")
    public Result delete(@PathVariable Integer studentid) {
        studentService.removeById(studentid);
        return Result.success();
    }

    @PostMapping("/del/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        studentService.removeByIds(ids);
        return Result.success();
    }

    @GetMapping
    public Result findAll() {
        return Result.success(studentService.list());
    }

    @GetMapping("/{id}")
    public Result findOne(@PathVariable Integer id) {
        return Result.success(studentService.getById(id));
//        return Result.success(studentService.getstudentid(studentid));
    }

    @GetMapping("/page")
    public Result findPage(@RequestParam String name,
                           @RequestParam(defaultValue = "1") Integer pageNum,
                           @RequestParam(defaultValue = "10") Integer pageSize) {
        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("\"studentid\"", name);
//        queryWrapper.orderByDesc("\"studentid\"");
        Page<Student> page = studentService.page(new Page<>(pageNum, pageSize), queryWrapper);
        List<Student> records = page.getRecords();

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

