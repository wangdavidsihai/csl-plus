package com.csl.plus.portal.pms.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.csl.plus.pms.entity.PmsProductAttributeCategory;
import com.csl.plus.pms.vo.PmsProductAttributeCategoryItem;

/**
 * <p>
 * 产品属性分类表 服务类
 * </p>
 *
 * @since 2019-04-19
 */
public interface IPmsProductAttributeCategoryService extends IService<PmsProductAttributeCategory> {

	List<PmsProductAttributeCategoryItem> getListWithAttr();
}
