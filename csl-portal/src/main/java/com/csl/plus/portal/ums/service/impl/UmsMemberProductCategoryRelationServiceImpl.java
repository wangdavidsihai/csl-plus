package com.csl.plus.portal.ums.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.csl.plus.portal.ums.service.IUmsMemberProductCategoryRelationService;
import com.csl.plus.ums.entity.UmsMemberProductCategoryRelation;
import com.csl.plus.ums.mapper.UmsMemberProductCategoryRelationMapper;

/**
 * <p>
 * 会员与产品分类关系表（用户喜欢的分类） 服务实现类
 * </p>
 *
 * @since 2019-04-19
 */
@Service
public class UmsMemberProductCategoryRelationServiceImpl
		extends ServiceImpl<UmsMemberProductCategoryRelationMapper, UmsMemberProductCategoryRelation>
		implements IUmsMemberProductCategoryRelationService {

}
