package com.csl.plus.portal.ums.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.csl.plus.portal.ums.service.IUmsMemberPermissionService;
import com.csl.plus.ums.entity.UmsMemberPermission;
import com.csl.plus.ums.mapper.UmsMemberPermissionMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


@Service("umsMemberPermissionService")
public class UmsMemberPermissionServiceImpl extends ServiceImpl<UmsMemberPermissionMapper, UmsMemberPermission> implements IUmsMemberPermissionService {

    @Resource
    private UmsMemberPermissionMapper umsMemberPermissionMapper;

    @Transactional
    public boolean saves(UmsMemberPermission entity) {
        umsMemberPermissionMapper.insert(entity);
        return true;
    }

}