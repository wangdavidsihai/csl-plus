package com.csl.plus.portal.pms.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.csl.plus.pms.entity.PmsProductCategory;
import com.csl.plus.pms.vo.PmsProductCategoryWithChildrenItem;

/**
 * <p>
 * 产品分类 服务类
 * </p>
 *
 * @since 2019-04-19
 */
public interface IPmsProductCategoryService extends IService<PmsProductCategory> {

	List<PmsProductCategoryWithChildrenItem> listWithChildren();
}
