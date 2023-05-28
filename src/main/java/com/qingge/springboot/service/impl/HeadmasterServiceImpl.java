package com.qingge.springboot.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.log.Log;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qingge.springboot.common.Constants;
import com.qingge.springboot.controller.dto.HeadmasterDTO;
import com.qingge.springboot.controller.dto.HeadmasterPasswordDTO;
import com.qingge.springboot.entity.Menu;
import com.qingge.springboot.entity.Headmaster;
import com.qingge.springboot.exception.ServiceException;
import com.qingge.springboot.mapper.RoleMapper;
import com.qingge.springboot.mapper.RoleMenuMapper;
import com.qingge.springboot.mapper.HeadmasterMapper;
import com.qingge.springboot.service.IMenuService;
import com.qingge.springboot.service.IHeadmasterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qingge.springboot.utils.TokenUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 青哥哥
 * @since 2022-01-26
 */
@Service
public class HeadmasterServiceImpl extends ServiceImpl<HeadmasterMapper, Headmaster> implements IHeadmasterService {

    private static final Log LOG = Log.get();

    @Resource
    private HeadmasterMapper headmasterMapper;

    @Resource
    private RoleMapper roleMapper;

    @Resource
    private RoleMenuMapper roleMenuMapper;

    @Resource
    private IMenuService menuService;

    @Override
    public HeadmasterDTO login(HeadmasterDTO headmasterDTO) {
        // 用户密码 md5加密
        headmasterDTO.setPassword(SecureUtil.md5(headmasterDTO.getPassword()));
        Headmaster one = getUserInfo(headmasterDTO);
        if (one != null) {
            BeanUtil.copyProperties(one, headmasterDTO, true);
            // 设置token
            String token = TokenUtils.genToken(one.getHeadmasterid().toString(), one.getPassword());
            headmasterDTO.setToken(token);

            String role = one.getRole(); // ROLE_ADMIN
            // 设置用户的菜单列表
            List<Menu> roleMenus = getRoleMenus(role);
            headmasterDTO.setMenus(roleMenus);
            return headmasterDTO;
        } else {
            throw new ServiceException(Constants.CODE_600, "用户名或密码错误");
        }
    }


    @Override
    public Headmaster register(HeadmasterDTO headmasterDTO) {
        // 用户密码 md5加密
        headmasterDTO.setPassword(SecureUtil.md5(headmasterDTO.getPassword()));
        Headmaster one = getUserInfo(headmasterDTO);
        if (one == null) {
            one = new Headmaster();
            BeanUtil.copyProperties(headmasterDTO, one, true);
//            // 默认一个普通用户的角色
//            one.setRole(RoleEnum.ROLE_STUDENT.toString());
//            if (one.getNickname() == null) {
//                one.setNickname(one.getUsername());
//            }
            save(one);  // 把 copy完之后的用户对象存储到数据库
        } else {
            throw new ServiceException(Constants.CODE_600, "用户已存在");
        }
        return one;
    }

    @Override
    public void updatePassword(HeadmasterPasswordDTO headmasterPasswordDTO) {
        int update = headmasterMapper.updatePassword(headmasterPasswordDTO);
        if (update < 1) {
            throw new ServiceException(Constants.CODE_600, "密码错误");
        }
    }

    @Override
    public Page<Headmaster> findPage(Page<Headmaster> page, Integer headmasterid, String username, String password, Integer classid) {
        return headmasterMapper.findPage(page, String.valueOf(headmasterid), username, password , classid);
    }

    private Headmaster getUserInfo(HeadmasterDTO headmasterDTO) {
        QueryWrapper<Headmaster> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("\"username\"", headmasterDTO.getUsername());
        queryWrapper.eq("\"password\"", headmasterDTO.getPassword());
//        queryWrapper.eq("\"headmasterid\"", headmasterDTO.getHeadmasterid());
//        queryWrapper.eq("\"token\"", headmasterDTO.getToken());
//        queryWrapper.eq("\"role\"", headmasterDTO.getRole());
        Headmaster one;
        try {
            one = getOne(queryWrapper); // 从数据库查询用户信息
        } catch (Exception e) {
            LOG.error(e);
            throw new ServiceException(Constants.CODE_500, "系统错误");
        }
        return one;
    }

    /**
     * 获取当前角色的菜单列表
     * @param roleFlag
     * @return
     */
    private List<Menu> getRoleMenus(String roleFlag) {
        Integer roleId = roleMapper.selectByFlag(roleFlag);
        // 当前角色的所有菜单id集合
        List<Integer> menuIds = roleMenuMapper.selectByRoleId(roleId);

        // 查出系统所有的菜单(树形)
        List<Menu> menus = menuService.findMenus("");
        // new一个最后筛选完成之后的list
        List<Menu> roleMenus = new ArrayList<>();
        // 筛选当前用户角色的菜单
        for (Menu menu : menus) {
            if (menuIds.contains(menu.getId())) {
                roleMenus.add(menu);
            }
            List<Menu> children = menu.getChildren();
            // removeIf()  移除 children 里面不在 menuIds集合中的 元素
            children.removeIf(child -> !menuIds.contains(child.getId()));
        }
        return roleMenus;
    }

}
