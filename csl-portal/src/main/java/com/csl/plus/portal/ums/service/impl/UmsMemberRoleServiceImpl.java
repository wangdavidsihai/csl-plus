package com.csl.plus.portal.ums.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.csl.plus.portal.ums.service.IUmsMemberRoleService;
import com.csl.plus.ums.entity.UmsMemberRole;
import com.csl.plus.ums.mapper.UmsMemberRoleMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


@Service("umsMemberRoleService")
public class UmsMemberRoleServiceImpl extends ServiceImpl<UmsMemberRoleMapper, UmsMemberRole> implements IUmsMemberRoleService {

    @Resource
    private UmsMemberRoleMapper umsMemberRoleMapper;

    @Transactional
    public boolean saves(UmsMemberRole entity) {
        umsMemberRoleMapper.insert(entity);
        return true;
    }

}