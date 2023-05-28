package com.qingge.springboot.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.qingge.springboot.entity.DutyStudent;
import org.springframework.transaction.annotation.Transactional;

public interface IDutyStudentService extends IService<DutyStudent> {
    Page<DutyStudent> findPage(Page<DutyStudent> page, Integer dutyid);

    @Transactional
    void deleteDutyStudent(Integer dutyid);

    @Transactional
    void insertDutyStudent(Integer dutyid);

    @Transactional
    void updateDutyStudent(Integer dutyid);

    void removeByDutyIdAndStudentId(Integer dutyid, Integer studentid);
}
