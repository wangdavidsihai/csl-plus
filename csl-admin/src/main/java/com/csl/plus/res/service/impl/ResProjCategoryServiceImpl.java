package com.csl.plus.res.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.csl.plus.res.mapper.ResProjCategoryMapper;
import com.csl.plus.res.entity.ResProjCategory;
import com.csl.plus.res.service.IResProjCategoryService;

import javax.annotation.Resource;
import java.util.Date;


@Service("resProjCategoryService")
public class ResProjCategoryServiceImpl extends ServiceImpl<ResProjCategoryMapper, ResProjCategory> implements IResProjCategoryService {

    @Resource
    private ResProjCategoryMapper resProjCategoryMapper;
    
    @Transactional
    public boolean saves(ResProjCategory entity){
    	entity.setCreateDate(new Date());
        resProjCategoryMapper.insert(entity);
        return true;
    }

}