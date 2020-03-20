package com.csl.plus.res.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.csl.plus.res.entity.ResTechnical;
import com.csl.plus.res.mapper.ResTechnicalMapper;
import com.csl.plus.res.service.IResTechnicalService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;


@Service("resTechnicalService")
public class ResTechnicalServiceImpl extends ServiceImpl<ResTechnicalMapper, ResTechnical> implements IResTechnicalService {

    @Resource
    private ResTechnicalMapper resTechnicalMapper;

    @Transactional
    public boolean saves(ResTechnical entity) {
        entity.setCreateDate(new Date());
        resTechnicalMapper.insert(entity);
        return true;
    }

    @Override
    public List<ResTechnical> getList() {
        return resTechnicalMapper.getList();
    }

}