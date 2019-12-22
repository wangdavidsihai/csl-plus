package com.csl.plus.portal.cms.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.csl.plus.cms.entity.CmsResource;
import com.csl.plus.cms.mapper.CmsResourceMapper;
import com.csl.plus.portal.cms.service.ICmsResourceService;

@Service("cmsResourceService")
public class CmsResourceServiceImpl extends ServiceImpl<CmsResourceMapper, CmsResource> implements ICmsResourceService {

	@Resource
	private CmsResourceMapper cmsResourceMapper;

	@Transactional
	public boolean saves(CmsResource entity) {
		entity.setCreateDate(new Date());
		cmsResourceMapper.insert(entity);
		return true;
	}

}