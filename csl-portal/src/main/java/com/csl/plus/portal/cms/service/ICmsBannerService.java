package com.csl.plus.portal.cms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.csl.plus.cms.entity.CmsBanner;


/**
 * Banner List
 *
 * @author David
 * @email wangdavidsihai@gmail.com
 * @date 2019-12-22 14:02:33
 */
public interface ICmsBannerService extends IService<CmsBanner> {

    
    boolean saves(CmsBanner entity);
}

