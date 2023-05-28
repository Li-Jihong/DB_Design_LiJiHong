package com.qingge.springboot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qingge.springboot.entity.ClassLeader;
import com.qingge.springboot.mapper.ClassLeaderMapper;
import com.qingge.springboot.service.IClassLeaderService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * \* Created with IntelliJ IDEA.
 * \* @ProjectName: pure-design-master
 * \* @FileName: ClassLeaderServiceImpl
 * \* @author: li-jihong
 * \* Date: 2023-05-25 7:50
 */
@Service
public class ClassLeaderServiceImpl extends ServiceImpl<ClassLeaderMapper, ClassLeader> implements IClassLeaderService {
    @Resource
    private ClassLeaderMapper classleaderMapper;


    @Override
    public Page<ClassLeader> findPage(Page<ClassLeader> page, Integer studentid) {
        return classleaderMapper.selectPage(page, new QueryWrapper<ClassLeader>().like("studentid", studentid.toString()));
    }

    @Transactional
    @Override
    public void deleteClassLeader(Integer studentId) {
        classleaderMapper.deleteClassLeader(studentId);
    }

    @Transactional
    @Override
    public void insertClassLeader(Integer studentId) {
        classleaderMapper.insertClassLeader(studentId);
    }


    @Transactional
    @Override
    public void updateClassLeader(Integer studentId) {
        classleaderMapper.updateClassLeader(studentId);
    }
}
