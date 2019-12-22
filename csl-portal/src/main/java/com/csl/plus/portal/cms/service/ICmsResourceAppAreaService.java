package com.csl.plus.portal.cms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.csl.plus.cms.entity.CmsResourceAppArea;

/**
 * 资源领域表
 *
 * @author David
 * @email wangdavidsihai@gmail.com
 * @date 2019-12-22 13:11:01
 */
public interface ICmsResourceAppAreaService extends IService<CmsResourceAppArea> {

	boolean saves(CmsResourceAppArea entity);
}
