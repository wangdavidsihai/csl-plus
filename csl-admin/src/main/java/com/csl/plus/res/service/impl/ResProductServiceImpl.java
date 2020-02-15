package com.csl.plus.res.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.csl.plus.res.mapper.ResProductMapper;
import com.csl.plus.res.entity.ResProduct;
import com.csl.plus.res.service.IResProductService;

import javax.annotation.Resource;
import java.util.Date;


@Service("resProductService")
public class ResProductServiceImpl extends ServiceImpl<ResProductMapper, ResProduct> implements IResProductService {

    @Resource
    private ResProductMapper resProductMapper;
    
    @Transactional
    public boolean saves(ResProduct entity){
    	entity.setCreateDate(new Date());
        resProductMapper.insert(entity);
        return true;
    }

}