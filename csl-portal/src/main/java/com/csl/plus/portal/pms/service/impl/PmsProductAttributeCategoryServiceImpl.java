package com.csl.plus.portal.pms.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.csl.plus.pms.entity.PmsProductAttributeCategory;
import com.csl.plus.pms.mapper.PmsProductAttributeCategoryMapper;
import com.csl.plus.pms.vo.PmsProductAttributeCategoryItem;
import com.csl.plus.portal.pms.service.IPmsProductAttributeCategoryService;

/**
 * <p>
 * 产品属性分类表 服务实现类
 * </p>
 *
 * @since 2019-04-19
 */
@Service
public class PmsProductAttributeCategoryServiceImpl
		extends ServiceImpl<PmsProductAttributeCategoryMapper, PmsProductAttributeCategory>
		implements IPmsProductAttributeCategoryService {

	@Resource
	private PmsProductAttributeCategoryMapper productAttributeCategoryMapper;

	@Override
	public List<PmsProductAttributeCategoryItem> getListWithAttr() {
		return productAttributeCategoryMapper.getListWithAttr();
	}
}
