package com.csl.plus.portal.cms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.csl.plus.cms.entity.CmsResource;

/**
 * 资源表
 *
 * @author David
 * @email wangdavidsihai@gmail.com
 * @date 2019-12-22 13:11:01
 */
public interface ICmsResourceService extends IService<CmsResource> {

	boolean saves(CmsResource entity);
}
