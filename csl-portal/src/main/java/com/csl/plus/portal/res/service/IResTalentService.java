package com.csl.plus.portal.res.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.csl.plus.res.entity.ResTalent;


/**
 * 专家表
 *
 * @author David
 * @email
 * @date 2020-02-18 21:43:07
 */
public interface IResTalentService extends IService<ResTalent> {


    boolean saves(ResTalent entity);
}

