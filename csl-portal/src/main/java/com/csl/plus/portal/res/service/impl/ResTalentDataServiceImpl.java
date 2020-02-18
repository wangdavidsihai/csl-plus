package com.csl.plus.portal.res.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.csl.plus.portal.res.service.IResTalentDataService;
import com.csl.plus.res.entity.ResTalentData;
import com.csl.plus.res.mapper.ResTalentDataMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;


@Service("resTalentDataService")
public class ResTalentDataServiceImpl extends ServiceImpl<ResTalentDataMapper, ResTalentData> implements IResTalentDataService {

    @Resource
    private ResTalentDataMapper resTalentDataMapper;

    @Transactional
    public boolean saves(ResTalentData entity) {
        entity.setCreateDate(new Date());
        resTalentDataMapper.insert(entity);
        return true;
    }

    @Override
    public ResTalentData getTalentDataByTalId(Long talId) {
        return resTalentDataMapper.getTalentDataByTalId(talId);
    }

}