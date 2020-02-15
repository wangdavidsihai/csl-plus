package com.csl.plus.res.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.csl.plus.res.mapper.ResFinanceAppAreaMapper;
import com.csl.plus.res.entity.ResFinanceAppArea;
import com.csl.plus.res.service.IResFinanceAppAreaService;

import javax.annotation.Resource;
import java.util.Date;


@Service("resFinanceAppAreaService")
public class ResFinanceAppAreaServiceImpl extends ServiceImpl<ResFinanceAppAreaMapper, ResFinanceAppArea> implements IResFinanceAppAreaService {

    @Resource
    private ResFinanceAppAreaMapper resFinanceAppAreaMapper;
    
    @Transactional
    public boolean saves(ResFinanceAppArea entity){
    	entity.setCreateDate(new Date());
        resFinanceAppAreaMapper.insert(entity);
        return true;
    }

}