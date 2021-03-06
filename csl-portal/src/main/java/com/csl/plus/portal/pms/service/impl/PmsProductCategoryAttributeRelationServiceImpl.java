package com.csl.plus.portal.pms.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.csl.plus.pms.entity.PmsProductCategoryAttributeRelation;
import com.csl.plus.pms.mapper.PmsProductCategoryAttributeRelationMapper;
import com.csl.plus.portal.pms.service.IPmsProductCategoryAttributeRelationService;

/**
 * <p>
 * 产品的分类和属性的关系表，用于设置分类筛选条件（只支持一级分类） 服务实现类
 * </p>
 *
 * @since 2019-04-19
 */
@Service
public class PmsProductCategoryAttributeRelationServiceImpl
		extends ServiceImpl<PmsProductCategoryAttributeRelationMapper, PmsProductCategoryAttributeRelation>
		implements IPmsProductCategoryAttributeRelationService {

}
