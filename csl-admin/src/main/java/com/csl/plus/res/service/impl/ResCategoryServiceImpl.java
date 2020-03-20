package com.csl.plus.res.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.csl.plus.res.mapper.ResCategoryMapper;
import com.csl.plus.res.entity.ResCategory;
import com.csl.plus.res.service.IResCategoryService;

import javax.annotation.Resource;
import java.util.Date;


@Service("resCategoryService")
public class ResCategoryServiceImpl extends ServiceImpl<ResCategoryMapper, ResCategory> implements IResCategoryService {

    @Resource
    private ResCategoryMapper resCategoryMapper;
    
    @Transactional
    public boolean saves(ResCategory entity){
    	entity.setCreateDate(new Date());
        resCategoryMapper.insert(entity);
        return true;
    }

}