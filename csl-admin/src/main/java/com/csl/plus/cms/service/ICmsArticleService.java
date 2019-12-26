package com.csl.plus.cms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.csl.plus.cms.entity.CmsArticle;

/**
 * 文章表
 *
 * @author David
 * @email wangdavidsihai@gmail.com
 * @date 2019-12-22 14:02:33
 */
public interface ICmsArticleService extends IService<CmsArticle> {

	boolean saves(CmsArticle entity);

	int updateShowStatus(Long ids, Integer showStatus);
}
