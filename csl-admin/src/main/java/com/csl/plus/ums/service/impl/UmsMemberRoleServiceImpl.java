package com.csl.plus.ums.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.csl.plus.ums.entity.UmsMemberRole;
import com.csl.plus.ums.entity.UmsMemberRolePermission;
import com.csl.plus.ums.mapper.UmsMemberRoleMapper;
import com.csl.plus.ums.mapper.UmsMemberRolePermissionMapper;
import com.csl.plus.ums.service.IUmsMemberRolePermissionService;
import com.csl.plus.ums.service.IUmsMemberRoleService;
import com.csl.plus.ums.service.RedisService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service("umsMemberRoleService")
public class UmsMemberRoleServiceImpl extends ServiceImpl<UmsMemberRoleMapper, UmsMemberRole> implements IUmsMemberRoleService {

    @Resource
    private UmsMemberRoleMapper umsMemberRoleMapper;
    @Resource
    private RedisService redisService;
    @Resource
    private UmsMemberRolePermissionMapper umsMemberRolePermissionMapper;
    @Resource
    private IUmsMemberRolePermissionService umsMemberRolePermissionService;

    @Transactional
    public boolean saves(UmsMemberRole entity) {
        entity.setCreateTime(new Date());
        entity.setStatus(1);
        entity.setAdminCount(0);
        entity.setSort(0);
        umsMemberRoleMapper.insert(entity);
        updatePermission(entity.getId(), entity.getMenuIds());
        return true;
    }

    @Override
    public List<UmsMemberRole> getRoleListByUserId(Long userid, String username) {
//        if (!redisService.exists(String.format(RedisKey.RoleListKey, username))) {
        List<UmsMemberRole> list = umsMemberRoleMapper.getRoleListByUserId(userid);
//        String key = String.format(RedisKey.RoleListKey, username);
//        redisService.set(key, JsonUtil.objectToJson(list));
        return list;
//        } else {
//            return JsonUtil.jsonToList(redisService.get(String.format(RedisKey.RoleListKey, username)), UmsMemberRole.class);
//        }
    }

    public void updatePermission(Long roleId, String permissionIds) {
        //先删除原有关系
        umsMemberRolePermissionMapper.delete(new QueryWrapper<UmsMemberRolePermission>().eq("role_id", roleId));
        //批量插入新关系
        List<UmsMemberRolePermission> relationList = new ArrayList<>();
        if (!StringUtils.isEmpty(permissionIds)) {
            String[] mids = permissionIds.split(",");
            for (String permissionId : mids) {
                UmsMemberRolePermission relation = new UmsMemberRolePermission();
                relation.setRoleId(roleId);
                relation.setPermissionId(Long.valueOf(permissionId));
                relationList.add(relation);
            }
            umsMemberRolePermissionService.saveBatch(relationList);
        }

    }

}