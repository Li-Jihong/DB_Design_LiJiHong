package com.qingge.springboot.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qingge.springboot.controller.dto.HeadmasterPasswordDTO;
import com.qingge.springboot.entity.Headmaster;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 青哥哥
 * @since 2022-01-26
 */
public interface HeadmasterMapper extends BaseMapper<Headmaster> {

    @Update("update \"DB_DESIGN\".\"HEADMASTER\" set \"password\" = #{newpassword} where \"username\" = #{username} and \"password\" = #{password}")
    int updatePassword(HeadmasterPasswordDTO headmasterPasswordDTO);

    Page<Headmaster> findPage(Page<Headmaster> page, @Param("\"headmasterid\"") String headmasterid, @Param("\"username\"") String username, @Param("\"password\"") String password, @Param("\"classid\"") Integer classid);

}
