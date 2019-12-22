package com.csl.plus.cms.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.csl.plus.cms.mapper.CmsExpertMapper;
import com.csl.plus.cms.entity.CmsExpert;
import com.csl.plus.cms.service.ICmsExpertService;

import javax.annotation.Resource;
import java.util.Date;


@Service("cmsExpertService")
public class CmsExpertServiceImpl extends ServiceImpl<CmsExpertMapper, CmsExpert> implements ICmsExpertService {

    @Resource
    private CmsExpertMapper cmsExpertMapper;
    
    @Transactional
    public boolean saves(CmsExpert entity){
    	entity.setCreateDate(new Date());
        cmsExpertMapper.insert(entity);
        return true;
    }

}