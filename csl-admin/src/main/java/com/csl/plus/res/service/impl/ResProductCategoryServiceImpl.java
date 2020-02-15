package com.csl.plus.res.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.csl.plus.res.mapper.ResProductCategoryMapper;
import com.csl.plus.res.entity.ResProductCategory;
import com.csl.plus.res.service.IResProductCategoryService;

import javax.annotation.Resource;
import java.util.Date;


@Service("resProductCategoryService")
public class ResProductCategoryServiceImpl extends ServiceImpl<ResProductCategoryMapper, ResProductCategory> implements IResProductCategoryService {

    @Resource
    private ResProductCategoryMapper resProductCategoryMapper;
    
    @Transactional
    public boolean saves(ResProductCategory entity){
    	entity.setCreateDate(new Date());
        resProductCategoryMapper.insert(entity);
        return true;
    }

}