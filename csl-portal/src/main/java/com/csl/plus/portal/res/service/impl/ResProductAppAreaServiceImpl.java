package com.csl.plus.portal.res.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.csl.plus.portal.res.service.IResProductAppAreaService;
import com.csl.plus.res.entity.ResProductAppArea;
import com.csl.plus.res.mapper.ResProductAppAreaMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;


@Service("resProductAppAreaService")
public class ResProductAppAreaServiceImpl extends ServiceImpl<ResProductAppAreaMapper, ResProductAppArea> implements IResProductAppAreaService {

    @Resource
    private ResProductAppAreaMapper resProductAppAreaMapper;

    @Transactional
    public boolean saves(ResProductAppArea entity) {
        entity.setCreateDate(new Date());
        resProductAppAreaMapper.insert(entity);
        return true;
    }

}