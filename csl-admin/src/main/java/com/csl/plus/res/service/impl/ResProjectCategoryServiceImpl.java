package com.csl.plus.res.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.csl.plus.res.entity.ResProjectCategory;
import com.csl.plus.res.mapper.ResProjectCategoryMapper;
import com.csl.plus.res.service.IResProjectCategoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;


@Service("resProjCategoryService")
public class ResProjectCategoryServiceImpl extends ServiceImpl<ResProjectCategoryMapper, ResProjectCategory> implements IResProjectCategoryService {

    @Resource
    private ResProjectCategoryMapper resProjCategoryMapper;

    @Transactional
    public boolean saves(ResProjectCategory entity) {
        entity.setCreateDate(new Date());
        resProjCategoryMapper.insert(entity);
        return true;
    }

}