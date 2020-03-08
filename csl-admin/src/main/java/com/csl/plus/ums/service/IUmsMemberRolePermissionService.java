package com.csl.plus.ums.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.csl.plus.ums.entity.UmsMemberRolePermission;


/**
 * 后台用户角色和权限关系表
 *
 * @author David
 * @email
 * @date 2020-02-16 21:48:43
 */
public interface IUmsMemberRolePermissionService extends IService<UmsMemberRolePermission> {

    boolean saves(UmsMemberRolePermission entity);

}

