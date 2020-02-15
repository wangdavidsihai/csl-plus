package com.csl.plus.portal.res.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.csl.plus.portal.res.service.IResTechnicalCategoryService;
import com.csl.plus.res.entity.ResTechnicalCategory;
import com.csl.plus.res.mapper.ResTechnicalCategoryMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;


@Service("resTechnicalCategoryService")
public class ResTechnicalCategoryServiceImpl extends ServiceImpl<ResTechnicalCategoryMapper, ResTechnicalCategory> implements IResTechnicalCategoryService {

    @Resource
    private ResTechnicalCategoryMapper resTechnicalCategoryMapper;

    @Transactional
    public boolean saves(ResTechnicalCategory entity) {
        entity.setCreateDate(new Date());
        resTechnicalCategoryMapper.insert(entity);
        return true;
    }

}