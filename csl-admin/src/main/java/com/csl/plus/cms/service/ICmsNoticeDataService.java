package com.csl.plus.cms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.csl.plus.cms.entity.CmsNoticeData;


/**
 * 文章详表
 *
 * @author David
 * @email wangdavidsihai@gmail.com
 * @date 2019-12-22 14:02:33
 */
public interface ICmsNoticeDataService extends IService<CmsNoticeData> {

    
    boolean saves(CmsNoticeData entity);
}

