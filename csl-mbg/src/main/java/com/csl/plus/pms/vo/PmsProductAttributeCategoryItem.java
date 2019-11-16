package com.csl.plus.pms.vo;


import java.util.List;

import com.csl.plus.pms.entity.PmsProductAttribute;
import com.csl.plus.pms.entity.PmsProductAttributeCategory;

/**
 * 包含有分类下属性的dto
 * https://github.com/shenzhuan/mallplus on 2018/5/24.
 */
public class PmsProductAttributeCategoryItem extends PmsProductAttributeCategory {
    private List<PmsProductAttribute> productAttributeList;

    public List<PmsProductAttribute> getProductAttributeList() {
        return productAttributeList;
    }

    public void setProductAttributeList(List<PmsProductAttribute> productAttributeList) {
        this.productAttributeList = productAttributeList;
    }
}
