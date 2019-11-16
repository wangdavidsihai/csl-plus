package com.csl.plus.pms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.csl.plus.pms.entity.PmsProductCategory;
import com.csl.plus.pms.vo.PmsProductCategoryWithChildrenItem;

import java.util.List;

/**
 * <p>
 * 产品分类 Mapper 接口
 * </p>
 *
 *  
 * @since 2019-04-19
 */
public interface PmsProductCategoryMapper extends BaseMapper<PmsProductCategory> {

    List<PmsProductCategoryWithChildrenItem> listWithChildren();
}
