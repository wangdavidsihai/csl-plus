package com.csl.plus.pms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.csl.plus.pms.entity.PmsProductAttribute;
import com.csl.plus.pms.vo.ProductAttrInfo;

import java.util.List;

/**
 * <p>
 * 商品属性参数表 Mapper 接口
 * </p>
 *
 *  
 * @since 2019-04-19
 */
public interface PmsProductAttributeMapper extends BaseMapper<PmsProductAttribute> {

    List<ProductAttrInfo> getProductAttrInfo(Long productCategoryId);
}
