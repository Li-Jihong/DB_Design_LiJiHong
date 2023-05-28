package com.qingge.springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qingge.springboot.entity.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface StudentsMapper extends BaseMapper<Student> {
    Page<Student> findPage(Page<Student> page, @Param("studentid") Integer studentid);

    void deleteStudent(@Param("studentid") Integer studentid);

    void insertStudent(@Param("studentid") Integer studentid);
    void updateStudent(@Param("studentid") Integer studentid);
}