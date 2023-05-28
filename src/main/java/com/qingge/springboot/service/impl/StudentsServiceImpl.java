package com.qingge.springboot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qingge.springboot.entity.Student;
import com.qingge.springboot.mapper.StudentsMapper;
import com.qingge.springboot.service.IStudentsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class StudentsServiceImpl extends ServiceImpl<StudentsMapper, Student> implements IStudentsService {
    @Resource
    private StudentsMapper studentMapper;



    @Override
    public Page<Student> findPage(Page<Student> page, Integer studentid) {
        return studentMapper.selectPage(page, new QueryWrapper<Student>().like("studentid", studentid.toString()));
    }
    @Transactional
    @Override
    public void deleteStudent(Integer studentId) {
        studentMapper.deleteStudent(studentId);
    }
    @Transactional
    @Override
    public void insertStudent(Integer studentId) {
        studentMapper.insertStudent(studentId);
    }



    @Transactional
    @Override
    public void updateStudent(Integer studentId) {
        studentMapper.updateStudent(studentId);
    }

}
