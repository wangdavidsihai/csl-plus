package com.csl.plus.res.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.csl.plus.res.entity.ResFinanceCompanyData;
import com.csl.plus.res.mapper.ResFinanceCompanyDataMapper;
import com.csl.plus.res.service.IResFinanceCompanyDataService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;


@Service("resFinanceCompanyDataService")
public class ResFinanceCompanyDataServiceImpl extends ServiceImpl<ResFinanceCompanyDataMapper, ResFinanceCompanyData> implements IResFinanceCompanyDataService {

    @Resource
    private ResFinanceCompanyDataMapper resFinanceCompanyDataMapper;

    @Transactional
    public boolean saves(ResFinanceCompanyData entity) {
        entity.setCreateDate(new Date());
        resFinanceCompanyDataMapper.insert(entity);
        return true;
    }

}