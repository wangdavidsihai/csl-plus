package com.csl.plus.portal.cms.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.csl.plus.cms.entity.CmsNotice;
import com.csl.plus.cms.mapper.CmsNoticeMapper;
import com.csl.plus.portal.cms.service.ICmsNoticeService;

@Service("cmsNoticeService")
public class CmsNoticeServiceImpl extends ServiceImpl<CmsNoticeMapper, CmsNotice> implements ICmsNoticeService {

	@Resource
	private CmsNoticeMapper cmsNoticeMapper;

	@Transactional
	public boolean saves(CmsNotice entity) {
		entity.setCreateDate(new Date());
		cmsNoticeMapper.insert(entity);
		return true;
	}

}