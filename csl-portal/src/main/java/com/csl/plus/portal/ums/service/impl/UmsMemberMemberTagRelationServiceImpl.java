package com.csl.plus.portal.ums.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.csl.plus.portal.ums.service.IUmsMemberMemberTagRelationService;
import com.csl.plus.ums.entity.UmsMemberMemberTagRelation;
import com.csl.plus.ums.mapper.UmsMemberMemberTagRelationMapper;

/**
 * <p>
 * 用户和标签关系表 服务实现类
 * </p>
 *
 * @since 2019-04-19
 */
@Service
public class UmsMemberMemberTagRelationServiceImpl
		extends ServiceImpl<UmsMemberMemberTagRelationMapper, UmsMemberMemberTagRelation>
		implements IUmsMemberMemberTagRelationService {

}
