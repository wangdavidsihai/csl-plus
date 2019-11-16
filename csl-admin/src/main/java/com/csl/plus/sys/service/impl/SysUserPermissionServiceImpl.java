package com.csl.plus.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.csl.plus.sys.entity.SysUserPermission;
import com.csl.plus.sys.mapper.SysUserPermissionMapper;
import com.csl.plus.sys.service.ISysUserPermissionService;

import org.springframework.stereotype.Service;

/**
 * <p>
 * 后台用户和权限关系表(除角色中定义的权限以外的加减权限) 服务实现类
 * </p>
 *
 * @since 2019-04-14
 */
@Service
public class SysUserPermissionServiceImpl extends ServiceImpl<SysUserPermissionMapper, SysUserPermission> implements ISysUserPermissionService {

}
