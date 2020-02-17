package com.csl.plus.portal.ums.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.csl.plus.ums.entity.UmsMemberUserRole;


/**
 * 后台用户和角色关系表
 *
 * @author David
 * @email
 * @date 2020-02-17 17:03:22
 */
public interface IUmsMemberUserRoleService extends IService<UmsMemberUserRole> {


    boolean saves(UmsMemberUserRole entity);
}

