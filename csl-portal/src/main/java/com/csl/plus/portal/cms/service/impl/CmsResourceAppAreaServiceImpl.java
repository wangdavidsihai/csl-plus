package com.csl.plus.portal.cms.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.csl.plus.cms.entity.CmsResourceAppArea;
import com.csl.plus.cms.mapper.CmsResourceAppAreaMapper;
import com.csl.plus.portal.cms.service.ICmsResourceAppAreaService;

@Service("cmsResourceAppAreaService")
public class CmsResourceAppAreaServiceImpl extends ServiceImpl<CmsResourceAppAreaMapper, CmsResourceAppArea>
		implements ICmsResourceAppAreaService {

	@Resource
	private CmsResourceAppAreaMapper cmsResourceAppAreaMapper;

	@Transactional
	public boolean saves(CmsResourceAppArea entity) {
		entity.setCreateDate(new Date());
		cmsResourceAppAreaMapper.insert(entity);
		return true;
	}

}