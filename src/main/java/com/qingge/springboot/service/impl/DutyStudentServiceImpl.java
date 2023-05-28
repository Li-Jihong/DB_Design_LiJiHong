package com.qingge.springboot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qingge.springboot.entity.DutyStudent;
import com.qingge.springboot.mapper.DutyStudentMapper;
import com.qingge.springboot.service.IDutyStudentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * \* Created with IntelliJ IDEA.
 * \* @ProjectName: pure-design-master
 * \* @FileName: DutyStudentServiceImpl
 * \* @author: li-jihong
 * \* Date: 2023-05-26 20:43
 */

@Service
public class DutyStudentServiceImpl extends ServiceImpl<DutyStudentMapper, DutyStudent> implements IDutyStudentService {
    @Resource
    private DutyStudentMapper dutyStudentMapper;


    @Override
    public Page<DutyStudent> findPage(Page<DutyStudent> page, Integer dutyid) {
        return dutyStudentMapper.selectPage(page, new QueryWrapper<DutyStudent>().like("dutyid", dutyid.toString()));
    }

    @Transactional
    @Override
    public void deleteDutyStudent(Integer dutyid) {
        dutyStudentMapper.deleteDutyStudent(dutyid);
    }

    @Transactional
    @Override
    public void insertDutyStudent(Integer dutyid) {
        dutyStudentMapper.insertDutyStudent(dutyid);
    }


    @Transactional
    @Override
    public void updateDutyStudent(Integer dutyid) {
        dutyStudentMapper.updateDutyStudent(dutyid);
    }

    @Override
    @Transactional
    public void removeByDutyIdAndStudentId(Integer dutyid, Integer studentid) {
        dutyStudentMapper.delete(new QueryWrapper<DutyStudent>()
                .eq("\"dutyid\"", dutyid)
                .eq("\"studentid\"", studentid));
    }

}
