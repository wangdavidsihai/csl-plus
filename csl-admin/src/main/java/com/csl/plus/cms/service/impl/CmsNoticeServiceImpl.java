package com.csl.plus.cms.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.csl.plus.cms.mapper.CmsNoticeMapper;
import com.csl.plus.cms.entity.CmsNotice;
import com.csl.plus.cms.service.ICmsNoticeService;

import javax.annotation.Resource;
import java.util.Date;


@Service("cmsNoticeService")
public class CmsNoticeServiceImpl extends ServiceImpl<CmsNoticeMapper, CmsNotice> implements ICmsNoticeService {

    @Resource
    private CmsNoticeMapper cmsNoticeMapper;
    
    @Transactional
    public boolean saves(CmsNotice entity){
    	entity.setCreateDate(new Date());
        cmsNoticeMapper.insert(entity);
        return true;
    }

}