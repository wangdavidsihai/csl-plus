package com.csl.plus.cms.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.csl.plus.cms.entity.CmsArticle;
import com.csl.plus.cms.mapper.CmsArticleMapper;
import com.csl.plus.cms.service.ICmsArticleService;
import com.csl.plus.common.utils.CommonCodeConst;

@Service("cmsArticleService")
public class CmsArticleServiceImpl extends ServiceImpl<CmsArticleMapper, CmsArticle> implements ICmsArticleService {

	@Resource
	private CmsArticleMapper cmsArticleMapper;

	@Transactional
	public boolean saves(CmsArticle entity) {
		Authentication au = SecurityContextHolder.getContext().getAuthentication();
		entity.setStatus(CommonCodeConst.STATUS_REVIEW);
		entity.setCreateBy(au.getName());
		entity.setCreateDate(new Date());
		cmsArticleMapper.insert(entity);
		return true;
	}

}