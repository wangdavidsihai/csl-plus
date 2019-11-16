package com.csl.plus.ums.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.csl.plus.ums.entity.UmsMemberProductCategoryRelation;
import com.csl.plus.ums.mapper.UmsMemberProductCategoryRelationMapper;
import com.csl.plus.ums.service.IUmsMemberProductCategoryRelationService;

import org.springframework.stereotype.Service;

/**
 * <p>
 * 会员与产品分类关系表（用户喜欢的分类） 服务实现类
 * </p>
 *
 * @since 2019-04-19
 */
@Service
public class UmsMemberProductCategoryRelationServiceImpl extends ServiceImpl<UmsMemberProductCategoryRelationMapper, UmsMemberProductCategoryRelation> implements IUmsMemberProductCategoryRelationService {

}
