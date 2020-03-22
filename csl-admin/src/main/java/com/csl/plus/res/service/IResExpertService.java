package com.csl.plus.res.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.csl.plus.audit.entity.ReviewLog;
import com.csl.plus.res.entity.ResExpert;

import java.util.List;


/**
 * 专家表
 *
 * @author David
 * @email
 * @date 2020-02-15 22:48:23
 */
public interface IResExpertService extends IService<ResExpert> {


    boolean saves(ResExpert entity);

    int updateVerifyStatus(Long ids, Integer verifyStatus, String detail);

    List<ReviewLog> getVertifyRecord(Long id, String sysGroup);
}

