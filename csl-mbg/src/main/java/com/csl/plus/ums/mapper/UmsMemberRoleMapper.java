package com.csl.plus.ums.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.csl.plus.ums.entity.UmsMemberRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 后台用户角色表
 *
 * @author David
 * @email
 * @date 2020-02-16 21:48:43
 */
@Mapper
public interface UmsMemberRoleMapper extends BaseMapper<UmsMemberRole> {

    /**
     * 获取用于所有角色
     */
    List<UmsMemberRole> getRoleListByUserId(@Param("userid") Long userid);
}
