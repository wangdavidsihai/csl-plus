package com.csl.plus.cms.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.csl.plus.cms.mapper.CmsCompanyMapper;
import com.csl.plus.cms.entity.CmsCompany;
import com.csl.plus.cms.service.ICmsCompanyService;

import javax.annotation.Resource;
import java.util.Date;


@Service("cmsCompanyService")
public class CmsCompanyServiceImpl extends ServiceImpl<CmsCompanyMapper, CmsCompany> implements ICmsCompanyService {

    @Resource
    private CmsCompanyMapper cmsCompanyMapper;
    
    @Transactional
    public boolean saves(CmsCompany entity){
    	entity.setCreateDate(new Date());
        cmsCompanyMapper.insert(entity);
        return true;
    }

}