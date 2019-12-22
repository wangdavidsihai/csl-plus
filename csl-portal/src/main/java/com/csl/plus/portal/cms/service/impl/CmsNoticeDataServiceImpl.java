package com.csl.plus.portal.cms.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.csl.plus.cms.entity.CmsNoticeData;
import com.csl.plus.cms.mapper.CmsNoticeDataMapper;
import com.csl.plus.portal.cms.service.ICmsNoticeDataService;

@Service("cmsNoticeDataService")
public class CmsNoticeDataServiceImpl extends ServiceImpl<CmsNoticeDataMapper, CmsNoticeData>
		implements ICmsNoticeDataService {

	@Resource
	private CmsNoticeDataMapper cmsNoticeDataMapper;

	@Transactional
	public boolean saves(CmsNoticeData entity) {
		entity.setCreateDate(new Date());
		cmsNoticeDataMapper.insert(entity);
		return true;
	}

}