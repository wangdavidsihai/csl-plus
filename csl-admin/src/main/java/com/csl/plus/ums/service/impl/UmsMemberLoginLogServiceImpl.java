package com.csl.plus.ums.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.csl.plus.ums.entity.UmsMemberLoginLog;
import com.csl.plus.ums.mapper.UmsMemberLoginLogMapper;
import com.csl.plus.ums.service.IUmsMemberLoginLogService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;


@Service("umsMemberLoginLogService")
public class UmsMemberLoginLogServiceImpl extends ServiceImpl<UmsMemberLoginLogMapper, UmsMemberLoginLog> implements IUmsMemberLoginLogService {

    @Resource
    private UmsMemberLoginLogMapper umsMemberLoginLogMapper;

    @Transactional
    public boolean saves(UmsMemberLoginLog entity) {
        entity.setCreateDate(new Date());
        umsMemberLoginLogMapper.insert(entity);
        return true;
    }

}