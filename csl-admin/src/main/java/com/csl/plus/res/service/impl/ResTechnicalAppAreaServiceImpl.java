package com.csl.plus.res.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.csl.plus.res.mapper.ResTechnicalAppAreaMapper;
import com.csl.plus.res.entity.ResTechnicalAppArea;
import com.csl.plus.res.service.IResTechnicalAppAreaService;

import javax.annotation.Resource;
import java.util.Date;


@Service("resTechnicalAppAreaService")
public class ResTechnicalAppAreaServiceImpl extends ServiceImpl<ResTechnicalAppAreaMapper, ResTechnicalAppArea> implements IResTechnicalAppAreaService {

    @Resource
    private ResTechnicalAppAreaMapper resTechnicalAppAreaMapper;
    
    @Transactional
    public boolean saves(ResTechnicalAppArea entity){
    	entity.setCreateDate(new Date());
        resTechnicalAppAreaMapper.insert(entity);
        return true;
    }

}