package com.csl.plus.cms.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.csl.plus.cms.mapper.CmsNoticeDataMapper;
import com.csl.plus.cms.entity.CmsNoticeData;
import com.csl.plus.cms.service.ICmsNoticeDataService;

import javax.annotation.Resource;
import java.util.Date;


@Service("cmsNoticeDataService")
public class CmsNoticeDataServiceImpl extends ServiceImpl<CmsNoticeDataMapper, CmsNoticeData> implements ICmsNoticeDataService {

    @Resource
    private CmsNoticeDataMapper cmsNoticeDataMapper;
    
    @Transactional
    public boolean saves(CmsNoticeData entity){
    	entity.setCreateDate(new Date());
        cmsNoticeDataMapper.insert(entity);
        return true;
    }

}