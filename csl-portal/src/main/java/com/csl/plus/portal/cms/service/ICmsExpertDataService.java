package com.csl.plus.portal.cms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.csl.plus.cms.entity.CmsExpertData;

/**
 * 专家表
 *
 * @author David
 * @email wangdavidsihai@gmail.com
 * @date 2019-12-22 13:11:01
 */
public interface ICmsExpertDataService extends IService<CmsExpertData> {

	boolean saves(CmsExpertData entity);
}
