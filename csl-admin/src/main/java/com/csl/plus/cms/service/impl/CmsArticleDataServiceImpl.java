package com.csl.plus.cms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.csl.plus.cms.entity.CmsArticle;
import com.csl.plus.cms.entity.CmsArticleData;
import com.csl.plus.cms.mapper.CmsArticleDataMapper;
import com.csl.plus.cms.service.ICmsArticleDataService;
import com.csl.plus.common.utils.CommonCodes;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

@Service("cmsArticleDataService")
public class CmsArticleDataServiceImpl extends ServiceImpl<CmsArticleDataMapper, CmsArticleData>
        implements ICmsArticleDataService {

    @Resource
    private CmsArticleDataMapper cmsArticleDataMapper;

    @Transactional
    public boolean saves(CmsArticle entity) {
        CmsArticleData detail = entity.getDetail();
        if (detail == null) {
            return false;
        }
        Authentication au = SecurityContextHolder.getContext().getAuthentication();
        detail.setAllowComment(CommonCodes.ALLOWED);
        detail.setTitle(entity.getTitle());
        detail.setAuthor(au.getName());
        detail.setCreateBy(au.getName());
        detail.setStatus(CommonCodes.STATUS_REVIEW);
        detail.setCreateDate(new Date());
        cmsArticleDataMapper.insert(detail);
        return true;
    }

}