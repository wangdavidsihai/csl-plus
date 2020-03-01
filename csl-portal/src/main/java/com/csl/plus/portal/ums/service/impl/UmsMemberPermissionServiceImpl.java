package com.csl.plus.portal.ums.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.csl.plus.portal.constant.RedisKey;
import com.csl.plus.portal.ums.service.IUmsMemberPermissionService;
import com.csl.plus.portal.ums.service.RedisService;
import com.csl.plus.portal.util.JsonUtil;
import com.csl.plus.ums.entity.UmsMemberPermission;
import com.csl.plus.ums.mapper.UmsMemberPermissionMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


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
        if (!redisService.exists(String.format(RedisKey.PermisionListKey, username))) {
            List<UmsMemberPermission> list = umsMemberPermissionMapper.getUmsMemberPerms(id);
            String key = String.format(RedisKey.PermisionListKey, username);
            redisService.set(key, JsonUtil.objectToJson(list));
            return list;
        } else {
            return JsonUtil.jsonToList(redisService.get(String.format(RedisKey.PermisionListKey, username)), UmsMemberPermission.class);
        }

    }

}