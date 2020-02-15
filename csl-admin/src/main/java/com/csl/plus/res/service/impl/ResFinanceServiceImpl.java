package com.csl.plus.res.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.csl.plus.res.mapper.ResFinanceMapper;
import com.csl.plus.res.entity.ResFinance;
import com.csl.plus.res.service.IResFinanceService;

import javax.annotation.Resource;
import java.util.Date;


@Service("resFinanceService")
public class ResFinanceServiceImpl extends ServiceImpl<ResFinanceMapper, ResFinance> implements IResFinanceService {

    @Resource
    private ResFinanceMapper resFinanceMapper;
    
    @Transactional
    public boolean saves(ResFinance entity){
    	entity.setCreateDate(new Date());
        resFinanceMapper.insert(entity);
        return true;
    }

}