package com.csl.plus.res.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.csl.plus.res.entity.ResFinance;

import java.util.List;


/**
 * 金融需求表
 *
 * @author David
 * @email
 * @date 2020-02-15 22:12:07
 */
public interface IResFinanceService extends IService<ResFinance> {


    boolean saves(ResFinance entity);

    List<ResFinance> getList();
}

