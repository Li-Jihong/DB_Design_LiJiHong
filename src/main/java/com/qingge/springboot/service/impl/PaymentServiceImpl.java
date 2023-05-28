package com.qingge.springboot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qingge.springboot.entity.Payment;
import com.qingge.springboot.mapper.PaymentMapper;
import com.qingge.springboot.service.IPaymentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * \* Created with IntelliJ IDEA.
 * \* @ProjectName: pure-design-master
 * \* @FileName: PaymentServiceImpl
 * \* @author: li-jihong
 * \* Date: 2023-05-25 9:33
 */
@Service
public class PaymentServiceImpl extends ServiceImpl<PaymentMapper, Payment> implements IPaymentService {
    @Resource
    private PaymentMapper paymentMapper;


    @Override
    public Page<Payment> findPage(Page<Payment> page, Integer studentid) {
        return paymentMapper.selectPage(page, new QueryWrapper<Payment>().like("studentid", studentid.toString()));
    }

    @Transactional
    @Override
    public void deletePayment(Integer studentid) {
        paymentMapper.deletePayment(studentid);
    }

    @Transactional
    @Override
    public void insertPayment(Integer studentid) {
        paymentMapper.insertPayment(studentid);
    }


    @Transactional
    @Override
    public void updatePayment(Integer studentid) {
        paymentMapper.updatePayment(studentid);
    }
}
