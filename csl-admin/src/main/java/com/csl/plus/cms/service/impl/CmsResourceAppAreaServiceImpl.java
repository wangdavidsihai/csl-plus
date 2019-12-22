package com.csl.plus.cms.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.csl.plus.cms.mapper.CmsResourceAppAreaMapper;
import com.csl.plus.cms.entity.CmsResourceAppArea;
import com.csl.plus.cms.service.ICmsResourceAppAreaService;

import javax.annotation.Resource;
import java.util.Date;


@Service("cmsResourceAppAreaService")
public class CmsResourceAppAreaServiceImpl extends ServiceImpl<CmsResourceAppAreaMapper, CmsResourceAppArea> implements ICmsResourceAppAreaService {

    @Resource
    private CmsResourceAppAreaMapper cmsResourceAppAreaMapper;
    
    @Transactional
    public boolean saves(CmsResourceAppArea entity){
    	entity.setCreateDate(new Date());
        cmsResourceAppAreaMapper.insert(entity);
        return true;
    }

}