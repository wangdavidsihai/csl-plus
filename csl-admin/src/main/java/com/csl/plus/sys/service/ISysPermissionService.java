package com.csl.plus.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.csl.plus.bo.Tree;
import com.csl.plus.sys.entity.SysPermission;
import com.csl.plus.sys.entity.SysPermissionNode;

import java.util.List;

/**
 * <p>
 * 后台用户权限表 服务类
 * </p>
 *
 * @since 2019-04-14
 */
public interface ISysPermissionService extends IService<SysPermission> {

    List<Tree<SysPermission>> getPermissionsByUserId(Long id);

    List<SysPermissionNode> treeList();
}
