package com.csl.plus.sys.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.csl.plus.bo.Rediskey;
import com.csl.plus.sys.entity.SysPermission;
import com.csl.plus.sys.entity.SysRole;
import com.csl.plus.sys.entity.SysUser;
import com.csl.plus.sys.entity.SysUserPermission;
import com.csl.plus.sys.entity.SysUserRole;
import com.csl.plus.sys.mapper.SysPermissionMapper;
import com.csl.plus.sys.mapper.SysRoleMapper;
import com.csl.plus.sys.mapper.SysUserMapper;
import com.csl.plus.sys.mapper.SysUserPermissionMapper;
import com.csl.plus.sys.mapper.SysUserRoleMapper;
import com.csl.plus.sys.service.ISysRolePermissionService;
import com.csl.plus.sys.service.ISysUserPermissionService;
import com.csl.plus.sys.service.ISysUserRoleService;
import com.csl.plus.sys.service.ISysUserService;
import com.csl.plus.ums.service.RedisService;
import com.csl.plus.util.JsonUtil;
import com.csl.plus.util.JwtTokenUtil;
import com.csl.plus.util.UserUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * 后台用户表 服务实现类
 * </p>
 *
 * @since 2019-04-14
 */
@Slf4j
@Service("sysUserService")
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

	@Autowired(required = false)
	private AuthenticationManager authenticationManager;
	@Resource
	private UserDetailsService userDetailsService;
	@Resource
	private JwtTokenUtil jwtTokenUtil;
	@Resource
	private PasswordEncoder passwordEncoder;
	@Value("${jwt.tokenHead}")
	private String tokenHead;
	@Resource
	private SysUserMapper adminMapper;
	@Resource
	private SysUserRoleMapper adminRoleRelationMapper;
	@Resource
	private ISysUserRoleService adminRoleRelationService;
	@Resource
	private SysUserPermissionMapper adminPermissionRelationMapper;
	@Resource
	private SysRoleMapper roleMapper;
	@Resource
	private ISysUserPermissionService userPermissionService;
	@Resource
	private ISysRolePermissionService rolePermissionService;
	@Resource
	private ISysUserRoleService userRoleService;
	@Resource
	private SysPermissionMapper permissionMapper;

	@Resource
	private RedisService redisService;

	@Override
	public String refreshToken(String oldToken) {
		String token = oldToken.substring(tokenHead.length());
		if (jwtTokenUtil.canRefresh(token)) {
			return jwtTokenUtil.refreshToken(token);
		}
		return null;
	}

	@Override
	public String login(String username, String password) {
		String token = null;

		// 密码需要客户端加密后传递
		// UsernamePasswordAuthenticationToken authenticationToken = new
		// UsernamePasswordAuthenticationToken(username,
		// passwordEncoder.encode(password));
		try {
			UserDetails userDetails = userDetailsService.loadUserByUsername(username);
			if (!passwordEncoder.matches(password, userDetails.getPassword())) {
				throw new BadCredentialsException("密码不正确");
			}
			Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null,
					userDetails.getAuthorities());
//			Authentication authentication = authenticationManager.authenticate(authenticationToken);
			SecurityContextHolder.getContext().setAuthentication(authentication);
			token = jwtTokenUtil.generateToken(userDetails);
			this.removePermissRedis(UserUtils.getCurrentMember().getId());
		} catch (AuthenticationException e) {
			log.warn("登录异常:{}", e.getMessage());
		}
		return token;
	}

	@Override
	public int updateUserRole(Long adminId, List<Long> roleIds) {
		int count = roleIds == null ? 0 : roleIds.size();
		// 先删除原来的关系
		SysUserRole userRole = new SysUserRole();
		userRole.setAdminId(adminId);
		adminRoleRelationMapper.delete(new QueryWrapper<>(userRole));
		// 建立新关系
		if (!CollectionUtils.isEmpty(roleIds)) {
			List<SysUserRole> list = new ArrayList<>();
			for (Long roleId : roleIds) {
				SysUserRole roleRelation = new SysUserRole();
				roleRelation.setAdminId(adminId);
				roleRelation.setRoleId(roleId);
				list.add(roleRelation);
			}
			userRoleService.saveBatch(list);
		}
		return count;
	}

	@Override
	public List<SysRole> getRoleListByUserId(Long adminId) {
		return roleMapper.getRoleListByUserId(adminId);
	}

	@Override
	public int updatePermissionByUserId(Long adminId, List<Long> permissionIds) {
		// 删除原所有权限关系
		SysUserPermission userPermission = new SysUserPermission();
		userPermission.setAdminId(adminId);
		adminPermissionRelationMapper.delete(new QueryWrapper<>(userPermission));
		// 获取用户所有角色权限
		List<SysPermission> permissionList = permissionMapper.listMenuByUserId(adminId);
		List<Long> rolePermissionList = permissionList.stream().map(SysPermission::getId).collect(Collectors.toList());
		if (!CollectionUtils.isEmpty(permissionIds)) {
			List<SysUserPermission> relationList = new ArrayList<>();
			// 筛选出+权限
			List<Long> addPermissionIdList = permissionIds.stream()
					.filter(permissionId -> !rolePermissionList.contains(permissionId)).collect(Collectors.toList());
			// 筛选出-权限
			List<Long> subPermissionIdList = rolePermissionList.stream()
					.filter(permissionId -> !permissionIds.contains(permissionId)).collect(Collectors.toList());
			// 插入+-权限关系
			relationList.addAll(convert(adminId, 1, addPermissionIdList));
			relationList.addAll(convert(adminId, -1, subPermissionIdList));
			userPermissionService.saveBatch(relationList);
		}
		return 0;
	}

	@Override
	public List<SysPermission> getPermissionListByUserId(Long adminId) {
		if (!redisService.exists(String.format(Rediskey.menuList, adminId))) {
			List<SysPermission> list = permissionMapper.listMenuByUserId(adminId);
			redisService.set(String.format(Rediskey.menuTreesList, adminId), JsonUtil.objectToJson(list));
			return list;
		} else {
			return JsonUtil.jsonToList(redisService.get(String.format(Rediskey.menuTreesList, adminId)),
					SysPermission.class);
		}

	}

	@Override
	public boolean saves(SysUser umsAdmin) {
		umsAdmin.setCreateTime(new Date());
		umsAdmin.setStatus(1);
		// 查询是否有相同用户名的用户

		List<SysUser> umsAdminList = adminMapper
				.selectList(new QueryWrapper<SysUser>().eq("username", umsAdmin.getUsername()));
		if (umsAdminList.size() > 0) {
			return false;
		}
		// 将密码进行加密操作
		// TODO 默认密码考虑使用用户手机号或者身份证号码某些位数以及用户名策略，预留策略模式，校验密码强度
		if (StringUtils.isEmpty(umsAdmin.getPassword())) {
			umsAdmin.setPassword("123456");
		}
		String md5Password = passwordEncoder.encode(umsAdmin.getPassword());
		umsAdmin.setPassword(md5Password);
		adminMapper.insert(umsAdmin);
		updateRole(umsAdmin.getId(), umsAdmin.getRoleIds());
		return true;
	}

	@Override
	@Transactional
	public boolean updates(Long id, SysUser admin) {
		admin.setUsername(null);
		admin.setId(id);
		String md5Password = passwordEncoder.encode(admin.getPassword());
		admin.setPassword(md5Password);
		updateRole(id, admin.getRoleIds());
		adminMapper.updateById(admin);
		return true;
	}

	@Override
	public List<SysPermission> listUserPerms(Long id) {
		if (!redisService.exists(String.format(Rediskey.menuList, id))) {
			List<SysPermission> list = permissionMapper.listUserPerms(id);
			String key = String.format(Rediskey.menuList, id);
			redisService.set(key, JsonUtil.objectToJson(list));
			return list;
		} else {
			return JsonUtil.jsonToList(redisService.get(String.format(Rediskey.menuList, id)), SysPermission.class);
		}

	}

	@Override
	public void removePermissRedis(Long id) {
		redisService.remove(String.format(Rediskey.menuTreesList, id));
		redisService.remove(String.format(Rediskey.menuList, id));
	}

	/**
	 * 将+-权限关系转化为对象
	 */
	private List<SysUserPermission> convert(Long adminId, Integer type, List<Long> permissionIdList) {
		List<SysUserPermission> relationList = permissionIdList.stream().map(permissionId -> {
			SysUserPermission relation = new SysUserPermission();
			relation.setAdminId(adminId);
			relation.setType(type);
			relation.setPermissionId(permissionId);
			return relation;
		}).collect(Collectors.toList());
		return relationList;
	}

	public void updateRole(Long adminId, String roleIds) {
		this.removePermissRedis(adminId);
		adminRoleRelationMapper.delete(new QueryWrapper<SysUserRole>().eq("admin_id", adminId));
		// 建立新关系
		if (!StringUtils.isEmpty(roleIds)) {
			String[] rids = roleIds.split(",");
			List<SysUserRole> list = new ArrayList<>();
			for (String roleId : rids) {
				SysUserRole roleRelation = new SysUserRole();
				roleRelation.setAdminId(adminId);
				roleRelation.setRoleId(Long.valueOf(roleId));
				list.add(roleRelation);
			}
			adminRoleRelationService.saveBatch(list);
		}
	}

	/**
	 * 获取Sysuer的details信息，包含Role的信息
	 */
	@Override
	public SysUser getOneUserInfo(SysUser user) {
		SysUser sysUser = this.getOne(new QueryWrapper<>(user));
		List<SysRole> roleList = getRoleListByUserId(sysUser.getId());
		for (SysRole r : roleList) {
			if (!StringUtils.isEmpty(sysUser.getRoleIds())) {
				sysUser.setRoleIds(sysUser.getRoleIds() + "," + r.getId());
			} else {
				sysUser.setRoleIds(r.getId().toString());
			}
		}
		return sysUser;
	}
}
