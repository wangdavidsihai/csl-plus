package com.csl.plus.portal.res.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.csl.plus.portal.res.service.IResTalentService;
import com.csl.plus.res.entity.ResTalent;
import com.csl.plus.res.mapper.ResTalentMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;


@Service("resTalentService")
public class ResTalentServiceImpl extends ServiceImpl<ResTalentMapper, ResTalent> implements IResTalentService {

    @Resource
    private ResTalentMapper resTalentMapper;

    @Transactional
    public boolean saves(ResTalent entity) {
        entity.setCreateDate(new Date());
        resTalentMapper.insert(entity);
        return true;
    }

}