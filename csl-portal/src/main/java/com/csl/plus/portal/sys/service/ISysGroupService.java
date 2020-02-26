package com.csl.plus.portal.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.csl.plus.sys.entity.SysGroup;


/**
 * 系统类别表
 *
 * @author David
 * @email
 * @date 2020-02-26 13:23:47
 */
public interface ISysGroupService extends IService<SysGroup> {


    boolean saves(SysGroup entity);
}

