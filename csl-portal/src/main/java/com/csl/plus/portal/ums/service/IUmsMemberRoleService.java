package com.csl.plus.portal.ums.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.csl.plus.ums.entity.UmsMemberRole;


/**
 * 后台用户角色表
 *
 * @author David
 * @email
 * @date 2020-02-16 21:48:43
 */
public interface IUmsMemberRoleService extends IService<UmsMemberRole> {


    boolean saves(UmsMemberRole entity);
}

