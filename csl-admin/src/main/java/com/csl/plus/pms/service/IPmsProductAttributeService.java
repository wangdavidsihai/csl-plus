package com.csl.plus.pms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.csl.plus.pms.entity.PmsProductAttribute;
import com.csl.plus.pms.vo.ProductAttrInfo;

import java.util.List;

/**
 * <p>
 * 商品属性参数表 服务类
 * </p>
 *
 * @since 2019-04-19
 */
public interface IPmsProductAttributeService extends IService<PmsProductAttribute> {

    List<ProductAttrInfo> getProductAttrInfo(Long productCategoryId);

    boolean saveAndUpdate(PmsProductAttribute entity);
}
