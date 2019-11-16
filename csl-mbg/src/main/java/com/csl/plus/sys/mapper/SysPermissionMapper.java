package com.csl.plus.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.csl.plus.sys.entity.SysPermission;

import java.util.List;

/**
 * <p>
 * 后台用户权限表 Mapper 接口
 * </p>
 *
 *  
 * @since 2019-04-14
 */
public interface SysPermissionMapper extends BaseMapper<SysPermission> {

    List<SysPermission> listMenuByUserId(Long id);

    List<SysPermission> getPermissionList(Long roleId);

    List<SysPermission> listUserPerms(Long id);
}
