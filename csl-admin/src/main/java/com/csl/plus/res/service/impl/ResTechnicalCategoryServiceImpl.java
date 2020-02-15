package com.csl.plus.res.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.csl.plus.res.mapper.ResTechnicalCategoryMapper;
import com.csl.plus.res.entity.ResTechnicalCategory;
import com.csl.plus.res.service.IResTechnicalCategoryService;

import javax.annotation.Resource;
import java.util.Date;


@Service("resTechnicalCategoryService")
public class ResTechnicalCategoryServiceImpl extends ServiceImpl<ResTechnicalCategoryMapper, ResTechnicalCategory> implements IResTechnicalCategoryService {

    @Resource
    private ResTechnicalCategoryMapper resTechnicalCategoryMapper;
    
    @Transactional
    public boolean saves(ResTechnicalCategory entity){
    	entity.setCreateDate(new Date());
        resTechnicalCategoryMapper.insert(entity);
        return true;
    }

}