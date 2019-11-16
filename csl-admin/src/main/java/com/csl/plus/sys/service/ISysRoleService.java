package com.csl.plus.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.csl.plus.sys.entity.SysPermission;
import com.csl.plus.sys.entity.SysRole;
import com.csl.plus.sys.entity.SysRolePermission;

import java.util.List;

/**
 * <p>
 * 后台用户角色表 服务类
 * </p>
 *
 * @since 2019-04-14
 */
public interface ISysRoleService extends IService<SysRole> {
    List<SysRolePermission> getRolePermission(Long roleId);

    /**
     * 获取指定角色权限
     */
    List<SysPermission> getPermissionList(Long roleId);

    boolean saves(SysRole entity);

    boolean updates(SysRole entity);
}
