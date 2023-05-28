package com.qingge.springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qingge.springboot.entity.DutyStudent;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface DutyStudentMapper extends BaseMapper<DutyStudent> {
    Page<DutyStudent> findPage(Page<DutyStudent> page, @Param("dutyid") Integer dutyid);
    void deleteDutyStudent(@Param("dutyid") Integer dutyid);

    void insertDutyStudent(@Param("dutyid") Integer dutyid);
    void updateDutyStudent(@Param("dutyid") Integer dutyid);
    void removeByDutyIdAndStudentId(@Param("dutyid") Integer dutyid, @Param("studentid") Integer studentid);
}
