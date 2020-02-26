package com.csl.plus.portal.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.csl.plus.portal.sys.service.ISysGroupService;
import com.csl.plus.sys.entity.SysGroup;
import com.csl.plus.sys.mapper.SysGroupMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;


@Service("sysGroupService")
public class SysGroupServiceImpl extends ServiceImpl<SysGroupMapper, SysGroup> implements ISysGroupService {

    @Resource
    private SysGroupMapper sysGroupMapper;

    @Transactional
    public boolean saves(SysGroup entity) {
        entity.setCreateDate(new Date());
        sysGroupMapper.insert(entity);
        return true;
    }

}