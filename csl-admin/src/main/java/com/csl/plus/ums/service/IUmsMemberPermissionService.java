package com.csl.plus.ums.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.csl.plus.ums.entity.UmsMemberPermission;
import com.csl.plus.ums.entity.UmsMemberPermissionNode;

import java.util.List;


/**
 * 后台用户权限表
 *
 * @author David
 * @email
 * @date 2020-02-16 21:48:43
 */
public interface IUmsMemberPermissionService extends IService<UmsMemberPermission> {


    boolean saves(UmsMemberPermission entity);

    List<UmsMemberPermission> getMemberPermListByUserId(Long id, String username);

    List<UmsMemberPermissionNode> treeList();
}

