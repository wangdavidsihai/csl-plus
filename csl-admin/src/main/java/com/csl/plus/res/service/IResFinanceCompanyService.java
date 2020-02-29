package com.csl.plus.res.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.csl.plus.res.entity.ResFinanceCompany;


/**
 * @author David
 * @email
 * @date 2020-02-29 12:55:20
 */
public interface IResFinanceCompanyService extends IService<ResFinanceCompany> {


    boolean saves(ResFinanceCompany entity);
}

