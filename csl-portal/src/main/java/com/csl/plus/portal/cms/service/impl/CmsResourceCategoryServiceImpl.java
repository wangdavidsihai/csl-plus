package com.csl.plus.portal.cms.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.csl.plus.cms.entity.CmsResourceCategory;
import com.csl.plus.cms.mapper.CmsResourceCategoryMapper;
import com.csl.plus.portal.cms.service.ICmsResourceCategoryService;

@Service("cmsResourceCategoryService")
public class CmsResourceCategoryServiceImpl extends ServiceImpl<CmsResourceCategoryMapper, CmsResourceCategory>
		implements ICmsResourceCategoryService {

	@Resource
	private CmsResourceCategoryMapper cmsResourceCategoryMapper;

	@Transactional
	public boolean saves(CmsResourceCategory entity) {
		entity.setCreateDate(new Date());
		cmsResourceCategoryMapper.insert(entity);
		return true;
	}

}