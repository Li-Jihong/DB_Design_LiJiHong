package com.qingge.springboot.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.qingge.springboot.entity.ClassLeader;
import org.springframework.transaction.annotation.Transactional;

public interface IClassLeaderService extends IService<ClassLeader> {
    Page<ClassLeader> findPage(Page<ClassLeader> page, Integer studentid);

    void deleteClassLeader(Integer studentId);
    void insertClassLeader(Integer studentId);

    @Transactional
    void updateClassLeader(Integer studentId);
}
