package com.csl.plus.res.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.csl.plus.audit.entity.ReviewLog;
import com.csl.plus.res.entity.ResProject;

import java.util.List;


/**
 * 项目表
 *
 * @author David
 * @email
 * @date 2020-02-15 22:12:07
 */
public interface IResProjectService extends IService<ResProject> {


    boolean saves(ResProject entity);

    List<ResProject> getList();

    int updateVerifyStatus(Long ids, Integer verifyStatus, String detail);

    List<ReviewLog> getVertifyRecord(Long id, String sysGroup);
}

