package com.qingge.springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qingge.springboot.entity.Duty;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface DutyMapper extends BaseMapper<Duty> {

    Page<Duty> findPage(Page<Duty> page, @Param("dutyid") Integer dutyid);
    void deleteDuty(@Param("dutyid") Integer dutyid);

    void insertDuty(@Param("dutyid") Integer dutyid);
    void updateDuty(@Param("dutyid") Integer dutyid);
}