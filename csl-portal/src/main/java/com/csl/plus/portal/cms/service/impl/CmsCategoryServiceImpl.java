package com.csl.plus.portal.cms.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.csl.plus.cms.entity.CmsCategory;
import com.csl.plus.cms.mapper.CmsCategoryMapper;
import com.csl.plus.portal.cms.service.ICmsCategoryService;

@Service("cmsCategoryService")
public class CmsCategoryServiceImpl extends ServiceImpl<CmsCategoryMapper, CmsCategory> implements ICmsCategoryService {

	@Resource
	private CmsCategoryMapper cmsCategoryMapper;

	@Transactional
	public boolean saves(CmsCategory entity) {
		entity.setCreateDate(new Date());
		cmsCategoryMapper.insert(entity);
		return true;
	}

}