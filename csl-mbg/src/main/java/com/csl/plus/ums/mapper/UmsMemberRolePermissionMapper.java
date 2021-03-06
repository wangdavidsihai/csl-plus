package com.csl.plus.ums.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.csl.plus.ums.entity.UmsMemberRolePermission;
import org.apache.ibatis.annotations.Mapper;

/**
 * 后台用户角色和权限关系表
 *
 * @author David
 * @email
 * @date 2020-02-16 21:48:43
 */
@Mapper
public interface UmsMemberRolePermissionMapper extends BaseMapper<UmsMemberRolePermission> {

}
