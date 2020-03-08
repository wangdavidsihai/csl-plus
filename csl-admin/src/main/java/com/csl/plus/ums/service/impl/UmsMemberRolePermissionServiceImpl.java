package com.csl.plus.ums.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.csl.plus.ums.entity.UmsMemberRolePermission;
import com.csl.plus.ums.mapper.UmsMemberRolePermissionMapper;
import com.csl.plus.ums.service.IUmsMemberRolePermissionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


@Service("umsMemberRolePermissionService")
public class UmsMemberRolePermissionServiceImpl extends ServiceImpl<UmsMemberRolePermissionMapper, UmsMemberRolePermission> implements IUmsMemberRolePermissionService {

    @Resource
    private UmsMemberRolePermissionMapper umsMemberRolePermissionMapper;

    @Transactional
    public boolean saves(UmsMemberRolePermission entity) {
        umsMemberRolePermissionMapper.insert(entity);
        return true;
    }

}