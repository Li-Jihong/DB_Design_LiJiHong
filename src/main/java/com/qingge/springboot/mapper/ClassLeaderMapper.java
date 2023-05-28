package com.qingge.springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qingge.springboot.entity.ClassLeader;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface ClassLeaderMapper extends BaseMapper<ClassLeader> {

    Page<ClassLeader> findPage(Page<ClassLeader> page, @Param("studentid") Integer studentid);
    void deleteClassLeader(@Param("studentid") Integer studentid);

    void insertClassLeader(@Param("studentid") Integer studentid);
    void updateClassLeader(@Param("studentid") Integer studentid);
}