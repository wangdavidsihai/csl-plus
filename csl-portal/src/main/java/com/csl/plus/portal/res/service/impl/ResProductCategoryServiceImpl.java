package com.csl.plus.portal.res.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.csl.plus.portal.res.service.IResProductCategoryService;
import com.csl.plus.res.entity.ResProductCategory;
import com.csl.plus.res.mapper.ResProductCategoryMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;


@Service("resProductCategoryService")
public class ResProductCategoryServiceImpl extends ServiceImpl<ResProductCategoryMapper, ResProductCategory> implements IResProductCategoryService {

    @Resource
    private ResProductCategoryMapper resProductCategoryMapper;

    @Transactional
    public boolean saves(ResProductCategory entity) {
        entity.setCreateDate(new Date());
        resProductCategoryMapper.insert(entity);
        return true;
    }

}