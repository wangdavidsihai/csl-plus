package com.csl.plus.ums.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.csl.plus.ums.entity.UmsMemberUserRole;
import com.csl.plus.ums.mapper.UmsMemberUserRoleMapper;
import com.csl.plus.ums.service.IUmsMemberUserRoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


@Service("umsMemberUserRoleService")
public class UmsMemberUserRoleServiceImpl extends ServiceImpl<UmsMemberUserRoleMapper, UmsMemberUserRole> implements IUmsMemberUserRoleService {

    @Resource
    private UmsMemberUserRoleMapper umsMemberUserRoleMapper;

    @Transactional
    public boolean saves(UmsMemberUserRole entity) {
        umsMemberUserRoleMapper.insert(entity);
        return true;
    }

}