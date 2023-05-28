package com.qingge.springboot.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qingge.springboot.common.Result;
import com.qingge.springboot.entity.Duty;
import com.qingge.springboot.service.IDutyService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * \* Created with IntelliJ IDEA.
 * \* @ProjectName: pure-design-master
 * \* @FileName: DutyController
 * \* @author: li-jihong
 * \* Date: 2023-05-25 10:55
 */
@RestController
@RequestMapping("/duty")
public class DutyController {
    @Resource
    private IDutyService dutyService;

    // 新增或者更新
    @PostMapping
    public Result save(@RequestBody Duty duty) {
        dutyService.saveOrUpdate(duty);
        return Result.success();
    }


    @DeleteMapping("/{dutyid}")
    public Result delete(@PathVariable Integer dutyid) {
        dutyService.removeById(dutyid);
        return Result.success();
    }

    @PostMapping("/del/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        dutyService.removeByIds(ids);
        return Result.success();
    }

    @GetMapping
    public Result findAll() {
        return Result.success(dutyService.list());
    }

    @GetMapping("/{id}")
    public Result findOne(@PathVariable Integer id) {
        return Result.success(dutyService.getById(id));
//        return Result.success(studentService.getstudentid(studentid));
    }

    @GetMapping("/page")
    public Result findPage(@RequestParam String name,
                           @RequestParam(defaultValue = "1") Integer pageNum,
                           @RequestParam(defaultValue = "10") Integer pageSize) {
        QueryWrapper<Duty> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("\"dutyid\"", name);
        queryWrapper.orderByDesc("\"dutyid\"");
        Page<Duty> page = dutyService.page(new Page<>(pageNum, pageSize), queryWrapper);
        List<Duty> records = page.getRecords();

        return Result.success(page);
    }
}