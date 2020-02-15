package com.csl.plus.portal.res.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.csl.plus.portal.res.service.IResExpertService;
import com.csl.plus.res.entity.ResExpert;
import com.csl.plus.res.mapper.ResExpertMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;


@Service("resExpertService")
public class ResExpertServiceImpl extends ServiceImpl<ResExpertMapper, ResExpert> implements IResExpertService {

    @Resource
    private ResExpertMapper resExpertMapper;

    @Transactional
    public boolean saves(ResExpert entity) {
        entity.setCreateDate(new Date());
        resExpertMapper.insert(entity);
        return true;
    }

}