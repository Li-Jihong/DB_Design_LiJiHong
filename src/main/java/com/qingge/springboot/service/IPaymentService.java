package com.qingge.springboot.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.qingge.springboot.entity.Payment;
import org.springframework.transaction.annotation.Transactional;

public interface IPaymentService extends IService<Payment> {
    Page<Payment> findPage(Page<Payment> page, Integer studentid);

    @Transactional
    void deletePayment(Integer studentid);

    @Transactional
    void insertPayment(Integer studentid);

    @Transactional
    void updatePayment(Integer studentid);
}
