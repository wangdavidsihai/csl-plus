package com.csl.plus.pms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.csl.plus.pms.entity.PmsProductAttributeCategory;
import com.csl.plus.pms.vo.PmsProductAttributeCategoryItem;

import java.util.List;

/**
 * <p>
 * 产品属性分类表 Mapper 接口
 * </p>
 *
 *  
 * @since 2019-04-19
 */
public interface PmsProductAttributeCategoryMapper extends BaseMapper<PmsProductAttributeCategory> {

    List<PmsProductAttributeCategoryItem> getListWithAttr();
}
