package com.csl.plus.ums.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.csl.plus.ums.entity.UmsMemberUserRole;
import org.apache.ibatis.annotations.Mapper;

/**
 * 后台用户和角色关系表
 *
 * @author David
 * @email
 * @date 2020-02-17 17:03:22
 */
@Mapper
public interface UmsMemberUserRoleMapper extends BaseMapper<UmsMemberUserRole> {

}
