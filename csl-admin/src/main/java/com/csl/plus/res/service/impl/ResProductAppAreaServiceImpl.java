package com.csl.plus.res.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.csl.plus.res.mapper.ResProductAppAreaMapper;
import com.csl.plus.res.entity.ResProductAppArea;
import com.csl.plus.res.service.IResProductAppAreaService;

import javax.annotation.Resource;
import java.util.Date;


@Service("resProductAppAreaService")
public class ResProductAppAreaServiceImpl extends ServiceImpl<ResProductAppAreaMapper, ResProductAppArea> implements IResProductAppAreaService {

    @Resource
    private ResProductAppAreaMapper resProductAppAreaMapper;
    
    @Transactional
    public boolean saves(ResProductAppArea entity){
    	entity.setCreateDate(new Date());
        resProductAppAreaMapper.insert(entity);
        return true;
    }

}