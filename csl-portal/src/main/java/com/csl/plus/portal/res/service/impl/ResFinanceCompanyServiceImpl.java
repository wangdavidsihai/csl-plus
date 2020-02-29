package com.csl.plus.portal.res.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.csl.plus.portal.res.service.IResFinanceCompanyService;
import com.csl.plus.res.entity.ResFinanceCompany;
import com.csl.plus.res.mapper.ResFinanceCompanyMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;


@Service("resFinanceCompanyService")
public class ResFinanceCompanyServiceImpl extends ServiceImpl<ResFinanceCompanyMapper, ResFinanceCompany> implements IResFinanceCompanyService {

    @Resource
    private ResFinanceCompanyMapper resFinanceCompanyMapper;

    @Transactional
    public boolean saves(ResFinanceCompany entity) {
        entity.setCreateDate(new Date());
        resFinanceCompanyMapper.insert(entity);
        return true;
    }

}