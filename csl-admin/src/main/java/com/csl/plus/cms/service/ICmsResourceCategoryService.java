package com.csl.plus.cms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.csl.plus.cms.entity.CmsResourceCategory;


/**
 * 产品类别表
 *
 * @author David
 * @email wangdavidsihai@gmail.com
 * @date 2019-12-22 13:11:01
 */
public interface ICmsResourceCategoryService extends IService<CmsResourceCategory> {

    
    boolean saves(CmsResourceCategory entity);
}

