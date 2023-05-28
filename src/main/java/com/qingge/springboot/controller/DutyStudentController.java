package com.qingge.springboot.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qingge.springboot.common.Result;
import com.qingge.springboot.entity.DutyStudent;
import com.qingge.springboot.service.IDutyStudentService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/dutystudent")
public class DutyStudentController {
    @Resource
    private IDutyStudentService dutyStudentService;

    @PostMapping
    public Result save(@RequestBody DutyStudent dutyStudent) {
        // 检查是否已存在相同的值日号和学号组合
        QueryWrapper<DutyStudent> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("\"dutyid\"", dutyStudent.getDutyid());
        queryWrapper.eq("\"studentid\"", dutyStudent.getStudentid());
        DutyStudent existingStudent = dutyStudentService.getOne(queryWrapper);

        if (existingStudent != null) {
            // 已存在相同的值日号和学号组合，不执行保存操作
            return Result.success("值日号和学号已存在，保存失败");
        }

        dutyStudentService.save(dutyStudent);
        return Result.success("保存成功");
    }



    @DeleteMapping("/{dutyid}/{studentid}")
    public Result delete(@PathVariable String dutyid, @PathVariable String studentid) {
        try {
            // 将 dutyid 和 studentid 转换为整数
            int dutyId = Integer.parseInt(dutyid);
            int studentId = Integer.parseInt(studentid);

            // 执行删除操作
            dutyStudentService.removeByDutyIdAndStudentId(dutyId, studentId);

            return Result.success();
        } catch (NumberFormatException e) {
            // 处理类型转换错误
            return Result.error();
        }
    }





    @PostMapping("/del/batch")
    public Result deleteBatch(@RequestBody List<DutyStudent> dutyStudents) {
        // 遍历dutyStudents列表，逐个删除
        for (DutyStudent ds : dutyStudents) {
            Integer dutyid = ds.getDutyid();
            Integer studentid = ds.getStudentid();

            if (dutyid != null && studentid != null) {
                dutyStudentService.removeByDutyIdAndStudentId(dutyid, studentid);
            }
        }


        return Result.success();
    }


    @GetMapping
    public Result findAll() {
        return Result.success(dutyStudentService.list());
    }

    @GetMapping("/{dutyid}")
    public Result findOne(@PathVariable Integer dutyid) {
        return Result.success(dutyStudentService.getById(dutyid));
    }

//    @GetMapping("/page")
//    public Result findPage(@RequestParam String name,
//                           @RequestParam(defaultValue = "1") Integer pageNum,
//                           @RequestParam(defaultValue = "10") Integer pageSize) {
//        QueryWrapper<DutyStudent> queryWrapper = new QueryWrapper<>();
//        queryWrapper.like("\"dutyid\"", name);
//        queryWrapper.orderByDesc("\"dutyid\"");
//        Page<DutyStudent> page = dutyStudentService.page(new Page<>(pageNum, pageSize), queryWrapper);
//        List<DutyStudent> records = page.getRecords();
//        return Result.success(page);
//    }

    @GetMapping("/page")
    public Result findPage(@RequestParam(required = false) String name,
                           @RequestParam(defaultValue = "1") Integer pageNum,
                           @RequestParam(defaultValue = "10") Integer pageSize) {
        QueryWrapper<DutyStudent> queryWrapper = new QueryWrapper<>();
//        if (name != null && !name.isEmpty()) {
//            queryWrapper.like("\"dutyid\"", name);
//        }
//        queryWrapper.orderByDesc("\"dutyid\"");
        queryWrapper.like("\"dutyid\"", name);
        queryWrapper.orderByDesc("\"dutyid\"");

        Page<DutyStudent> page = dutyStudentService.page(new Page<>(pageNum, pageSize), queryWrapper);
        List<DutyStudent> records = page.getRecords();

//        // 根据值日号进行分组
//        Map<Integer, List<DutyStudent>> groupedStudents = records.stream()
//                .collect(Collectors.groupingBy(DutyStudent::getDutyid));
//
//        return Result.success(groupedStudents);
        return  Result.success(records);
    }


}
