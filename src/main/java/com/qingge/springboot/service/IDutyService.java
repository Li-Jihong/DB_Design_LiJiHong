package com.qingge.springboot.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.qingge.springboot.entity.Duty;
import org.springframework.transaction.annotation.Transactional;

public interface IDutyService extends IService<Duty> {
    Page<Duty> findPage(Page<Duty> page, Integer dutyid);

    @Transactional
    void deleteDuty(Integer studentId);

    @Transactional
    void insertDuty(Integer studentId);

    @Transactional
    void updateDuty(Integer studentId);
}
