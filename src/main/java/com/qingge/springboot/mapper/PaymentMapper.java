package com.qingge.springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qingge.springboot.entity.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PaymentMapper extends BaseMapper<Payment> {

    Page<Payment> findPage(Page<Payment> page, @Param("studentid") Integer studentid);
    void deletePayment(@Param("studentid") Integer studentid);

    void insertPayment(@Param("studentid") Integer studentid);
    void updatePayment(@Param("studentid") Integer studentid);
}