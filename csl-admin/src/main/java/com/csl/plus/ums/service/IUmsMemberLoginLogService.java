package com.csl.plus.ums.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.csl.plus.ums.entity.UmsMemberLoginLog;


/**
 * 会员登录记录
 *
 * @author David
 * @email
 * @date 2020-03-04 14:52:45
 */
public interface IUmsMemberLoginLogService extends IService<UmsMemberLoginLog> {


    boolean saves(UmsMemberLoginLog entity);
}

