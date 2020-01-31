package com.csl.plus.inbox.service.impl;

import com.csl.plus.inbox.entity.MtMessage;
import com.csl.plus.inbox.mapper.MtMessageMapper;
import com.csl.plus.inbox.service.IMtMessageService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


import javax.annotation.Resource;
import java.util.Date;


@Service("mtMessageService")
public class MtMessageServiceImpl extends ServiceImpl<MtMessageMapper, MtMessage> implements IMtMessageService {

    @Resource
    private MtMessageMapper mtMessageMapper;
    
    @Transactional
    public boolean saves(MtMessage entity){
    	entity.setCreateDate(new Date());
        mtMessageMapper.insert(entity);
        return true;
    }

}