package com.csl.plus.portal.res.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.csl.plus.res.entity.ResFinanceCompanyData;


/**
 * @author David
 * @email
 * @date 2020-02-29 12:55:20
 */
public interface IResFinanceCompanyDataService extends IService<ResFinanceCompanyData> {


    boolean saves(ResFinanceCompanyData entity);
}

