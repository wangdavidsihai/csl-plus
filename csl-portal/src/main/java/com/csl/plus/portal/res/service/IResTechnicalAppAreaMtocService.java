package com.csl.plus.portal.res.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.csl.plus.res.entity.ResTechnicalAppAreaMtoc;


/**
 * 技术应用领域表
 *
 * @author David
 * @email
 * @date 2020-02-29 11:32:27
 */
public interface IResTechnicalAppAreaMtocService extends IService<ResTechnicalAppAreaMtoc> {


    boolean saves(ResTechnicalAppAreaMtoc entity);
}

