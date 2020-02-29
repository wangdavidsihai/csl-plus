package com.csl.plus.res.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.csl.plus.res.entity.ResTechnicalMtoc;
import com.csl.plus.res.mapper.ResTechnicalMtocMapper;
import com.csl.plus.res.service.IResTechnicalMtocService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;


@Service("resTechnicalMtocService")
public class ResTechnicalMtocServiceImpl extends ServiceImpl<ResTechnicalMtocMapper, ResTechnicalMtoc> implements IResTechnicalMtocService {

    @Resource
    private ResTechnicalMtocMapper resTechnicalMtocMapper;

    @Transactional
    public boolean saves(ResTechnicalMtoc entity) {
        entity.setCreateDate(new Date());
        resTechnicalMtocMapper.insert(entity);
        return true;
    }

}