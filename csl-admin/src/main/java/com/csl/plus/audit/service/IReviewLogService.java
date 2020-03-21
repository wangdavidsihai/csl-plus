package com.csl.plus.audit.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.csl.plus.audit.entity.ReviewLog;


/**
 * 商品审核记录
 *
 * @author David
 * @email 
 * @date 2020-03-21 20:29:15
 */
public interface IReviewLogService extends IService<ReviewLog> {

    
    boolean saves(ReviewLog entity);
}

