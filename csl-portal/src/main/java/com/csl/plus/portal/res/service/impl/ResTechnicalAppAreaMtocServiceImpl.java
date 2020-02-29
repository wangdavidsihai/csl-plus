package com.csl.plus.portal.res.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.csl.plus.portal.res.service.IResTechnicalAppAreaMtocService;
import com.csl.plus.res.entity.ResTechnicalAppAreaMtoc;
import com.csl.plus.res.mapper.ResTechnicalAppAreaMtocMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;


@Service("resTechnicalAppAreaMtocService")
public class ResTechnicalAppAreaMtocServiceImpl extends ServiceImpl<ResTechnicalAppAreaMtocMapper, ResTechnicalAppAreaMtoc> implements IResTechnicalAppAreaMtocService {

    @Resource
    private ResTechnicalAppAreaMtocMapper resTechnicalAppAreaMtocMapper;

    @Transactional
    public boolean saves(ResTechnicalAppAreaMtoc entity) {
        entity.setCreateDate(new Date());
        resTechnicalAppAreaMtocMapper.insert(entity);
        return true;
    }

}