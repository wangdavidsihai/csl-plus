package com.csl.plus.portal.cms.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.csl.plus.cms.entity.CmsArticleData;
import com.csl.plus.cms.mapper.CmsArticleDataMapper;
import com.csl.plus.portal.cms.service.ICmsArticleDataService;

@Service("cmsArticleDataService")
public class CmsArticleDataServiceImpl extends ServiceImpl<CmsArticleDataMapper, CmsArticleData>
		implements ICmsArticleDataService {

	@Resource
	private CmsArticleDataMapper cmsArticleDataMapper;

	@Transactional
	public boolean saves(CmsArticleData entity) {
		entity.setCreateDate(new Date());
		cmsArticleDataMapper.insert(entity);
		return true;
	}

}