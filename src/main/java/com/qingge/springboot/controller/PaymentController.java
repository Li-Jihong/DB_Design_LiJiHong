package com.qingge.springboot.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qingge.springboot.common.Result;
import com.qingge.springboot.entity.Payment;
import com.qingge.springboot.service.IPaymentService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * \* Created with IntelliJ IDEA.
 * \* @ProjectName: pure-design-master
 * \* @FileName: PaymentController
 * \* @author: li-jihong
 * \* Date: 2023-05-25 9:08
 */
@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Resource
    private IPaymentService paymentService;

    // 新增或者更新
    @PostMapping
    public Result save(@RequestBody Payment payment) {
        paymentService.saveOrUpdate(payment);
        return Result.success();
    }


    @DeleteMapping("/{studentid}")
    public Result delete(@PathVariable Integer studentid) {
        paymentService.removeById(studentid);
        return Result.success();
    }

    @PostMapping("/del/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        paymentService.removeByIds(ids);
        return Result.success();
    }

    @GetMapping
    public Result findAll() {
        return Result.success(paymentService.list());
    }

    @GetMapping("/{id}")
    public Result findOne(@PathVariable Integer id) {
        return Result.success(paymentService.getById(id));
//        return Result.success(studentService.getstudentid(studentid));
    }

    @GetMapping("/page")
    public Result findPage(@RequestParam String name,
                           @RequestParam(defaultValue = "1") Integer pageNum,
                           @RequestParam(defaultValue = "10") Integer pageSize) {
        QueryWrapper<Payment> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("\"studentid\"", name);
        queryWrapper.orderByDesc("\"studentid\"");
        Page<Payment> page = paymentService.page(new Page<>(pageNum, pageSize), queryWrapper);
        List<Payment> records = page.getRecords();

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
