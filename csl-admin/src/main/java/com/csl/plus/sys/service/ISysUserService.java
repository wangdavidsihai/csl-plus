package com.csl.plus.sys.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.csl.plus.sys.entity.SysPermission;
import com.csl.plus.sys.entity.SysRole;
import com.csl.plus.sys.entity.SysUser;

/**
 * <p>
 * 后台用户表 服务类
 * </p>
 *
 * @since 2019-04-14
 */
public interface ISysUserService extends IService<SysUser> {

	String refreshToken(String token);

	String login(String username, String password);

	int updateUserRole(Long adminId, List<Long> roleIds);

	List<SysRole> getRoleListByUserId(Long adminId);

	int updatePermissionByUserId(Long adminId, List<Long> permissionIds);

	List<SysPermission> getPermissionListByUserId(Long adminId);

	boolean saves(SysUser entity);

	boolean updates(Long id, SysUser admin);

	List<SysPermission> listUserPerms(Long id);

	void removePermissRedis(Long id);

	SysUser getOneUserInfo(SysUser user);
}
