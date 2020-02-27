package com.csl.plus.cms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.csl.plus.cms.entity.CmsArticle;
import com.csl.plus.cms.mapper.CmsArticleMapper;
import com.csl.plus.cms.service.ICmsArticleService;
import com.csl.plus.common.utils.CommonCodes;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

@Service("cmsArticleService")
public class CmsArticleServiceImpl extends ServiceImpl<CmsArticleMapper, CmsArticle> implements ICmsArticleService {

    @Resource
    private CmsArticleMapper cmsArticleMapper;

    @Transactional
    public boolean saves(CmsArticle entity) {
        Authentication au = SecurityContextHolder.getContext().getAuthentication();
        entity.setStatus(CommonCodes.STATUS_REVIEW);
        entity.setCreateBy(au.getName());
        entity.setCreateDate(new Date());
        cmsArticleMapper.insert(entity);
        return true;
    }

    @Override
    public int updateShowStatus(Long id, Integer showStatus) {
        CmsArticle record = new CmsArticle();
        if (showStatus == 0) {
            record.setStatus(CommonCodes.STATUS_TERMINATED);
        } else {
            record.setStatus(CommonCodes.STATUS_REVIEW);
        }
        return cmsArticleMapper.update(record, new QueryWrapper<CmsArticle>().eq("id", id));
    }

}