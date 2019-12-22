package com.csl.plus.portal.cms.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.csl.plus.cms.entity.CmsExpertData;
import com.csl.plus.cms.mapper.CmsExpertDataMapper;
import com.csl.plus.portal.cms.service.ICmsExpertDataService;

@Service("cmsExpertDataService")
public class CmsExpertDataServiceImpl extends ServiceImpl<CmsExpertDataMapper, CmsExpertData>
		implements ICmsExpertDataService {

	@Resource
	private CmsExpertDataMapper cmsExpertDataMapper;

	@Transactional
	public boolean saves(CmsExpertData entity) {
		entity.setCreateDate(new Date());
		cmsExpertDataMapper.insert(entity);
		return true;
	}

}