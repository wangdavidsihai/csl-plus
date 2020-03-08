package com.csl.plus.ums.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.csl.plus.ums.entity.UmsMemberRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * 后台用户角色表
 *
 * @author David
 * @email
 * @date 2020-02-16 21:48:43
 */
public interface IUmsMemberRoleService extends IService<UmsMemberRole> {


    boolean saves(UmsMemberRole entity);

    /**
     * 获取用于所有角色
     */
    List<UmsMemberRole> getRoleListByUserId(@Param("userid") Long userid, String username);

}

