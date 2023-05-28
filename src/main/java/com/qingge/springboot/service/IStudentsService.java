package com.qingge.springboot.service;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.qingge.springboot.entity.Student;
import org.springframework.transaction.annotation.Transactional;

public interface IStudentsService extends IService<Student> {

    Page<Student> findPage(Page<Student> page, Integer studentid);

    void deleteStudent(Integer studentId);
    void insertStudent(Integer studentId);

    @Transactional
    void updateStudent(Integer studentId);

}
