package com.csl.plus.portal.pms.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.csl.plus.pms.entity.PmsProductCategory;
import com.csl.plus.pms.mapper.PmsProductCategoryMapper;
import com.csl.plus.pms.vo.PmsProductCategoryWithChildrenItem;
import com.csl.plus.portal.pms.service.IPmsProductCategoryService;

/**
 * <p>
 * 产品分类 服务实现类
 * </p>
 *
 * @since 2019-04-19
 */
@Service
public class PmsProductCategoryServiceImpl extends ServiceImpl<PmsProductCategoryMapper, PmsProductCategory>
		implements IPmsProductCategoryService {

	@Resource
	private PmsProductCategoryMapper categoryMapper;

	@Override
	public List<PmsProductCategoryWithChildrenItem> listWithChildren() {
		return categoryMapper.listWithChildren();
	}
}
