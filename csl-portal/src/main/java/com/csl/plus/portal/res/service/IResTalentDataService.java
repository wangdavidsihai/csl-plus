package com.csl.plus.portal.res.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.csl.plus.res.entity.ResTalentData;


/**
 * 专家表
 *
 * @author David
 * @email
 * @date 2020-02-18 21:43:07
 */
public interface IResTalentDataService extends IService<ResTalentData> {


    boolean saves(ResTalentData entity);

    ResTalentData getTalentDataByTalId(Long talId);
}

