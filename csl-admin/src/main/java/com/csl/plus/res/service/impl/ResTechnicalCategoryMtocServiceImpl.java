package com.csl.plus.res.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.csl.plus.res.entity.ResTechnicalCategoryMtoc;
import com.csl.plus.res.mapper.ResTechnicalCategoryMtocMapper;
import com.csl.plus.res.service.IResTechnicalCategoryMtocService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;


@Service("resTechnicalCategoryMtocService")
public class ResTechnicalCategoryMtocServiceImpl extends ServiceImpl<ResTechnicalCategoryMtocMapper, ResTechnicalCategoryMtoc> implements IResTechnicalCategoryMtocService {

    @Resource
    private ResTechnicalCategoryMtocMapper resTechnicalCategoryMtocMapper;

    @Transactional
    public boolean saves(ResTechnicalCategoryMtoc entity) {
        entity.setCreateDate(new Date());
        resTechnicalCategoryMtocMapper.insert(entity);
        return true;
    }

}