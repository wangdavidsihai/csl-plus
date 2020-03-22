package com.csl.plus.res.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.csl.plus.audit.entity.ReviewLog;
import com.csl.plus.res.entity.ResTechnical;

import java.util.List;


/**
 * 需求表
 *
 * @author David
 * @email
 * @date 2020-02-15 22:12:07
 */
public interface IResTechnicalService extends IService<ResTechnical> {


    boolean saves(ResTechnical entity);

    List<ResTechnical> getList();

    int updateVerifyStatus(Long ids, Integer verifyStatus, String detail);

    List<ReviewLog> getVertifyRecord(Long id, String sysGroup);
}

