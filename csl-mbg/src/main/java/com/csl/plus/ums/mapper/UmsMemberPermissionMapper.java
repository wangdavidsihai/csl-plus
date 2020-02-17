package com.csl.plus.ums.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.csl.plus.ums.entity.UmsMemberPermission;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 后台用户权限表
 *
 * @author David
 * @email
 * @date 2020-02-16 21:48:43
 */
@Mapper
public interface UmsMemberPermissionMapper extends BaseMapper<UmsMemberPermission> {

    List<UmsMemberPermission> getPermissionList(Long roleId);

    List<UmsMemberPermission> getUmsMemberPerms(Long id);
}
