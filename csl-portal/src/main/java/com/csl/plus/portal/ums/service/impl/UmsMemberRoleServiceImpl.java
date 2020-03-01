package com.csl.plus.portal.ums.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.csl.plus.portal.constant.RedisKey;
import com.csl.plus.portal.ums.service.IUmsMemberRoleService;
import com.csl.plus.portal.ums.service.RedisService;
import com.csl.plus.portal.util.JsonUtil;
import com.csl.plus.ums.entity.UmsMemberRole;
import com.csl.plus.ums.mapper.UmsMemberRoleMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


@Service("umsMemberRoleService")
public class UmsMemberRoleServiceImpl extends ServiceImpl<UmsMemberRoleMapper, UmsMemberRole> implements IUmsMemberRoleService {

    @Resource
    private UmsMemberRoleMapper umsMemberRoleMapper;
    @Resource
    private RedisService redisService;

    @Transactional
    public boolean saves(UmsMemberRole entity) {
        umsMemberRoleMapper.insert(entity);
        return true;
    }

    @Override
    public List<UmsMemberRole> getRoleListByUserId(Long userid, String username) {
        if (!redisService.exists(String.format(RedisKey.RoleListKey, username))) {
            List<UmsMemberRole> list = umsMemberRoleMapper.getRoleListByUserId(userid);
            String key = String.format(RedisKey.RoleListKey, username);
            redisService.set(key, JsonUtil.objectToJson(list));
            return list;
        } else {
            return JsonUtil.jsonToList(redisService.get(String.format(RedisKey.RoleListKey, username)), UmsMemberRole.class);
        }
    }

}