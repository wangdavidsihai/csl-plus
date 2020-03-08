package com.csl.plus.ums.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.csl.plus.ums.entity.UmsMemberPermission;
import com.csl.plus.ums.entity.UmsMemberPermissionNode;
import com.csl.plus.ums.mapper.UmsMemberPermissionMapper;
import com.csl.plus.ums.service.IUmsMemberPermissionService;
import com.csl.plus.ums.service.RedisService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;


@Service("umsMemberPermissionService")
public class UmsMemberPermissionServiceImpl extends ServiceImpl<UmsMemberPermissionMapper, UmsMemberPermission> implements IUmsMemberPermissionService {

    @Resource
    private UmsMemberPermissionMapper umsMemberPermissionMapper;
    @Resource
    private RedisService redisService;

    @Transactional
    public boolean saves(UmsMemberPermission entity) {
        umsMemberPermissionMapper.insert(entity);
        return true;
    }

    @Override
    public List<UmsMemberPermission> getMemberPermListByUserId(Long id, String username) {
//        if (!redisService.exists(String.format(RedisKey.PermisionListKey, username))) {
        List<UmsMemberPermission> list = umsMemberPermissionMapper.getUmsMemberPerms(id);
//            String key = String.format(RedisKey.PermisionListKey, username);
//            redisService.set(key, JsonUtil.objectToJson(list));
        return list;
//        } else {
//            return JsonUtil.jsonToList(redisService.get(String.format(RedisKey.PermisionListKey, username)), UmsMemberPermission.class);
//        }
    }

    @Override
    public List<UmsMemberPermissionNode> treeList() {
        List<UmsMemberPermission> permissionList = umsMemberPermissionMapper.selectList(new QueryWrapper<>());
        List<UmsMemberPermissionNode> result = permissionList.stream().filter(permission -> permission.getPid().equals(0L))
                .map(permission -> covert(permission, permissionList)).collect(Collectors.toList());
        return result;
    }

    /**
     * 将权限转换为带有子级的权限对象 当找不到子级权限的时候map操作不会再递归调用covert
     */
    private UmsMemberPermissionNode covert(UmsMemberPermission permission, List<UmsMemberPermission> permissionList) {
        UmsMemberPermissionNode node = new UmsMemberPermissionNode();
        BeanUtils.copyProperties(permission, node);
        List<UmsMemberPermissionNode> children = permissionList.stream()
                .filter(subPermission -> subPermission.getPid().equals(permission.getId()))
                .map(subPermission -> covert(subPermission, permissionList)).collect(Collectors.toList());
        node.setChildren(children);
        return node;
    }

}