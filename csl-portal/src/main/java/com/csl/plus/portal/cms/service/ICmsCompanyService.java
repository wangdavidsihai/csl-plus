package com.csl.plus.portal.cms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.csl.plus.cms.entity.CmsCompany;

/**
 * Company
 *
 * @author David
 * @email wangdavidsihai@gmail.com
 * @date 2019-12-19 20:56:57
 */
public interface ICmsCompanyService extends IService<CmsCompany> {

	boolean saves(CmsCompany entity);
}
