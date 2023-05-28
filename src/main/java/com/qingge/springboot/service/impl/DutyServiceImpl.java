package com.qingge.springboot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qingge.springboot.entity.Duty;
import com.qingge.springboot.mapper.DutyMapper;
import com.qingge.springboot.service.IDutyService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * \* Created with IntelliJ IDEA.
 * \* @ProjectName: pure-design-master
 * \* @FileName: DutyServiceImpl
 * \* @author: li-jihong
 * \* Date: 2023-05-25 11:12
 */

@Service
public class DutyServiceImpl extends ServiceImpl<DutyMapper, Duty> implements IDutyService {
    @Resource
    private DutyMapper dutyMapper;


    @Override
    public Page<Duty> findPage(Page<Duty> page, Integer dutyid) {
        return dutyMapper.selectPage(page, new QueryWrapper<Duty>().like("dutyid", dutyid.toString()));
    }

    @Transactional
    @Override
    public void deleteDuty(Integer studentId) {
        dutyMapper.deleteDuty(studentId);
    }

    @Transactional
    @Override
    public void insertDuty(Integer studentId) {
        dutyMapper.insertDuty(studentId);
    }


    @Transactional
    @Override
    public void updateDuty(Integer studentId) {
        dutyMapper.updateDuty(studentId);
    }
}