package com.qingge.springboot.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qingge.springboot.controller.dto.HeadmasterDTO;
import com.qingge.springboot.controller.dto.HeadmasterPasswordDTO;
import com.qingge.springboot.entity.Headmaster;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 青哥哥
 * @since 2022-01-26
 */
public interface IHeadmasterService extends IService<Headmaster> {

    HeadmasterDTO login(HeadmasterDTO headmasterDTO);

    Headmaster register(HeadmasterDTO headmasterDTO);

    void updatePassword(HeadmasterPasswordDTO headmasterPasswordDTO);


    Page<Headmaster> findPage(Page<Headmaster> page, Integer headmasterid, String username, String password, Integer classid);
}
