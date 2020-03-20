package com.csl.plus.res.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.csl.plus.res.entity.ResFinance;
import com.csl.plus.res.mapper.ResFinanceMapper;
import com.csl.plus.res.service.IResFinanceService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;


@Service("resFinanceService")
public class ResFinanceServiceImpl extends ServiceImpl<ResFinanceMapper, ResFinance> implements IResFinanceService {

    @Resource
    private ResFinanceMapper resFinanceMapper;

    @Transactional
    public boolean saves(ResFinance entity) {
        entity.setCreateDate(new Date());
        resFinanceMapper.insert(entity);
        return true;
    }

    @Override
    public List<ResFinance> getList() {
        return resFinanceMapper.getList();
    }

}