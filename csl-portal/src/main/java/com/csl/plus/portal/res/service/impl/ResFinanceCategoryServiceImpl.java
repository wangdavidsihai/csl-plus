package com.csl.plus.portal.res.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.csl.plus.portal.res.service.IResFinanceCategoryService;
import com.csl.plus.res.entity.ResFinanceCategory;
import com.csl.plus.res.mapper.ResFinanceCategoryMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;


@Service("resFinanceCategoryService")
public class ResFinanceCategoryServiceImpl extends ServiceImpl<ResFinanceCategoryMapper, ResFinanceCategory> implements IResFinanceCategoryService {

    @Resource
    private ResFinanceCategoryMapper resFinanceCategoryMapper;

    @Transactional
    public boolean saves(ResFinanceCategory entity) {
        entity.setCreateDate(new Date());
        resFinanceCategoryMapper.insert(entity);
        return true;
    }

}